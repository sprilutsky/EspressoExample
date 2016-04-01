package cpm.sprilutsky.espressoexample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;

/**
 * Created by Sergey Prilutsky on 01.04.16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final int MAX_ITERATION_FOR_CHECK = 10;


    public MainActivityTest() {
        super(MainActivity.class);
        this.mActivityRule = new ActivityTestRule<>(
                MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void globalTest() {
        for (int j = 0; j < MAX_ITERATION_FOR_CHECK; j++) {
            checkIsDisplayUIOnMainActivity();

            checkInputEmptyMessage(typeText(""));
            checkErrorAlert();
            checkInputNonEmptyMessage(typeText(Long.toString(System.currentTimeMillis())));

            checkResultScreen();
            List<String> data = checkHistoryResultsScreen();
            checkDataOnHistoryResultsScreen(data);
        }
    }

    private void checkDataOnHistoryResultsScreen(List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            onData(anything()).inAdapterView(withId(R.id.data_list)).atPosition(i).perform(click());
            onView(withText(data.get(i))).inRoot(isDialog()).check(matches(isDisplayed()));
            onView(withText("OK")).inRoot(isDialog()).perform(click());
        }
        onView(withId(R.id.data_list)).perform(swipeDown());
        onView(withId(R.id.data_list)).perform(swipeUp());
        onView(withId(R.id.back)).perform(click());
    }

    private List<String> checkHistoryResultsScreen() {
        onView(withId(R.id.show_history)).check(matches(isDisplayed()));
        onView(withId(R.id.show_history)).perform(click());
        onView(withId(R.id.data_list)).check(matches(isDisplayed()));
        onView(withId(R.id.back)).check(matches(isDisplayed()));
        return AppSingleton.getInstance().getResults();
    }

    private void checkResultScreen() {
        onView(withId(R.id.result_txt)).check(matches(isDisplayed()));
        onView(withId(R.id.back)).check(matches(isDisplayed()));
        onView(withId(R.id.back)).perform(click());
    }

    private void checkErrorAlert() {
        onView(withText("Error")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Please input any message")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("OK")).inRoot(isDialog()).perform(click());
    }

    private void checkInputEmptyMessage(ViewAction viewAction) {
        checkInputNonEmptyMessage(viewAction);
    }

    private void checkInputNonEmptyMessage(ViewAction viewAction) {
        onView(withId(R.id.title)).perform(clearText());
        onView(withId(R.id.title)).perform(viewAction);
        onView(withId(R.id.submit)).perform(click());
    }

    private void checkIsDisplayUIOnMainActivity() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.submit)).check(matches(isDisplayed()));
    }
}

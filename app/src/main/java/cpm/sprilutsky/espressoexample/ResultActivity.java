package cpm.sprilutsky.espressoexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sergey Prilutsky on 01.04.16.
 */
public class ResultActivity extends AppCompatActivity {

    private static final String RESULT = "RESULT";
    private TextView resultTxt;
    private TextView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        initViews();
        parseIntent();

        setListeners();
    }

    private void initViews() {
        backBtn = (Button) findViewById(R.id.back);
        resultTxt = (TextView) findViewById(R.id.result_txt);
    }


    private void setListeners() {
        backBtn.setOnClickListener(new BackClickListener());
    }


    private void parseIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String result = bundle.getString(RESULT);
            if (result != null) {
                resultTxt.setText(result);
            }
        }
    }

    public static Intent getIntent(Context context, String result) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(RESULT, result);
        return intent;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private class BackClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}

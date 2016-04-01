package cpm.sprilutsky.espressoexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Prilutsky on 01.04.16.
 */
public class AppSingleton {
    private static AppSingleton ourInstance = new AppSingleton();

    public static AppSingleton getInstance() {
        return ourInstance;
    }

    private AppSingleton() {
    }

    private List<String> results;

    public List<String> getResults() {
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}

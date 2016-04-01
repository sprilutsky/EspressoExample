package cpm.sprilutsky.espressoexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button submitBtn;
    private Button showHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

    private void initViews() {
        input = (EditText) findViewById(R.id.title);
        submitBtn = (Button) findViewById(R.id.submit);
        showHistory = (Button) findViewById(R.id.show_history);
    }

    private void initListeners() {
        submitBtn.setOnClickListener(new SubmitClickListener());
        showHistory.setOnClickListener(new ShowHistoryClickListener());
    }

    private class SubmitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = input.getText().toString().trim();
            if (!text.isEmpty()) {
                AppSingleton.getInstance().getResults().add(text);
                startActivity(ResultActivity.getIntent(MainActivity.this, input.getText().toString().trim()));
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Please input any message");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        }
    }

    private class ShowHistoryClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(HistoryResultsActivity.getIntent(MainActivity.this));
        }
    }
}

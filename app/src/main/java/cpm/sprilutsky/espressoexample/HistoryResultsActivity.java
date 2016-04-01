package cpm.sprilutsky.espressoexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Sergey Prilutsky on 01.04.16.
 */
public class HistoryResultsActivity extends AppCompatActivity {

    private Button backBtn;
    private ListView dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histy_results_activity);
        initViews();
        initListeners();
        init();
    }

    private void initViews() {
        backBtn = (Button) findViewById(R.id.back);
        dataList = (ListView) findViewById(R.id.data_list);
    }

    private void initListeners() {
        backBtn.setOnClickListener(new BackCLickListener());
        dataList.setOnItemClickListener(new ItemClickListener());
    }

    private void init() {
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, AppSingleton.getInstance().getResults());
        dataList.setAdapter(adapter);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, HistoryResultsActivity.class);
    }

    private class BackCLickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HistoryResultsActivity.this);
            builder.setMessage((String) parent.getItemAtPosition(position));
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

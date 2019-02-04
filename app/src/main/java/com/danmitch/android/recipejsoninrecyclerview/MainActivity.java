package com.danmitch.android.recipejsoninrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private static final String TAG = "MainActivity";

    // vars
    public static TextView mTextViewTest;
    private Button mButtonFetchJson;
    private ArrayList<User> mUsers = new ArrayList<>();
    FetchJsonAsyncTask mFetchJsonAsyncTask = new FetchJsonAsyncTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonFetchJson = findViewById(R.id.btn_fetch_JSON);
        mTextViewTest = findViewById(R.id.txtView_test);

//        initRecyclerView();
        fetchJSON();
    }

    private void fetchJSON() {
        mButtonFetchJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: fetching JSON...");
                mFetchJsonAsyncTask.execute();
            }
        });
    }

//    private void initRecyclerView() {
//        Log.d(TAG, "initRecyclerView: started");
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mUsers);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }

    @Override
    public void processFinish(ArrayList<User> users) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, users);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

package com.mfeldsztejn.storetest.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.mfeldsztejn.storetest.R;

public class MainActivity extends AppCompatActivity {

    //It should be package protected since it will be used from an inner class
    RecyclerView articleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        articleRecyclerView = (RecyclerView) findViewById(R.id.article_recycler_view);
    }
}

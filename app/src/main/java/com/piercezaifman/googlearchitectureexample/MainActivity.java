package com.piercezaifman.googlearchitectureexample;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ZooListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ZooAdapter adapter = new ZooAdapter();
        recyclerView.setAdapter(adapter);

        mViewModel = ViewModelProviders.of(this).get(ZooListViewModel.class);
        mViewModel.getZoos().observe(this, (adapter::update));

        findViewById(R.id.activity_main_fab).setOnClickListener(v -> {
            Zoo zoo = new Zoo("My new zoo (" + new Random().nextInt(1000) + ")", new ArrayList<>());
            mViewModel.addZoo(zoo);
        });
    }
}

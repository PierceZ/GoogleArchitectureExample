package com.piercezaifman.googlearchitectureexample;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ZooAdapter adapter = new ZooAdapter();
        recyclerView.setAdapter(adapter);

        ZooListViewModel viewModel = ViewModelProviders.of(this).get(ZooListViewModel.class);
        viewModel.getZoos().observe(this, (adapter::update));
        viewModel.refresh();

        findViewById(R.id.activity_main_fab).setOnClickListener(v -> {
            Repository.get().addZoo(new Zoo("My new zoo (" + new Random().nextInt(1000) + ")", new ArrayList<>()));
            viewModel.refresh();
        });
    }
}

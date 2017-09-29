package com.piercezaifman.googlearchitectureexample.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.piercezaifman.googlearchitectureexample.R;
import com.piercezaifman.googlearchitectureexample.viewmodel.ZooListViewModel;

public class MainActivity extends AppCompatActivity {

    private ZooListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.activity_main_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ZooAdapter adapter = new ZooAdapter();
        adapter.setOnClickListener((view, position) -> {
            DialogFragment zooFragment = ZooFragment.newInstance(adapter.getItemId(position));
            zooFragment.show(getSupportFragmentManager(), ZooFragment.class.getName());
        });
        recyclerView.setAdapter(adapter);

        mViewModel = ViewModelProviders.of(this).get(ZooListViewModel.class);
        mViewModel.getZoos().observe(this, (adapter::update));

        findViewById(R.id.activity_main_fab).setOnClickListener(v -> {
            DialogFragment zooFragment = ZooFragment.newInstance();
            zooFragment.show(getSupportFragmentManager(), ZooFragment.class.getName());
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.refreshZoos();
    }
}

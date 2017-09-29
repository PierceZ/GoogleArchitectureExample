package com.piercezaifman.googlearchitectureexample.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.piercezaifman.googlearchitectureexample.R;
import com.piercezaifman.googlearchitectureexample.viewmodel.ZooActivityViewModel;

/**
 * Created by Pierce Zaifman on 2017-09-20.
 */

public class ZooActivity extends AppCompatActivity {

    public static final String EXTRA_ZOO_ID = "EXTRA_ZOO_ID";

    private long mZooId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo);

        Bundle extras = getIntent().getExtras();
        if (savedInstanceState != null) {
            mZooId = savedInstanceState.getLong(EXTRA_ZOO_ID);
        } else if (extras != null) {
            mZooId = extras.getLong(EXTRA_ZOO_ID);
        } else {
            finish();
            return;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.activity_zoo_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        AnimalAdapter adapter = new AnimalAdapter();
        recyclerView.setAdapter(adapter);

        ZooActivityViewModel viewModel = ViewModelProviders.of(this).get(ZooActivityViewModel.class);
        viewModel.loadZoo(mZooId);
        viewModel.getZoo().observe(this, zoo -> {
            if (zoo != null) {
                setTitle(zoo.getName());
                adapter.update(zoo.animals);
            }
        });

        findViewById(R.id.activity_zoo_fab).setOnClickListener(
                v -> Toast.makeText(this, "Not implemented!", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_ZOO_ID, mZooId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_zoo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_zoo_edit:
                DialogFragment zooFragment = ZooFragment.newInstance(mZooId);
                zooFragment.show(getSupportFragmentManager(), ZooFragment.class.getName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

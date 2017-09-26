package com.piercezaifman.googlearchitectureexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.Repository;

import java.util.List;

import io.objectbox.reactive.DataSubscription;

/**
 * Created by Pierce Zaifman on 2017-09-19.
 */

public class ZooListViewModel extends BaseViewModel {

    private MutableLiveData<List<Zoo>> mZoosLiveData;

    public ZooListViewModel() {
        mZoosLiveData = new MutableLiveData<>();
        DataSubscription subscription = Repository.get().subscribeToZooList(this::updateZoos);
        addSubscription(subscription);
    }

    private void updateZoos(List<Zoo> zoos) {
        mZoosLiveData.setValue(zoos);
    }

    public LiveData<List<Zoo>> getZoos() {
        return mZoosLiveData;
    }

    public void addZoo(Zoo zoo) {
        Repository.get().addZoo(zoo);
    }
}

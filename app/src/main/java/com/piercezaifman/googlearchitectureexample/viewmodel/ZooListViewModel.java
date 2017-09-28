package com.piercezaifman.googlearchitectureexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.ZooRepository;

import java.util.List;

import io.objectbox.reactive.DataSubscription;

/**
 * Created by Pierce Zaifman on 2017-09-19.
 */

public class ZooListViewModel extends BaseViewModel {

    private MutableLiveData<List<Zoo>> mZoosLiveData;

    public ZooListViewModel() {
        mZoosLiveData = new MutableLiveData<>();
        DataSubscription subscription = ZooRepository.subscribeToZooList(this::refreshZoos);
        addSubscription(subscription);
    }

    private void refreshZoos(List<Zoo> zoos) {
        mZoosLiveData.postValue(zoos);
    }

    public LiveData<List<Zoo>> getZoos() {
        return mZoosLiveData;
    }

    public void refreshZoos() {
        ZooRepository.refreshZoos();
    }
}

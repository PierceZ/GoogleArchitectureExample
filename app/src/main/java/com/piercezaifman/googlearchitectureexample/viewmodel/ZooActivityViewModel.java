package com.piercezaifman.googlearchitectureexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.ZooRepository;

import io.objectbox.reactive.DataSubscription;

/**
 * Created by Pierce Zaifman on 2017-09-29.
 */

public class ZooActivityViewModel extends BaseViewModel {

    private MutableLiveData<Zoo> mZooLiveData;

    public ZooActivityViewModel() {
        mZooLiveData = new MutableLiveData<>();
    }

    public void loadZoo(long id) {
        DataSubscription sub = ZooRepository.subscribeToZoo(this::refreshZoo, id, false);
        addSubscription(sub);
        ZooRepository.refreshZoo(id);

    }

    private void refreshZoo(Zoo zoo) {
        mZooLiveData.postValue(zoo);
    }

    public LiveData<Zoo> getZoo() {
        return mZooLiveData;
    }
}

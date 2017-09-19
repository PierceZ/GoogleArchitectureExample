package com.piercezaifman.googlearchitectureexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class ZooListViewModel extends ViewModel {

    private MutableLiveData<List<Zoo>> mZoosLiveData;

    public ZooListViewModel() {
        mZoosLiveData = new MutableLiveData<>();
    }

    public void refresh() {
        Repository.get().loadZoos(mZoosLiveData);
    }

    public LiveData<List<Zoo>> getZoos() {
        return mZoosLiveData;
    }
}

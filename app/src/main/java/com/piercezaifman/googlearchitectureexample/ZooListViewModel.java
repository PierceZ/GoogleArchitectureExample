package com.piercezaifman.googlearchitectureexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class ZooListViewModel extends ViewModel {

    private DisposableLiveData<List<Zoo>> mZoosLiveData;

    public ZooListViewModel() {
        mZoosLiveData = new DisposableLiveData<>();
        Disposable disposable = Repository.get().subscribeToZooList(this::updateZoos);
        mZoosLiveData.setDisposable(disposable);
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

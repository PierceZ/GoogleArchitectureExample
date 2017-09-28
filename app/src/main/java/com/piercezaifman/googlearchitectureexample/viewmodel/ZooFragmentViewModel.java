package com.piercezaifman.googlearchitectureexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.ZooRepository;
import com.piercezaifman.googlearchitectureexample.repository.response.ZooUpdateResponse;

/**
 * Created by Pierce Zaifman on 2017-09-27.
 */

public class ZooFragmentViewModel extends BaseViewModel {

    private MutableLiveData<Zoo> mZooLiveData;
    private MutableLiveData<ZooUpdateResponse> mZooResponseLiveData;

    public ZooFragmentViewModel() {
        mZooLiveData = new MutableLiveData<>();
        mZooResponseLiveData = new MutableLiveData<>();
    }

    public void loadZoo(long id) {
        ZooRepository.subscribeToZoo(this::refreshZoo, id, true);
    }

    private void refreshZoo(Zoo zoo) {
        mZooLiveData.postValue(zoo);
    }

    public LiveData<Zoo> getZoo() {
        return mZooLiveData;
    }

    public LiveData<ZooUpdateResponse> getZooUpdateResponse() {
        return mZooResponseLiveData;
    }

    public void updateZoo(Zoo zoo) {
        ZooRepository.updateZoo(zoo, mZooResponseLiveData);
    }

    public void addZoo(String name) {
        ZooRepository.addZoo(new Zoo(name), mZooResponseLiveData);
    }
}

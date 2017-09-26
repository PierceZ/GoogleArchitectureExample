package com.piercezaifman.googlearchitectureexample.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.reactive.DataSubscription;

/**
 * Created by Pierce Zaifman on 2017-09-26.
 */

public abstract class BaseViewModel extends ViewModel {

    private final List<DataSubscription> mSubscriptions;

    @Override
    protected void onCleared() {
        super.onCleared();
        for (DataSubscription subscription : mSubscriptions) {
            if (!subscription.isCanceled()) {
                subscription.cancel();
            }
        }
    }

    protected final void addSubscription(@NonNull DataSubscription subscription) {
        mSubscriptions.add(subscription);
    }

    public BaseViewModel() {
        mSubscriptions = new ArrayList<>();
    }
}

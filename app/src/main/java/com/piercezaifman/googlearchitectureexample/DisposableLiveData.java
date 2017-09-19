package com.piercezaifman.googlearchitectureexample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class DisposableLiveData<T> extends MutableLiveData<T> {

    private Disposable mDisposable;

    public void setDisposable(@NonNull Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        super.removeObserver(observer);
        if (!hasObservers() && mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}

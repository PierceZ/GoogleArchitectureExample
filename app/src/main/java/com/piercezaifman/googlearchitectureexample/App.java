package com.piercezaifman.googlearchitectureexample;

import android.app.Application;

/**
 * Created by Pierce Zaifman on 2017-09-20.
 */

public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static App getApp() {
        return sApp;
    }
}

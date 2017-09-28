package com.piercezaifman.googlearchitectureexample.repository.response;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Pierce Zaifman on 2017-09-27.
 */
public class Response {

    public static final int STATUS_LOADING = 0, STATUS_SUCCESS = 1, STATUS_FAIL = 2;

    @Retention(SOURCE)
    @IntDef({STATUS_LOADING, STATUS_SUCCESS, STATUS_FAIL})
    @interface Status {
    }

    private final int mStatus;
    private String mPayload;

    public Response(@Status int status, String payload) {
        mStatus = status;
        mPayload = payload;
    }

    @Status
    public int getStatus() {
        return mStatus;
    }

    public String getPayload() {
        return mPayload;
    }
}

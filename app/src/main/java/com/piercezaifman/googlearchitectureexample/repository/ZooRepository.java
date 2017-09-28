package com.piercezaifman.googlearchitectureexample.repository;

import android.arch.lifecycle.MutableLiveData;

import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.api.ZooAPI;
import com.piercezaifman.googlearchitectureexample.repository.database.ZooDAO;
import com.piercezaifman.googlearchitectureexample.repository.parser.ZooParser;
import com.piercezaifman.googlearchitectureexample.repository.response.Response;
import com.piercezaifman.googlearchitectureexample.repository.response.ZooUpdateResponse;

import java.util.List;

import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;

/**
 * Created by Pierce Zaifman on 2017-09-19.
 */
public class ZooRepository {

    public static DataSubscription subscribeToZooList(DataObserver<List<Zoo>> observer) {
        return ZooDAO.subscribeToZooList(observer);
    }

    public static DataSubscription subscribeToZoo(DataObserver<Zoo> observer, long id, boolean singleUpdate) {
        return ZooDAO.subscribeToZoo(observer, id, singleUpdate);
    }

    public static void refreshZoos() {
        ZooAPI.loadZoos(zoosResponse -> {
            if (zoosResponse != null && zoosResponse.getStatus() == Response.STATUS_SUCCESS) {
                ZooParser parser = new ZooParser(zoosResponse.getPayload());
                parser.parseZooList();
                List<Zoo> zoos = parser.getZooList();
                if (zoos != null) {
                    ZooDAO.insertZoos(zoos);
                }
            }
        });
    }

    public static void addZoo(Zoo newZoo, MutableLiveData<ZooUpdateResponse> liveResponse) {
        liveResponse.postValue(new ZooUpdateResponse(Response.STATUS_LOADING));
        ZooAPI.addZoo(newZoo, zooResponse -> handleZooResponse(zooResponse, liveResponse));
    }

    public static void updateZoo(Zoo zoo, MutableLiveData<ZooUpdateResponse> liveResponse) {
        liveResponse.postValue(new ZooUpdateResponse(Response.STATUS_LOADING));
        ZooAPI.updateZoo(zoo, zooResponse -> handleZooResponse(zooResponse, liveResponse));
    }

    private static void handleZooResponse(Response zooResponse, MutableLiveData<ZooUpdateResponse> liveResponse) {
        if (zooResponse != null) {
            if (zooResponse.getStatus() == Response.STATUS_SUCCESS) {
                ZooParser parser = new ZooParser(zooResponse.getPayload());
                parser.parseZoo();
                Zoo zoo = parser.getZoo();
                if (zoo != null) {
                    ZooDAO.insertZoo(zoo);
                }
            }

            liveResponse.postValue(new ZooUpdateResponse(zooResponse.getStatus()));
        }
    }
}

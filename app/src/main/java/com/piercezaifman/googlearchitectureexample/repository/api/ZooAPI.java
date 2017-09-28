package com.piercezaifman.googlearchitectureexample.repository.api;

import android.arch.lifecycle.Observer;
import android.os.AsyncTask;

import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.repository.response.Response;

/**
 * Created by Pierce Zaifman on 2017-09-27.
 */

public class ZooAPI {

    public static void addZoo(Zoo newZoo, Observer<Response> responseObserver) {
        // Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            // Fake JSON response, it should come from the server
            String fakeResponse = "{\"name\": \"" + newZoo.getName() + "\"}";
            Response response = new Response(Response.STATUS_SUCCESS, fakeResponse);
            responseObserver.onChanged(response);
        });
    }

    public static void updateZoo(Zoo zoo, Observer<Response> responseObserver) {
        // Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            // Fake JSON response, it should come from the server
            String fakeResponse = "{\"name\": \"" + zoo.getName() + "\", \"id\": \"" + zoo.getId() + "\"}";
            Response response = new Response(Response.STATUS_SUCCESS, fakeResponse);
            responseObserver.onChanged(response);
        });
    }

    public static void loadZoos(Observer<Response> responseObserver) {
        //Fake network request
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            // Fake response, it should come from the server
            Response response = new Response(Response.STATUS_FAIL, "Error occurred!");
            responseObserver.onChanged(response);
        });
    }
}

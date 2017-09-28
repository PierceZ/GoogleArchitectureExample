package com.piercezaifman.googlearchitectureexample.repository.database;

import com.piercezaifman.googlearchitectureexample.App;
import com.piercezaifman.googlearchitectureexample.model.Zoo;
import com.piercezaifman.googlearchitectureexample.model.Zoo_;

import java.util.Collection;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;
import io.objectbox.reactive.SubscriptionBuilder;

/**
 * Created by Pierce Zaifman on 2017-09-28.
 */

public class ZooDAO {

    private static Box<Zoo> getZooBox() {
        BoxStore boxStore = App.getBoxStore();
        return boxStore.boxFor(Zoo.class);
    }

    public static DataSubscription subscribeToZooList(DataObserver<List<Zoo>> observer) {
        return getZooBox().query().build().subscribe().on(AndroidScheduler.mainThread()).observer(observer);
    }

    public static DataSubscription subscribeToZoo(DataObserver<Zoo> observer, long id, boolean singleUpdate) {
        SubscriptionBuilder<Zoo> builder = getZooBox().query().equal(Zoo_.id, id).build().subscribe().transform(list -> {
            if (list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }
        }).on(AndroidScheduler.mainThread());

        if (singleUpdate) {
            builder.single();
        }
        return builder.observer(observer);
    }

    public static void insertZoo(Zoo zoo) {
        getZooBox().put(zoo);
    }

    public static void insertZoos(Collection<Zoo> zoos) {
        getZooBox().put(zoos);
    }
}

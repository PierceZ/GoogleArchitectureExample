package com.piercezaifman.googlearchitectureexample;

import android.app.Application;

import com.piercezaifman.googlearchitectureexample.model.Animal;
import com.piercezaifman.googlearchitectureexample.model.MyObjectBox;
import com.piercezaifman.googlearchitectureexample.model.Zoo;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by Pierce Zaifman on 2017-09-20.
 */

public class App extends Application {

    private static App sApp;
    private static BoxStore sBoxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        sBoxStore = MyObjectBox.builder().androidContext(App.getApp()).build();

        initDatabase();
    }

    public static App getApp() {
        return sApp;
    }

    public static BoxStore getBoxStore() {
        return sBoxStore;
    }

    /**
     * Setup the database with mock data. This is just an example App, this wouldn't be included in a real app.
     */
    private void initDatabase() {
        Box<Zoo> zooBox = App.getBoxStore().boxFor(Zoo.class);

        // add some hard-coded default data if there is none
        if (zooBox.count() == 0) {
            List<Animal> canadaAnimals = new ArrayList<>();
            canadaAnimals.add(new Animal("Dog", "https://cdn.pixabay.com/photo/2016/10/15/12/01/dog-1742295_960_720.jpg", "Mammal"));
            canadaAnimals.add(new Animal("Beaver", "https://cdn.pixabay.com/photo/2017/01/21/14/41/beaver-1997344_960_720.jpg", "Mammal"));
            canadaAnimals.add(new Animal("Blue jay", "https://cdn.pixabay.com/photo/2017/02/05/04/19/blue-jay-2039121_960_720.jpg", "Bird"));
            Zoo canadaZoo = new Zoo("Canada Zoo");
            canadaZoo.animals.addAll(canadaAnimals);


            List<Animal> australiaAnimals = new ArrayList<>();
            australiaAnimals.add(new Animal("Kangaroo", "https://cdn.pixabay.com/photo/2016/11/22/19/19/animal-1850146_960_720.jpg", "Mammal"));
            australiaAnimals.add(new Animal("Koala", "https://cdn.pixabay.com/photo/2016/11/18/16/33/animal-1835689_960_720.jpg", "Mammal"));
            australiaAnimals.add(new Animal("Kookaburra", "https://cdn.pixabay.com/photo/2017/04/21/18/44/kookaburra-2249570_960_720.jpg", "Bird"));
            Zoo australiaZoo = new Zoo("Australia Zoo");
            australiaZoo.animals.addAll(australiaAnimals);


            List<Animal> brazilAnimals = new ArrayList<>();
            brazilAnimals.add(new Animal("Poison dart frog", "https://cdn.pixabay.com/photo/2016/12/20/16/50/poison-1920980_960_720.jpg", "Amphibian"));
            brazilAnimals.add(new Animal("Jaguar", "https://cdn.pixabay.com/photo/2012/12/14/15/23/jaguar-70026_960_720.jpg", "Mammal"));
            brazilAnimals.add(new Animal("Toucan", "https://cdn.pixabay.com/photo/2013/11/24/01/33/animal-216640_960_720.jpg", "Bird"));
            Zoo brazilZoo = new Zoo("Brazil Zoo");
            brazilZoo.animals.addAll(brazilAnimals);

            zooBox.put(canadaZoo, australiaZoo, brazilZoo);
        }
    }
}

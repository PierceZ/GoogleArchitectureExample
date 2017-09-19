package com.piercezaifman.googlearchitectureexample;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class Repository {

    private static Repository sRepository;

    private List<Zoo> mZoos = new ArrayList<>();

    public static Repository get() {
        if (sRepository == null) {
            sRepository = new Repository();
        }

        return sRepository;
    }

    /**
     * Setup hard-coded data.
     */
    public Repository() {
        List<Animal> canadaAnimals = new ArrayList<>();
        canadaAnimals.add(new Animal("Dog", "https://cdn.pixabay.com/photo/2016/10/15/12/01/dog-1742295_960_720.jpg", "Mammal"));
        canadaAnimals.add(new Animal("Beaver", "https://cdn.pixabay.com/photo/2017/01/21/14/41/beaver-1997344_960_720.jpg", "Mammal"));
        canadaAnimals.add(new Animal("Blue jay", "https://cdn.pixabay.com/photo/2017/02/05/04/19/blue-jay-2039121_960_720.jpg", "Bird"));
        Zoo canadaZoo = new Zoo("Canada Zoo", canadaAnimals);


        List<Animal> australiaAnimals = new ArrayList<>();
        australiaAnimals.add(new Animal("Kangaroo", "https://cdn.pixabay.com/photo/2016/11/22/19/19/animal-1850146_960_720.jpg", "Mammal"));
        australiaAnimals.add(new Animal("Koala", "https://cdn.pixabay.com/photo/2016/11/18/16/33/animal-1835689_960_720.jpg", "Mammal"));
        australiaAnimals.add(new Animal("Kookaburra", "https://cdn.pixabay.com/photo/2017/04/21/18/44/kookaburra-2249570_960_720.jpg", "Bird"));
        Zoo australiaZoo = new Zoo("Australia Zoo", australiaAnimals);


        List<Animal> brazilAnimals = new ArrayList<>();
        brazilAnimals.add(new Animal("Poison dart frog", "https://cdn.pixabay.com/photo/2016/12/20/16/50/poison-1920980_960_720.jpg", "Amphibian"));
        brazilAnimals.add(new Animal("Jaguar", "https://cdn.pixabay.com/photo/2012/12/14/15/23/jaguar-70026_960_720.jpg", "Mammal"));
        brazilAnimals.add(new Animal("Toucan", "https://cdn.pixabay.com/photo/2013/11/24/01/33/animal-216640_960_720.jpg", "Bird"));
        Zoo brazilZoo = new Zoo("Brazil Zoo", brazilAnimals);


        mZoos.add(canadaZoo);
        mZoos.add(australiaZoo);
        mZoos.add(brazilZoo);
    }

    /**
     * Trigger loading of Zoos.
     * NOTE: This would be where I could trigger a database load and a server request. To keep it simple I'm just loading
     * some hard-coded data.
     */
    public void loadZoos(MutableLiveData<List<Zoo>> liveData) {
        liveData.setValue(mZoos);
    }

    public void addZoo(Zoo newZoo) {
        mZoos.add(newZoo);
    }

}

package com.piercezaifman.googlearchitectureexample;

import java.util.List;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class Zoo {

    private String name;
    private List<Animal> animals;

    public Zoo(String name, List<Animal> animals) {
        this.name = name;
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}

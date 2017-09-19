package com.piercezaifman.googlearchitectureexample;

/**
 * Created by piercezaifman on 2017-09-19.
 */

public class Animal {

    private String name;
    private String image;
    private String group;

    public Animal(String name, String image, String group) {
        this.name = name;
        this.image = image;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

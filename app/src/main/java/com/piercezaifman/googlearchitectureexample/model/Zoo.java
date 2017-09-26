package com.piercezaifman.googlearchitectureexample.model;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Pierce Zaifman on 2017-09-19.
 */
@Entity
public class Zoo {

    @Id
    private long id;
    private String name;

    @Backlink
    public ToMany<Animal> animals;

    public Zoo() {
    }

    public Zoo(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

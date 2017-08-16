package com.example.muzna.universitysystem;

import java.io.Serializable;

/**
 * Created by muzna on 8/15/2017.
 */

class University implements Serializable{
    String name;String id;
    public University(String name,String id) {
        this.name=name;
        this.id=id;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

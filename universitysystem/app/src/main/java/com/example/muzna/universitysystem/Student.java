package com.example.muzna.universitysystem;

/**
 * Created by muzna on 8/15/2017.
 */

class Student {
    String name;String id;
    public Student(String name,String id) {
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

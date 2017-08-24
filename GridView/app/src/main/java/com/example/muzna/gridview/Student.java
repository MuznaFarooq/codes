package com.example.muzna.gridview;

/**
 * Created by muzna on 8/11/2017.
 */

class Student {
    String name,school1,id;
    public Student(String id,String name,String school) {
        this.id=id;
        this.name=name;
        this.school1=school;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school1;
    }

    public String getId() {
        return id;
    }
}

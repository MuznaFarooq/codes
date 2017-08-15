package com.example.muzna.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muzna on 8/2/2017.
 */

public  class StudentClass implements Parcelable{
    String name,email;

    public StudentClass(String email, String name) {
        this.email = email;
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail() {return email; }
    public int describeContents()
    {
        return 0; //describe the kinds of special objects
    }
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(email);
    }
    public static final Parcelable.Creator<StudentClass> CREATOR= new Parcelable.Creator<StudentClass>() {
        @Override
        public StudentClass createFromParcel(Parcel source) {
            return new StudentClass(source);
        }

        @Override
        public StudentClass[] newArray(int size) {
            return new StudentClass[size];
        }
    };
    public StudentClass(Parcel source)
    {
        name=source.readString();
        email=source.readString();
    }

}

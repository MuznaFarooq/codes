package com.example.muzna.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by muzna on 8/1/2017.
 */

public class Studentclass implements Parcelable{
    String name,email;
     public Studentclass(String name,String email)
    {
        this.name=name;
        this.email=email;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public int describeContents()
    {
        return 0; //describe the kinds of special objects
    }
    public void writeToParcel(Parcel dest,int flags)
    {
        dest.writeString(name);
        dest.writeString(email);
    }
    public static final Creator<Studentclass>CREATOR= new Creator<Studentclass>() {
        @Override
        public Studentclass createFromParcel(Parcel source) {
            return new Studentclass(source);
        }

        @Override
        public Studentclass[] newArray(int size) {
            return new Studentclass[size];
        }
    };
    public Studentclass(Parcel source)
    {
        name=source.readString();
        email=source.readString();
    }

}

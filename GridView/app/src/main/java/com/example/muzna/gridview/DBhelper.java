package com.example.muzna.gridview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by muzna on 8/10/2017.
 */

public class DBhelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="dbstudent";
    public static final String TABLE_NAME="student";
    public static final int DATABASE_VERSION=1;

    public static final String COLUMN_1="id";
    public static final String COLUMN_2 = "name";
    public static final String COLUMN_3="school";

    public DBhelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_2 + " TEXT," + COLUMN_3 + " TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply discard the data and start over
        String DELETE_ENTRIES="DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean add(String name,String school){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_2,name);
        contentValues.put(COLUMN_3,school);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean delete(int id) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            String idstring = String.valueOf(id);
            int a = DB.delete(TABLE_NAME, " " + COLUMN_1 + " = ? ", new String[]{idstring});
            DB.close();
            if (a == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Cursor getlist()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}

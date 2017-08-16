package com.example.muzna.universitysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by muzna on 8/15/2017.
 */

class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbuniversity";
    public static final String TABLE_NAME = "university";
    public static final String TABLE_NAME2 = "student";
    public static final int DATABASE_VERSION = 1;

    public static final String UNICOLUMN_1 = "id";
    public static final String UNICOLUMN_2 = "uniname";

    public static final String STDCOLUMN_1 = "stdId";
    public static final String STDCOLUMN_2 = "stdname";
    public static final String STDCOLUMN_3 = "uni_id";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = " CREATE TABLE " + TABLE_NAME + "(" + UNICOLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + UNICOLUMN_2 + " TEXT" + ")";
        String CREATE_CONTACTS_TABLE2 = " CREATE TABLE " + TABLE_NAME2 + "(" + STDCOLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + STDCOLUMN_2 + " TEXT," + STDCOLUMN_3 +" INTEGER)";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE2);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply discard the data and start over
        String DELETE_ENTRIES = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean uniadd(String uniname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UNICOLUMN_2, uniname);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean unidelete(int id) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            String idstring = String.valueOf(id);
            int a = DB.delete(TABLE_NAME, " " + UNICOLUMN_1 + " = ? ", new String[]{idstring});
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

    public Cursor getlist() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public boolean stdadd(String studentname,String uni_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(STDCOLUMN_2, studentname);
            contentValues.put(STDCOLUMN_3,uni_id);

            long result = db.insert(TABLE_NAME2, null, contentValues);
            db.close();

            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean stddelete(int get_id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String getStudyBriefIdInString = String.valueOf(get_id);
            int i = db.delete(TABLE_NAME2, " " + STDCOLUMN_1 + " = ? ", new String[]{getStudyBriefIdInString});
            db.close();

            if (i == -1) {
                return false;
            } else {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<University> getAllUni() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<University> UniList = new ArrayList<>();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        data.moveToFirst();
        if (data.moveToFirst()) {
            String UniId = data.getString(0);
            String UniName = data.getString(1);
            University uni = new University(UniName, UniId);
            UniList.add(uni);
        }
            while (data.moveToNext()) {
                String id = data.getString(0);
                String name = data.getString(1);
                University mUni = new University(name, id);
                UniList.add(mUni);
            }
        return UniList;
    }
    public ArrayList<Student> getAllStudents(String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Student> StdList = new ArrayList<>();
        String strSQL = "select * from student where uni_id = ?";
        Cursor data = db.rawQuery(strSQL, new String[]{Id});
        data.moveToFirst();
        if (data.moveToFirst()) {
            String StdId = data.getString(0);
            String StdName = data.getString(1);
            Student student = new Student(StdName, StdId);
            StdList.add(student);
        }
        while (data.moveToNext()) {
            String id = data.getString(0);
            String name = data.getString(1);
            Student std = new Student(name, id);
            StdList.add(std);
        }
            return StdList;
    }
}
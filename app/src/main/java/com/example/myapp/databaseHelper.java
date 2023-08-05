package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public databaseHelper(@Nullable Context context ) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table allusers(email TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop Table if exists allusers");
    }

    public Boolean insertData(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result =db.insert("allusers",null,contentValues);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from allusers where email = ?",new String[]{email});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }
    public Boolean checkEmailPassword(String email,String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery("Select * from allusers where email = ? and password = ?",new String[]{email,password});

        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}

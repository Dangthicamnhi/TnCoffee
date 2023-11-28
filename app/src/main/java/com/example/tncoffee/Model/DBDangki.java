package com.example.tncoffee.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBDangki extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "UserInfo.db";
    private static final int DATABASE_VERSION = 1;

    public DBDangki (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE UserInfo (" +
                "username TEXT PRIMARY KEY," +
                "password TEXT," +
                "fullName TEXT," +
                "birthDate TEXT," +
                "gender TEXT)");
    }
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserInfo WHERE username=? AND password=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return exists;
    }
    public void addUser(String username, String password, String fullName, String birthDate, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("fullName", fullName);
        values.put("birthDate", birthDate);
        values.put("gender", gender);
        db.insert("UserInfo", null, values);
        db.close();
    }
    public void deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("UserInfo", "username = ?", new String[]{username});
        db.close();
    }
    public void updateUser(String username, String password, String fullName, String birthDate, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        values.put("fullName", fullName);
        values.put("birthDate", birthDate);
        values.put("gender", gender);

        db.update("UserInfo", values, "username = ?", new String[]{username});
        db.close();
    }
    public List<String> getAllSignUpData() {
        List<String> signUpDataList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM UserInfo", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int columnIndexUsername = cursor.getColumnIndex("username");
                    int columnIndexPassword = cursor.getColumnIndex("password");
                    int columnIndexFullName = cursor.getColumnIndex("fullName");
                    int columnIndexBirthDate = cursor.getColumnIndex("birthDate");
                    int columnIndexGender = cursor.getColumnIndex("gender");

                    if (columnIndexUsername != -1 && columnIndexPassword != -1 && columnIndexFullName != -1 &&
                            columnIndexBirthDate != -1 && columnIndexGender != -1) {
                        String username = cursor.getString(columnIndexUsername);
                        String password = cursor.getString(columnIndexPassword);
                        String fullName = cursor.getString(columnIndexFullName);
                        String birthDate = cursor.getString(columnIndexBirthDate);
                        String gender = cursor.getString(columnIndexGender);

                        String userData = "Username: " + username +
                                ", Password: " + password +
                                ", Full Name: " + fullName +
                                ", Birth Date: " + birthDate +
                                ", Gender: " + gender;
                        signUpDataList.add(userData);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return signUpDataList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserInfo");
        onCreate(db);
    }
}

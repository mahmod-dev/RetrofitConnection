package com.mahmouddev.retrofitconnection.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mahmouddev.retrofitconnection.models.Users;

import java.util.ArrayList;

import static com.mahmouddev.retrofitconnection.models.Users.COL_USERNAME;
import static com.mahmouddev.retrofitconnection.models.Users.TABLE_NAME;


public class DbHelper extends SQLiteOpenHelper {
    private final SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, "UsersDB", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Users.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insert(String username, String email, String password,String mobile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(Users.COL_EMAIL, email);
        contentValues.put(Users.COL_PASSWORD, password);
        contentValues.put(Users.COL_PHONE, mobile);
        contentValues.put(Users.COL_IS_HIDDEN, 0);


        return db.insert(TABLE_NAME, null, contentValues) > 0;
    }

    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> data = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + Users.COL_ID + " DESC";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();
                users.setId(cursor.getInt(cursor.getColumnIndex(Users.COL_ID)));
                users.setUsername(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
                users.setEmail(cursor.getString(cursor.getColumnIndex(Users.COL_EMAIL)));
                users.setPassword(cursor.getString(cursor.getColumnIndex(Users.COL_PASSWORD)));
                data.add(users);

            } while (cursor.moveToNext());

        }
        cursor.close();
        return data;
    }

    public ArrayList<Users> getUser(String username, String password) {
        ArrayList<Users> data = new ArrayList<>();

        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL_USERNAME + " = '" + username + "'" + " AND " + Users.COL_PASSWORD + " = '" + password + "'";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();
                users.setId(cursor.getInt(cursor.getColumnIndex(Users.COL_ID)));
                users.setUsername(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
                users.setEmail(cursor.getString(cursor.getColumnIndex(Users.COL_EMAIL)));
                users.setPhone(cursor.getString(cursor.getColumnIndex(Users.COL_PHONE)));
                users.setPassword(cursor.getString(cursor.getColumnIndex(Users.COL_PASSWORD)));
                data.add(users);
            } while (cursor.moveToNext());

        }
        cursor.close();
        return data;
    }

    public boolean delete(int id) {
        String[] args = {String.valueOf(id)};
        return db.delete(TABLE_NAME, Users.COL_ID + " =? ", args) > 0;
    }

    public boolean update(int id, String username, String email, String password, int isHidden) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Users.COL_USERNAME, username);
        contentValues.put(Users.COL_EMAIL, email);
        contentValues.put(Users.COL_PASSWORD, password);
        contentValues.put(Users.COL_IS_HIDDEN, isHidden);
        String[] args = {String.valueOf(id)};
        return db.update(TABLE_NAME, contentValues, Users.COL_ID + " =? ", args) > 0;
    }

    public boolean deleteAll() {
        return db.delete(TABLE_NAME, null, null) > 0;
    }


}

package com.mahmouddev.retrofitconnection.models;

import java.io.Serializable;

public class Users implements Serializable {


    public static final String TABLE_NAME = "Users";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "username";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String COL_IS_HIDDEN = "isHidden";
    public static final String COL_PHONE = "phone";
    // is hidden 0 shown , 1 hidden


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_USERNAME + " TEXT, " + COL_EMAIL + " TEXT, " +
            COL_PHONE + " TEXT, "+
            COL_IS_HIDDEN+" INTEGER, "+ COL_PASSWORD + " TEXT)";

    private int id;
    private String username;
    private String email;
    private String password;
    private int isHidden;
    private String phone;

    public Users() {
    }

    public Users(String username, String email, String password, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }
}

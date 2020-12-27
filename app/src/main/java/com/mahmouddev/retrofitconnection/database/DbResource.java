package com.mahmouddev.retrofitconnection.database;

import com.mahmouddev.retrofitconnection.models.Users;
import com.mahmouddev.retrofitconnection.util.Helper;

public class DbResource {


    public void validate(Users users) throws Exception {
        Helper.isValidPassword(users.getPassword());
        Helper.isEmailValid(users.getEmail());
        Helper.isMobileValid(users.getPhone());
    }
}

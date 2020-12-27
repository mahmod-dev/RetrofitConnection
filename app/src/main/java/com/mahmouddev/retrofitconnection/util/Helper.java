package com.mahmouddev.retrofitconnection.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static final String ERR_LENGTH = "Password length must have at least 6 character !!";
    public static final String ERR_SPECIAL_CHAR = "Password must have at least one special character !!";
    public static final String ERR_UPPER_CASE_CHAR = "Password must have at least one uppercase character !!";
    public static final String ERR_LOW_CASE_CHAR = "Password must have at least one lowercase character !!";
    public static final String ERR_DIGIT = "Password must have at least one digit character !!";
    public static final String ERR_EMAIL = "Invalid email !!";
    public static final String ERR_MOBILE = "Invalid mobile !!";


    public static void isValidPassword(String password) throws Exception {

        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");


        if (password.length() < 6) {
            throw new Exception(ERR_LENGTH);
        }
        if (!specialCharPatten.matcher(password).find()) {
            throw new Exception(ERR_SPECIAL_CHAR);
        }
        if (!UpperCasePatten.matcher(password).find()) {
            throw new Exception(ERR_UPPER_CASE_CHAR);
        }
        if (!lowerCasePatten.matcher(password).find()) {
            throw new Exception(ERR_LOW_CASE_CHAR);
        }
        if (!digitCasePatten.matcher(password).find()) {
            throw new Exception(ERR_DIGIT);
        }


    }


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_MOBILE_NUMBER_REGEX =
            Pattern.compile("[0-9 ]");

    public static void isEmailValid(String emailStr) throws Exception {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        if (!matcher.find()) {
            throw new Exception(ERR_EMAIL);
        }
    }

    public static void isMobileValid(String emailStr) throws Exception {
        Matcher matcher = VALID_MOBILE_NUMBER_REGEX.matcher(emailStr);
        if (!matcher.find()) {
            throw new Exception(ERR_MOBILE);
        }
    }

}

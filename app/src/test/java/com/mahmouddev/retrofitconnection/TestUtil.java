package com.mahmouddev.retrofitconnection;

import com.mahmouddev.retrofitconnection.models.Users;

public class TestUtil {
    public static final Users invalidUserEmail =
            new Users("mahmoud", "gmail", "123@@@GGh", "0597987977");

    public static final Users invalidUserPasswordLength =
            new Users("mahmoud", "mahmoud@gmail.com", "1@Gh", "0597987977");

    public static final Users invalidUserPasswordUpperCase =
            new Users("mahmoud", "mahmoud@gmail.com", "123@@@gggh", "0597987977");

    public static final Users invalidUserPasswordLowerCase =
            new Users("mahmoud", "mahmoud@gmail.com", "123@@@GGGH", "0597987977");

    public static final Users invalidUserPasswordDigit =
            new Users("mahmoud", "mahmoud@gmail.com", "werqweqw@@@GGh", "0597987977");

    public static final Users invalidUserPasswordSpecialChar =
            new Users("mahmoud", "mahmoud@gmail.com", "123GGhhh", "0597987977");

    public static final Users invalidUserMobile =
            new Users("mahmoud", "mahmoud@gmail.com", "123@@@GGh", "mobile");

    public static final Users validUser =
            new Users("mahmoud", "mahmoud@gmail.com", "123@@@GGh", "0597796100");
}

package com.mahmouddev.retrofitconnection;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.mahmouddev.retrofitconnection.database.DbHelper;
import com.mahmouddev.retrofitconnection.database.DbResource;
import com.mahmouddev.retrofitconnection.models.Users;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.CountDownLatch;

import static com.mahmouddev.retrofitconnection.util.Helper.ERR_DIGIT;
import static com.mahmouddev.retrofitconnection.util.Helper.ERR_EMAIL;
import static com.mahmouddev.retrofitconnection.util.Helper.ERR_LENGTH;
import static com.mahmouddev.retrofitconnection.util.Helper.ERR_LOW_CASE_CHAR;
import static com.mahmouddev.retrofitconnection.util.Helper.ERR_MOBILE;
import static com.mahmouddev.retrofitconnection.util.Helper.ERR_SPECIAL_CHAR;
import static com.mahmouddev.retrofitconnection.util.Helper.ERR_UPPER_CASE_CHAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class LoginTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    CountDownLatch latch = new CountDownLatch(1);
    DbHelper dbHelper;
    DbResource dbResource;


    @Before
    public void beforeTest() {
        dbHelper = new DbHelper(appContext);
        dbResource = new DbResource();
    }

    @Test
    public void test_insert() {
        // dbHelper.insert("mahmoud", "mahmoud@gmail.com", "123456", "0597796100");
    }

    @Test
    public void insert_invalidEmail() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserEmail;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_EMAIL, exception.getMessage());
    }

    @Test
    public void insert_invalidMobile() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserMobile;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_MOBILE, exception.getMessage());
    }


    @Test
    public void insert_invalidPassword_length() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserPasswordLength;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_LENGTH, exception.getMessage());
    }

    @Test
    public void insert_invalidPassword_upper() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserPasswordUpperCase;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_UPPER_CASE_CHAR, exception.getMessage());
    }

    @Test
    public void insert_invalidPassword_lower() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserPasswordLowerCase;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_LOW_CASE_CHAR, exception.getMessage());
    }

    @Test
    public void insert_invalidPassword_digit() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserPasswordDigit;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_DIGIT, exception.getMessage());
    }

    @Test
    public void insert_invalidPassword_special() throws Exception {

        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Users users = TestUtil.invalidUserPasswordSpecialChar;
                dbResource.validate(users);
                dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone());

            }
        });

        assertEquals(ERR_SPECIAL_CHAR, exception.getMessage());
    }

    @Test
    public void insert_valid() throws Exception {

        Users users = TestUtil.validUser;
        dbResource.validate(users);
        assertTrue(dbHelper.insert(users.getUsername(), users.getPassword(), users.getEmail(), users.getPhone()));
        assertEquals("mahmoud", users.getUsername());
        assertEquals("mahmoud@gmail.com", users.getEmail());
        assertEquals("123@@@GGh", users.getPassword());
        assertEquals("0597796100", users.getPhone());

    }


}

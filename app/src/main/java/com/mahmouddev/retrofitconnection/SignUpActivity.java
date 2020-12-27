package com.mahmouddev.retrofitconnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.mahmouddev.retrofitconnection.database.DbHelper;
import com.mahmouddev.retrofitconnection.database.DbResource;
import com.mahmouddev.retrofitconnection.fragment.HomeFragment;
import com.mahmouddev.retrofitconnection.fragment.ProfileFragment;
import com.mahmouddev.retrofitconnection.models.Users;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        DbHelper dbHelper = new DbHelper(this);
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etMobile = findViewById(R.id.etMobile);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView btnLogin = findViewById(R.id.btnLogin);
        FirebaseApp.initializeApp(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnRegister.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String mobile = etMobile.getText().toString();
            if (email == null) {
                etEmail.setError("Empty Email!");
                FirebaseCrashlytics.getInstance().setCustomKey("email", "Empty Email!");
                return;

            }

            if (mobile == null) {
                etMobile.setError("Empty Mobile!");
                FirebaseCrashlytics.getInstance().setCustomKey("Mobile", "Empty Mobile!");
                return;

            }

            if (username == null) {
                etUsername.setError("Empty Username!");
                FirebaseCrashlytics.getInstance().setCustomKey("Username", "Empty Username!");

                return;

            }
            if (password == null) {
                FirebaseCrashlytics.getInstance().setCustomKey("Password", "Password Username!");
                etPassword.setError("Empty Password!");
                return;

            }

            Users users = new Users();
            users.setPhone(mobile);
            users.setEmail(email);
            users.setPassword(password);
            users.setUsername(username);
            try {
                DbResource.validate(users);
                if (dbHelper.insert(users.getUsername(), users.getEmail(), users.getPassword(), users.getPhone())) {
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "sign up successfully, please login", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                FirebaseCrashlytics.getInstance().recordException(e);
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });


    }

}
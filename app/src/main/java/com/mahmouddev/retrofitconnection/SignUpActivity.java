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
import com.mahmouddev.retrofitconnection.database.DbHelper;
import com.mahmouddev.retrofitconnection.fragment.HomeFragment;
import com.mahmouddev.retrofitconnection.fragment.ProfileFragment;

public class SignUpActivity extends AppCompatActivity {

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
        btnRegister.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String mobile = etMobile.getText().toString();
            if (email == null) {
                etEmail.setError("Empty Email!");
                return;

            }

            if (mobile == null) {
                etMobile.setError("Empty Mobile!");
                return;

            }

            if (username == null) {
                etUsername.setError("Empty Username!");
                return;

            }
            if (password == null) {
                etPassword.setError("Empty Password!");
                return;

            }
            if (dbHelper.insert(username, email, password,mobile)){
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "sign up successfully, please login", Toast.LENGTH_SHORT).show();
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
package com.mahmouddev.retrofitconnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.mahmouddev.retrofitconnection.database.DbHelper;
import com.mahmouddev.retrofitconnection.models.Users;
import com.mahmouddev.retrofitconnection.util.MyPreferences;

import java.util.ArrayList;


public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FirebaseApp.initializeApp(this);
        MyPreferences.setContext(getApplicationContext());
        if (MyPreferences.getBool("isRem")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        TextView btnSignUp = findViewById(R.id.btnSignUp);
        RadioButton rb = findViewById(R.id.rb);
        DbHelper dbHelper = new DbHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                ArrayList<Users> data = dbHelper.getUser(username, password);

                if (data.size() > 0) {
                    if (data.get(0).getUsername() != null && data.get(0).getPassword() != null) {
                        MyPreferences.setStr("name",data.get(0).getUsername());
                        MyPreferences.setStr("mobile",data.get(0).getPhone());
                        MyPreferences.setStr("email",data.get(0).getEmail());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        FirebaseCrashlytics.getInstance().log("Dont find user");

                        Toast.makeText(SignInActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    FirebaseCrashlytics.getInstance().log("doesnt exsits Please Sign up ");
                    Toast.makeText(SignInActivity.this, "doesnt exsits Please Sign up ", Toast.LENGTH_SHORT).show();

                }


            }
        });

        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    MyPreferences.setBool("isRem",true);
                }
            }
        });

    }

}
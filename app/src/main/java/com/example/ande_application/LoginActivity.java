package com.example.ande_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void login(View view) {
       startActivity(new Intent(LoginActivity.this , MainActivity.class));
    }

    public void signup(View view) {
        startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
    }

}

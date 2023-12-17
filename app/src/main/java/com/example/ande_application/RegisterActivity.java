package com.example.ande_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    public void signup(View view) {
        startActivity(new Intent(RegisterActivity.this , MainActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
    }
}

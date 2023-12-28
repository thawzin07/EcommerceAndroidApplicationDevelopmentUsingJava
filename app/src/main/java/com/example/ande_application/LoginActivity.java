package com.example.ande_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, pwd ;
    private FirebaseAuth auth;

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

package com.example.ande_application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email, pwd;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // Get FirebaseAuth instance
        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
    }

    public void login(View view) {
        String userEmail = email.getText().toString();
        String userpwd = pwd.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpwd)) {
            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userpwd.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 digits!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(userEmail, userpwd)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            String userId = firebaseUser.getUid();

                            Toast.makeText(LoginActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            // Initialize session manager
                            SessionManager sessionManager = new SessionManager(getApplicationContext());

                            // Save user ID when logged in
                            sessionManager.saveUserId(userId);

                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Log In Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signup(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

}

package com.example.ande_application;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, phone, pwd, rpwd;
    private FirebaseAuth auth;
    private FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // Get FirebaseAuth instance
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance() ;

        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        rpwd = findViewById(R.id.rpwd);
    }

    public void signup(View view) {

        String userName = username.getText().toString();
        String userEmail = email.getText().toString();
        String userPhone = phone.getText().toString();
        String userpwd = pwd.getText().toString();
        String userrpwd = rpwd.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter Email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPhone)) {
            Toast.makeText(this, "Enter Phone!", Toast.LENGTH_SHORT).show();
            return;
        } //need to add some more validation, currently happy flow

        if (TextUtils.isEmpty(userpwd)) {
            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userpwd.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 digits!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userrpwd)) {
            Toast.makeText(this, "Enter Password again!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!userpwd.equals(userrpwd)) {
            Toast.makeText(this, "Enter Same passwords!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail, userpwd)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            //create user object to store in firestore
                            User user = new User();
                            user.setUsername(userName);
                            user.setUseremail(userEmail);
                            user.setUserphone(Integer.parseInt(userPhone));

                            db.collection("users")
                                            .document(firebaseUser.getUid())
                                                    .set(user)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    Log.d(TAG, "User data added to Firestore successfully!");
                                                                }
                                                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding user data to Firestore", e);
                                        }
                                    });

                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, "Sign Up Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}

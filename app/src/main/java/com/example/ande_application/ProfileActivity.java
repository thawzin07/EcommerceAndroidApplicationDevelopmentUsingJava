package com.example.ande_application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextView userNameTextView, emailTextView, phoneTextView;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        userNameTextView = findViewById(R.id.userNameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);

        db = FirebaseFirestore.getInstance();

        // Initialize session manager
        SessionManager sessionManager = new SessionManager(getApplicationContext());

        String userId = sessionManager.getUserId();

        // Retrieve user data from Firestore using the received user ID
        db.collection("users").document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Get user data
                                String userName = document.getString("username");
                                String email = document.getString("useremail");
                                int phone = 0; // Default value in case of failure to retrieve the phone number

                                Object phoneObj = document.get("userphone");
                                if (phoneObj instanceof Long) {
                                    phone = ((Long) phoneObj).intValue();
                                } else if (phoneObj instanceof Integer) {
                                    phone = (Integer) phoneObj;
                                }

                                userNameTextView.setText(userName);
                                emailTextView.setText(email);
                                phoneTextView.setText(String.valueOf(phone));

                            }
                        } else {
                            // Handle error
                        }
                    }
                });
    }
}


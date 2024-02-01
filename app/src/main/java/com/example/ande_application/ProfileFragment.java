package com.example.ande_application;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileFragment extends Fragment {

    private ImageView profileImageView;
    private TextView userNameTextView, emailTextView, phoneTextView;
    private FirebaseFirestore db;
    private String userId;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImageView = view.findViewById(R.id.profileImageView);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);

        // Set OnClickListener for the button
        Button uploadPhotoButton = view.findViewById(R.id.uploadPhotoButton);
        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooserAndUpload();
            }
        });

        db = FirebaseFirestore.getInstance();

        // Initialize session manager
        SessionManager sessionManager = new SessionManager(requireContext());
        userId = sessionManager.getUserId();

        // Retrieve user data from Firestore using the received user ID
        db.collection("users").document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            // Get user data
                            String userName = document.getString("username");
                            String email = document.getString("useremail");
                            int phone = document.getLong("userphone").intValue();
                            String imageUrl ;
                            if (document.getString("userimageurl") == null ){
                                imageUrl = "https://firebasestorage.googleapis.com/v0/b/adne-steven.appspot.com/o/profileImages%2Fdefaultprofile.jpeg?alt=media&token=955ba03c-4bdb-4d23-bc1e-ce6dfebfcedb";
                            } else {
                                imageUrl = document.getString("userimageurl");
                            }


                            // Set text data
                            userNameTextView.setText(userName);
                            emailTextView.setText(email);
                            phoneTextView.setText(String.valueOf(phone));

                            // Load profile image using Glide or Picasso
                            if (imageUrl != null) {
                                Glide.with(requireContext())
                                        .load(imageUrl)
                                        .placeholder(R.drawable.profile) // Placeholder image while loading
                                        .error(R.drawable.sales) // Error image if loading fails
                                        .into(profileImageView);
                            } else {
                                // If no image URL is provided, you can set a default image here
                                profileImageView.setImageResource(R.drawable.wallet);
                            }
                        }
                    } else {
                        // Handle error
                    }
                });
        // Set OnClickListener for the logout button
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to log out?");

                // Add the buttons
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Logout button
                        FirebaseAuth.getInstance().signOut();
                        // Navigate to the login screen
                        Intent intent = new Intent(requireContext(), LoginActivity.class);
                        startActivity(intent);
                        requireActivity().finish(); // Finish current activity to prevent going back to profile screen
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        // Attempt to delete Account
        //Button deleteAccountButton = view.findViewById(R.id.deleteAccountButton);
        //deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Delete Account");
                builder.setMessage("Are you sure you want to delete your account? This action cannot be undone.");

                // Set up the buttons
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (currentUser != null) {
                            currentUser.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Account deleted successfully

                                                // Delete the user document from Firestore
                                                db.collection("users")
                                                        .document(currentUser.getUid())
                                                        .delete()
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                Log.d(TAG, "User data deleted from Firestore successfully!");
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.w(TAG, "Error deleting user data from Firestore", e);
                                                            }
                                                        });

                                                Toast.makeText(requireContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show();

                                                // Redirect to the login screen after account deletion
                                                Intent intent = new Intent(requireContext(), LoginActivity.class);
                                                startActivity(intent);
                                                requireActivity().finish();
                                            } else {
                                                // If the delete fails, display a message to the user.
                                                Toast.makeText(requireContext(), "Failed to delete account: " + task.getException(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the account deletion
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });*/


        return view;
    }


    // Method to handle opening file chooser and uploading image
    public void openFileChooserAndUpload() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    // Override onActivityResult to handle result from file chooser
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {
            Log.d("ImagePicker", String.valueOf(requestCode));
            Log.d("ImagePicker", String.valueOf(resultCode));
            Log.d("Data" , String.valueOf(data.getData()));

            if (resultCode == -1 && data != null && data.getData() != null) {
                mImageUri = data.getData(); // Assign the selected image URI to mImageUri
                Log.d("ImagePicker", "Selected Image URI: " + mImageUri.toString());
                uploadImage();
            } else {
                // Handle if no image is selected or selection canceled
                Toast.makeText(requireContext(), "Image selection canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to upload the selected image to Firebase Storage and update Firestore
    private void uploadImage() {
        if (mImageUri != null) {
            String folderPath = "profileImages/" + userId + "/";
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(folderPath);
            UploadTask uploadTask = storageRef.putFile(mImageUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Image uploaded successfully, now retrieve the download URL
                storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl = uri.toString();
                    db.collection("users").document(userId)
                            .update("userimageurl", imageUrl)
                            .addOnSuccessListener(aVoid -> {
                                Glide.with(requireContext())
                                        .load(imageUrl)
                                        .placeholder(R.drawable.profile)
                                        .error(R.drawable.sales)
                                        .into(profileImageView);
                            })
                            .addOnFailureListener(e -> {
                                Log.e("UploadImage", "Failed to update Firestore document: " + e.getMessage());
                            });
                });
            }).addOnFailureListener(e -> {
                Log.e("UploadImage", "Failed to upload image: " + e.getMessage());
            });
        } else {
            Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
}


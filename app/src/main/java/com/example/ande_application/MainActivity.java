package com.example.ande_application;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView; // Import RecyclerView

import com.example.ande_application.BestSellerAdapter;
import com.example.ande_application.ClothingAdapter;
import com.example.ande_application.OfferAdapter;
import com.example.ande_application.BestSeller;
import com.example.ande_application.Clothing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.util.Log; // Import Log

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load HomeFragment when activity is created
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.NavigationBarItem1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new HomeFragment())
                            .commit();
                    return true;
                case R.id.NavigationBarItem2:
                    // Replace with your second fragment
                    return true;
                case R.id.NavigationBarItem3:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ProfileFragment())
                            .commit();
                    return true;
            }
            return false;
        });
    }
}

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
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.util.Log; // Import Log

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView offerRecyclerView, bestSellerRecyclerView, clothingRecyclerView, bestSellerRecyclerView2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// offer RecyclerView
        offerRecyclerView = findViewById(R.id.offerRecyclerView);
        offerRecyclerView.setHasFixedSize(true);
        offerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Integer> imageList = new ArrayList<>();

        imageList.add(R.drawable.profile);
        imageList.add(R.drawable.profile);
        imageList.add(R.drawable.profile);

        OfferAdapter offerAdapter = new OfferAdapter(imageList);

        offerRecyclerView.setAdapter(offerAdapter);

        // best RecyclerView

        bestSellerRecyclerView = findViewById(R.id.bestSellerRecyclerView);
        bestSellerRecyclerView.setHasFixedSize(true);
        bestSellerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<BestSeller> bestSellerList = new ArrayList<>();

        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));

        BestSellerAdapter bestSellerAdapter = new BestSellerAdapter(bestSellerList);

        bestSellerRecyclerView.setAdapter(bestSellerAdapter);


        // clothing RecyclerView

        clothingRecyclerView = findViewById(R.id.clothingRecyclerView);
        clothingRecyclerView.setHasFixedSize(true);
        clothingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Clothing> clothingList = new ArrayList<>();

        clothingList.add(new Clothing(R.drawable.storage, "Up to 30% off"));
        clothingList.add(new Clothing(R.drawable.storage, "Up to 30% off"));
        clothingList.add(new Clothing(R.drawable.storage, "Up to 30% off"));

        ClothingAdapter clothingAdapter = new ClothingAdapter(clothingList);
        clothingRecyclerView.setAdapter(clothingAdapter);

        // best RecyclerView 2

        bestSellerRecyclerView2 = findViewById(R.id.bestSeller2RecyclerView);
        bestSellerRecyclerView2.setHasFixedSize(true);
        bestSellerRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        bestSellerRecyclerView2.setAdapter(bestSellerAdapter);


    }


}

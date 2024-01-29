package com.example.ande_application;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView; // Import RecyclerView

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.util.Log; // Import Log
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList; // Import ArrayList
import java.util.List; // Import List

public class MainActivity extends AppCompatActivity {

    private RecyclerView offerRecyclerView , bestSellerRecyclerView , clothingRecyclerView , bestSellerRecyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// offer RecyclerView
        offerRecyclerView = findViewById(R.id.offerRecyclerView);
        offerRecyclerView.setHasFixedSize(true);
        offerRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        List<Integer> imageList = new ArrayList<>();

        imageList.add(R.drawable.offer_shoping);
        imageList.add(R.drawable.nikon_canon_offer);
        imageList.add(R.drawable.tv_offer);

        OfferAdapter offerAdapter = new OfferAdapter(imageList);

        offerRecyclerView.setAdapter(offerAdapter);

        // best RecyclerView

        bestSellerRecyclerView = findViewById(R.id.bestSellerRecyclerview);
        bestSellerRecyclerView.setHasFixedSize(true);
        bestSellerRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        List<BestSeller> bestSellerList = new ArrayList<>();

        bestSellerList.add(new BestSeller(R.drawable.bags , "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.mobiles , "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.watches , "Up to 20% off"));

        BestSellerAdapter bestSellerAdapter = new BestSellerAdapter(bestSellerList);

        bestSellerRecyclerView.setAdapter(bestSellerAdapter);


        // clothing RecyclerView

        clothingRecyclerView = findViewById(R.id.clothingRecyclerview);
        clothingRecyclerView.setHasFixedSize(true);
        clothingRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        List<Clothing> clothingList = new ArrayList<>();

        clothingList.add(new Clothing(R.drawable.levis_clothing , "Up to 30% off"));
        clothingList.add(new Clothing(R.drawable.women_clothing , "Up to 30% off"));
        clothingList.add(new Clothing(R.drawable.nikeshoes , "Up to 30% off"));

        ClothingAdapter clothingAdapter = new ClothingAdapter(clothingList);
        clothingRecyclerView.setAdapter(clothingAdapter);

        // best RecyclerView 2

        bestSellerRecyclerView2 = findViewById(R.id.bestSeller2RecyclerView);
        bestSellerRecyclerView2.setHasFixedSize(true);
        bestSellerRecyclerView2.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        bestSellerRecyclerView2.setAdapter(bestSellerAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Select the default menu item
        bottomNavigationView.setSelectedItemId(R.id.NavigationBarItem1);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.NavigationBarItem1:
                                // Handle the Home item click
                                // Example: switchFragment(new HomeFragment());
                                return true;
                            case R.id.NavigationBarItem2:
                                // Handle the Transport item click
                                // Example: switchFragment(new TransportFragment());
                                return true;
                            case R.id.NavigationBarItem3:
                                // Handle the Trip item click
                                // Example: switchFragment(new TripFragment());
                                return true;
                        }
                        return false;
                    }
                });
    }


}

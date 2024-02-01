package com.example.ande_application;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView offerRecyclerView, bestSellerRecyclerView, clothingRecyclerView,
            bestSellerRecyclerView2 , specialOfferRecyclerView;


    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Initialize RecyclerViews
        offerRecyclerView = view.findViewById(R.id.offerRecyclerView);
        bestSellerRecyclerView = view.findViewById(R.id.bestSellerRecyclerView);
        clothingRecyclerView = view.findViewById(R.id.clothingRecyclerView);
        bestSellerRecyclerView2 = view.findViewById(R.id.bestSeller2RecyclerView);
        specialOfferRecyclerView = view.findViewById(R.id.specialOfferRecyclerView);

        // Setup offerRecyclerView
        setupOfferRecyclerView();

        fetchBestSellers();

        // Setup bestSellerRecyclerView
        setupBestSellerRecyclerView();

        // Setup clothingRecyclerView
        setupClothingRecyclerView();

        // Setup bestSellerRecyclerView2
        setupBestSeller2RecyclerView();

        return view;
    }

    //fetch best seller data from Firestore
    //fetch best seller data from Firestore
    private void fetchBestSellers() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("special_offers")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<SpecialOffer> specialOfferList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String imageUrl = document.getString("imageUrl");
                            String offerText = document.getString("offerText");
                            Double price = document.getDouble("price");
                            specialOfferList.add(new SpecialOffer(imageUrl, offerText , price));
                        }

                        // Log the specialOfferList
                        Log.d(TAG, "Special Offer List: " + specialOfferList.toString());

                        // Set the layout manager
                        specialOfferRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

                        // Pass the list of best sellers, context, and listener to your adapter
                        SpecialOfferAdapter adapter = new SpecialOfferAdapter(requireContext(), specialOfferList, new SpecialOfferAdapter.OnSpecialOfferClickListener() {
                            @Override
                            public void onSpecialOfferClicked(SpecialOffer specialOffer) {
                                // Start OneItemActivity when special offer is clicked
                                Intent intent = new Intent(requireContext(), OneItemActivity.class);
                                // Pass special offer data to OneItemActivity using intent extras
                                intent.putExtra("imageUrl", specialOffer.getImageUrl());
                                intent.putExtra("offerText", specialOffer.getOfferText());
                                intent.putExtra("price" , specialOffer.getPrice());
                                startActivity(intent);
                            }
                        });
                        specialOfferRecyclerView.setAdapter(adapter);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }



    public void onSpecialOfferImageClick(View view) {
        // Start OneItemActivity when ImageView is clicked
        startActivity(new Intent(requireContext(), OneItemActivity.class));
    }



    private void setupOfferRecyclerView() {
        offerRecyclerView.setHasFixedSize(true);
        offerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.profile);
        imageList.add(R.drawable.profile);
        imageList.add(R.drawable.profile);

        OfferAdapter offerAdapter = new OfferAdapter(imageList);
        offerRecyclerView.setAdapter(offerAdapter);
    }

    private void setupBestSellerRecyclerView() {
        bestSellerRecyclerView.setHasFixedSize(true);
        bestSellerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<BestSeller> bestSellerList = new ArrayList<>();
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));

        BestSellerAdapter bestSellerAdapter = new BestSellerAdapter(bestSellerList);
        bestSellerRecyclerView.setAdapter(bestSellerAdapter);
    }

    private void setupClothingRecyclerView() {
        clothingRecyclerView.setHasFixedSize(true);
        clothingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<Clothing> clothingList = new ArrayList<>();
        clothingList.add(new Clothing(R.drawable.storage, "Up to 30% off"));
        clothingList.add(new Clothing(R.drawable.storage, "Up to 30% off"));
        clothingList.add(new Clothing(R.drawable.storage, "Up to 30% off"));

        ClothingAdapter clothingAdapter = new ClothingAdapter(clothingList);
        clothingRecyclerView.setAdapter(clothingAdapter);
    }

    private void setupBestSeller2RecyclerView() {
        bestSellerRecyclerView2.setHasFixedSize(true);
        bestSellerRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<BestSeller> bestSellerList = new ArrayList<>();
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));
        bestSellerList.add(new BestSeller(R.drawable.cart, "Up to 20% off"));

        BestSellerAdapter bestSellerAdapter = new BestSellerAdapter(bestSellerList);
        bestSellerRecyclerView2.setAdapter(bestSellerAdapter);
    }
}

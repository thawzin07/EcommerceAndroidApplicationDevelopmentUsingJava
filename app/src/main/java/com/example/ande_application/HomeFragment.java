package com.example.ande_application;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ande_application.BestSeller;
import com.example.ande_application.BestSellerAdapter;
import com.example.ande_application.Clothing;
import com.example.ande_application.ClothingAdapter;
import com.example.ande_application.OfferAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView offerRecyclerView, bestSellerRecyclerView, clothingRecyclerView, bestSellerRecyclerView2;

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

        // Setup offerRecyclerView
        setupOfferRecyclerView();

        // Setup bestSellerRecyclerView
        setupBestSellerRecyclerView();

        // Setup clothingRecyclerView
        setupClothingRecyclerView();

        // Setup bestSellerRecyclerView2
        setupBestSeller2RecyclerView();

        return view;
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

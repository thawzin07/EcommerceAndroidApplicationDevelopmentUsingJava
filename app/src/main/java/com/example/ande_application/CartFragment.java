package com.example.ande_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CartFragment extends Fragment {

    private LinearLayout cartItemsLayout; // Newly added

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_cart.xml layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize views
        cartItemsLayout = rootView.findViewById(R.id.cartItemsLayout);
        TextView emptyCartTextView = rootView.findViewById(R.id.emptyCartTextView);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("CartData", requireContext().MODE_PRIVATE);
        String cartData = sharedPreferences.getString("cartData", "[]");

        // Parse cart data as a JSONArray
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(cartData);
        } catch (JSONException e) {
            e.printStackTrace();
            jsonArray = new JSONArray();
        }

        // Check if the cart is empty
        if (jsonArray.length() == 0) {
            emptyCartTextView.setVisibility(View.VISIBLE);
        } else {
            // Initialize a variable to hold the total price
            double totalPrice = 0.00f;

            // Iterate over each item in the cart and create a view for it
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String imageUrl = item.getString("imageUrl");
                    String title = item.getString("title");
                    float finalPrice = (float) item.getDouble("finalPrice");
                    int itemCount = item.getInt("itemCount");

                    totalPrice += finalPrice * itemCount;
                    // Create a view for the item and add it to the layout
                    addCartItemView(imageUrl, title, finalPrice, itemCount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Button buyButton = rootView.findViewById(R.id.buyButton);
            double finalTotalPrice = totalPrice;
            JSONArray finalJsonArray = jsonArray;
            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform buy action
                    Intent intent = new Intent(requireContext(), PaymentActivity.class);
                    try {
                        JSONObject firstItem = finalJsonArray.getJSONObject(0);
                        double firstItemPrice = firstItem.getDouble("finalPrice");
                        intent.putExtra("finalPrice", firstItemPrice);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    startActivity(intent);

                    //toast message indicating that the item is purchased
                    Toast.makeText(requireContext(), "Item purchased successfully!", Toast.LENGTH_SHORT).show();

                    // Optionally, clear the cart data
                    // clearCartData();
                }
            });
        }

        return rootView;
    }




    // Method to add a view for a cart item to the layout
    private void addCartItemView(String imageUrl, String title, float finalPrice, int itemCount) {
        // Inflate the cart item layout
        View cartItemView = LayoutInflater.from(requireContext()).inflate(R.layout.cart_item_layout, cartItemsLayout, false);

        // Initialize views in the cart item layout
        ImageView itemImageView = cartItemView.findViewById(R.id.itemImageView);
        TextView titleTextView = cartItemView.findViewById(R.id.titleTextView);
        TextView priceTextView = cartItemView.findViewById(R.id.priceTextView);
        TextView itemCountTextView = cartItemView.findViewById(R.id.itemCountTextView);

        // Set data to views
        Glide.with(requireContext())
                .load(imageUrl)
                .placeholder(R.drawable.coffee) // Placeholder image while loading
                .error(R.drawable.coffee) // Error image if loading fails
                .into(itemImageView);
        titleTextView.setText(title);
        priceTextView.setText("Price: $" + finalPrice);
        itemCountTextView.setText("Item Count: " + itemCount);

        // Add the cart item view to the layout
        cartItemsLayout.addView(cartItemView);
    }
}



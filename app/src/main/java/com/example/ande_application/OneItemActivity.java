package com.example.ande_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class OneItemActivity extends AppCompatActivity {

    // Declare finalPrice variable outside of inner OnClickListener classes
    private double finalPrice = 0.00;
    int count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneitem);

        // Initialize views
        ImageView itemImageView;
        TextView itemTitleTextView = findViewById(R.id.itemTitleTextView);
        TextView itemDescriptionTextView = findViewById(R.id.itemDescriptionTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        Button buyButton = findViewById(R.id.buyButton);
        TextView itemCountTextView = findViewById(R.id.itemCountTextView);
        Button increaseButton = findViewById(R.id.increaseButton);
        Button decreaseButton = findViewById(R.id.decreaseButton);
        Button addToCartButton = findViewById(R.id.addToCartButton);


        // Get the imageUrl, title, and price from the intent
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String title = getIntent().getStringExtra("offerText");
        Double price = getIntent().getDoubleExtra("price", 0.00);

// Find the ImageView in the layout
        itemImageView = findViewById(R.id.itemImageView);

        // Load the image using Glide
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.deals) // Placeholder image while loading
                .error(R.drawable.sales)
                .into(itemImageView);

        // Set data for the views
        itemTitleTextView.setText(title);
        itemDescriptionTextView.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        priceTextView.setText("Price: $" + price); // Concatenate price with the string

        // Set initial total price
        finalPrice = price; // Set initial final price
        totalPriceTextView.setText("Total Price: $" + finalPrice);

        // Set click listener for the increase button
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++; // Increment the count
                itemCountTextView.setText(String.valueOf(count)); // Update the count TextView
                finalPrice = price * count; // Recalculate the final price
                totalPriceTextView.setText("Total Price: $" + finalPrice); // Update the total price TextView
            }
        });

        // Set click listener for the decrease button
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) { // Ensure count doesn't go below 1
                    count--; // Decrement the count
                    itemCountTextView.setText(String.valueOf(count)); // Update the count TextView
                    finalPrice = price * count; // Recalculate the final price
                    totalPriceTextView.setText("Total Price: $" + finalPrice); // Update the total price TextView
                }
            }
        });

        // Set click listener for the button
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OneItemActivity.this, "Item added to your cart successfully!", Toast.LENGTH_SHORT).show();
                // Start PaymentActivity with total price as extra
                //Intent intent = new Intent(OneItemActivity.this, PaymentActivity.class);
                //intent.putExtra("finalPrice", finalPrice);
               // startActivity(intent);
            }
        });

        // Set click listener for the button
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start PaymentActivity with total price as extra
                Intent intent = new Intent(OneItemActivity.this, PaymentActivity.class);
                intent.putExtra("finalPrice", finalPrice);
                startActivity(intent);
            }
        });

        // Set click listener for the back button
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity or fragment
                finish();
            }
        });
    }

}

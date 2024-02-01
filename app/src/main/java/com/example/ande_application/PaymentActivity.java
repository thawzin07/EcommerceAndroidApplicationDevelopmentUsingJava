package com.example.ande_application;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class PaymentActivity extends AppCompatActivity {

    private TextView finalPriceTextView;
    private TextInputEditText creditCardNumberEditText;
    private TextInputEditText expiryMonthEditText;
    private TextInputEditText expiryYearEditText;
    private TextInputEditText cvcEditText;
    private Button placeOrderButton;
    private Button backButton; // Declare backButton here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentform);

        // Initialize views
        finalPriceTextView = findViewById(R.id.finalPriceTextView);
        creditCardNumberEditText = findViewById(R.id.creditCardNumberEditText);
        expiryMonthEditText = findViewById(R.id.expiryMonthEditText);
        expiryYearEditText = findViewById(R.id.expiryYearEditText);
        cvcEditText = findViewById(R.id.cvcEditText);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        backButton = findViewById(R.id.backButton);

        // Get the final price from the intent
        double finalPrice = getIntent().getDoubleExtra("finalPrice", 0.00);

        // Set final price
        finalPriceTextView.setText("Final Price: $" + finalPrice);

        // Set click listener for place order button
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input fields
                String creditCardNumber = creditCardNumberEditText.getText().toString().trim();
                String expiryMonth = expiryMonthEditText.getText().toString().trim();
                String expiryYear = expiryYearEditText.getText().toString().trim();
                String cvc = cvcEditText.getText().toString().trim();

                if (TextUtils.isEmpty(creditCardNumber) || TextUtils.isEmpty(expiryMonth) ||
                        TextUtils.isEmpty(expiryYear) || TextUtils.isEmpty(cvc)) {
                    Toast.makeText(PaymentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidCreditCardNumber(creditCardNumber)) {
                    Toast.makeText(PaymentActivity.this, "Invalid credit card number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidExpiryDate(expiryMonth, expiryYear)) {
                    Toast.makeText(PaymentActivity.this, "Invalid expiry date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidCVC(cvc)) {
                    Toast.makeText(PaymentActivity.this, "Invalid CVC", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Process payment
                processPayment();
            }
        });

        // Set click listener for back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous screen
                finish();
            }
        });
    }

    private boolean isValidCreditCardNumber(String creditCardNumber) {
        // Check if the credit card number is empty or null
        if (TextUtils.isEmpty(creditCardNumber)) {
            return false;
        }

        // Remove any spaces or dashes from the credit card number
        String sanitizedCreditCardNumber = creditCardNumber.replaceAll("[\\s-]+", "");

        // Check if the credit card number contains only digits
        if (!sanitizedCreditCardNumber.matches("\\d+")) {
            return false;
        }

        // Implement Luhn algorithm for credit card number validation
        int sum = 0;
        boolean alternate = false;
        for (int i = sanitizedCreditCardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(sanitizedCreditCardNumber.substring(i, i + 1));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    private boolean isValidExpiryDate(String expiryMonth, String expiryYear) {
        // Check if the expiry month and year are empty or null
        if (TextUtils.isEmpty(expiryMonth) || TextUtils.isEmpty(expiryYear)) {
            return false;
        }

        // Implement expiry date validation logic (e.g., check if the month and year are valid)
        // You may need to convert the strings to integers for comparison

        return true; // Placeholder, replace with actual logic
    }

    private boolean isValidCVC(String cvc) {
        // Check if the CVC is empty or null
        if (TextUtils.isEmpty(cvc)) {
            return false;
        }

        // Implement CVC validation logic (e.g., check if the CVC has the correct length)

        return true; // Placeholder, replace with actual logic
    }

    private void processPayment() {
        // Implement payment processing logic
        // Show success message or navigate to next screen
        Toast.makeText(this, "Payment processed successfully", Toast.LENGTH_SHORT).show();
        finish(); // Finish the activity after payment processing
    }

}

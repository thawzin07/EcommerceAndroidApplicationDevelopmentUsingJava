package com.example.ande_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SpecialOfferAdapter extends RecyclerView.Adapter<SpecialOfferAdapter.SpecialOfferViewHolder> {

    private Context context;
    private List<SpecialOffer> specialOfferList;
    private OnSpecialOfferClickListener listener;

    public SpecialOfferAdapter(Context context, List<SpecialOffer> specialOfferList, OnSpecialOfferClickListener listener) {
        this.context = context;
        this.specialOfferList = specialOfferList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SpecialOfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.special_offer_layout, parent, false);
        return new SpecialOfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialOfferViewHolder holder, int position) {
        SpecialOffer specialOffer = specialOfferList.get(position);
        holder.offerText.setText(specialOffer.getOfferText());

        // Load image from Firebase Cloud Storage using Glide
        Glide.with(context)
                .load(specialOffer.getImageUrl())
                .into(holder.imageView);

        // Set click listener on the image
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSpecialOfferClicked(specialOffer);
                }
            }
        });

        //holder.priceText.setText(String.valueOf(specialOffer.getPrice()));
    }

    @Override
    public int getItemCount() {
        return specialOfferList.size();
    }

    public interface OnSpecialOfferClickListener {
        void onSpecialOfferClicked(SpecialOffer specialOffer);
    }

    public class SpecialOfferViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView offerText;

        public SpecialOfferViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.specialOfferImage);
            offerText = itemView.findViewById(R.id.specialOfferText);
        }
    }
}

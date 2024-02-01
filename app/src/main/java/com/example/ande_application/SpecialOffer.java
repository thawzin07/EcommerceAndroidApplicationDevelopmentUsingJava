package com.example.ande_application;

public class SpecialOffer {
    private String imageUrl;
    private String offerText;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public SpecialOffer(String imageUrl, String offerText, Double price) {
        this.imageUrl = imageUrl;
        this.offerText = offerText;
        this.price = price ;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOfferText() {
        return offerText;
    }
}

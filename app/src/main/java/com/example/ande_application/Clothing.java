package com.example.ande_application;

public class Clothing {

    private int image;
    private String offer;

    public Clothing(int image , String offer){
        this.image = image;
        this.offer = offer;
    }

    public int getImage() {
        return image;
    }

    public String getOffer() {
        return offer;
    }
}
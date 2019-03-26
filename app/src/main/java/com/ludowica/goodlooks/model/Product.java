package com.ludowica.goodlooks.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private double price;
    @SerializedName("category")
    private int category;

    public Product() {
    }

    public Product(int id, String name, String image, double price, int category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}

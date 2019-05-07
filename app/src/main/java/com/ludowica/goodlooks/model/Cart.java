package com.ludowica.goodlooks.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("id")
    private int id;

    @SerializedName("userId")
    private int userId;

    @SerializedName("cartProducts")
    private List<CartProduct> cartProducts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductInterface {
    @GET("/products")
    Call<Product> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);
}

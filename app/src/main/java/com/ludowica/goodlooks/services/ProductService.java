package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("/products")
    Call<List<Product>> getAll();
}

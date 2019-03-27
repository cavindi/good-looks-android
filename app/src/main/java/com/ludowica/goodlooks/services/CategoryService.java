package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("/categories")
    Call<List<Category>> getAll();
}

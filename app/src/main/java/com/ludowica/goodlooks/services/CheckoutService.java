package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.model.Cart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CheckoutService {

    @GET("checkout/{id}")
    Call<Cart> checkoutCustomer(@Path("id") int id);
}

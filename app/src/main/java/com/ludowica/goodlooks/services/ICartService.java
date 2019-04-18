package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.forms.ShoppingCartForm;
import com.ludowica.goodlooks.model.Cart;
import com.ludowica.goodlooks.model.CartProduct;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ICartService {

    @GET("carts/{id}")
    Call<Cart> get(@Path("id") int id);

    @POST("carts")
    Call<CartProduct> addOrUpdate(@Body ShoppingCartForm shoppingCartForm);

    @POST("carts/create")
    Call<Cart> createCart(@Body int userId);

    @HTTP(method = "DELETE", path = "carts", hasBody = true)
    Call<CartProduct> deleteCartItem(@Body ShoppingCartForm shoppingCartForm);
}

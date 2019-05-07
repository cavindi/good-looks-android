package com.ludowica.goodlooks.services;

import android.content.Context;
import android.util.Log;

import com.ludowica.goodlooks.forms.ShoppingCartForm;
import com.ludowica.goodlooks.model.Cart;
import com.ludowica.goodlooks.model.CartProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartService {

    private static final String TAG = CartService.class.getSimpleName();


    public void addOrUpdate(Context context, int productId, int quantity) {

        int cartId = SharedPreferencesManager.getCartId(context);
        ShoppingCartForm shoppingCartForm = new ShoppingCartForm(cartId, productId, quantity);

        ICartService cartService = ApiClient.getClient().create(ICartService.class);
        Call<CartProduct> call = cartService.addOrUpdate(shoppingCartForm);

        call.enqueue(new Callback<CartProduct>() {
            @Override
            public void onResponse(Call<CartProduct> call, Response<CartProduct> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<CartProduct> call, Throwable t) {

            }
        });
    }

    public void initGetCart(Context context) {

        int userId = SharedPreferencesManager.getUserId(context);

        ICartService cartService = ApiClient.getClient().create(ICartService.class);
        Call<Cart> call = cartService.fetchCart(userId);

        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart cart = response.body();
                Log.d(TAG, "Number of carts received: " + cart);
                SharedPreferencesManager.setCartId(context, cart.getId());
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    public void deleteCartItem(Context context, int productId, int quantity) {

        int cartId = SharedPreferencesManager.getCartId(context);
        ShoppingCartForm shoppingCartForm = new ShoppingCartForm(cartId, productId, quantity);

        ICartService cartService = ApiClient.getClient().create(ICartService.class);
        Call<CartProduct> call = cartService.deleteCartItem(shoppingCartForm);

        call.enqueue(new Callback<CartProduct>() {
            @Override
            public void onResponse(Call<CartProduct> call, Response<CartProduct> response) {
                CartProduct cartProduct = response.body();
                Log.d(TAG, "Number of cart product received: " + cartProduct);
            }

            @Override
            public void onFailure(Call<CartProduct> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}

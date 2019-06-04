package com.ludowica.goodlooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.model.Cart;
import com.ludowica.goodlooks.model.CartProduct;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.CheckoutService;
import com.ludowica.goodlooks.services.ICartService;
import com.ludowica.goodlooks.services.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    private TextView lblQuantity, lblTotal;
    private EditText inputCardNo;

    private List<CartProduct> cartProductList = new ArrayList<>();

    private static final String TAG = CheckoutActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Checkout");

        init();
        retrieveCart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {

        lblQuantity = findViewById(R.id.lbl_quantity);
        lblTotal = findViewById(R.id.lbl_total);

        inputCardNo = findViewById(R.id.input_card_number);
    }

    public void btnCheckoutClicked(View view) {

        if (inputCardNo.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.empty_card_no), Toast.LENGTH_SHORT).show();
        } else {

            int cartId = SharedPreferencesManager.getCartId(this);

            CheckoutService checkoutService = ApiClient.getClient().create(CheckoutService.class);
            Call<Cart> call = checkoutService.checkoutCustomer(cartId);

            call.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    Cart cart = response.body();
                    int cartId = cart.getId();
                    SharedPreferencesManager.setCartId(getApplicationContext(), cartId);

                    Toast.makeText(getApplicationContext(), getString(R.string.success_checkout), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });
        }
    }

    private void retrieveCart() {

        int cartId = SharedPreferencesManager.getCartId(this);

        ICartService cartService = ApiClient.getClient().create(ICartService.class);
        Call<Cart> call = cartService.get(cartId);

        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart cart = response.body();
                cartProductList.clear();
                cartProductList.addAll(cart.getCartProducts());

                setValues();
                Log.d(TAG, "Number of cart products received: " + cartProductList.size());
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void setValues() {

        double total = 0;
        int quantity = 0;

        for (CartProduct cartProduct : cartProductList) {
            quantity += cartProduct.getQuantity();
            total += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
        }

        lblQuantity.setText(String.valueOf(quantity));
        lblTotal.setText(getString(R.string.price, total));

    }
}

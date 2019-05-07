package com.ludowica.goodlooks.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.adapter.CartAdapter;
import com.ludowica.goodlooks.model.Cart;
import com.ludowica.goodlooks.model.CartProduct;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.ICartService;
import com.ludowica.goodlooks.services.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = CartActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private FloatingActionButton btnCheckout;
    private TextView lblEmpty;

    private List<CartProduct> cartProductList = new ArrayList<>();
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shopping Cart");

        init();
        getUserCart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartProductList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartProductList);
        recyclerView.setAdapter(cartAdapter);
    }


    public void getUserCart() {

        int userId = SharedPreferencesManager.getUserId(this);

        ICartService cartService = ApiClient.getClient().create(ICartService.class);
        Call<Cart> call = cartService.fetchCart(userId);

        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart cart = response.body();

                if (!cart.getCartProducts().isEmpty()) {
                    cartProductList.clear();
                    cartProductList.addAll(cart.getCartProducts());
                    cartAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.empty_cart), Toast.LENGTH_LONG).show();
                }

                Log.d(TAG, "Cart: " + cartProductList.size());
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}

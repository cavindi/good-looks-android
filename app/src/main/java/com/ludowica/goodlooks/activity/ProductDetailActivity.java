package com.ludowica.goodlooks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.model.Product;
import com.ludowica.goodlooks.services.CartService;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView name, price, description;
    private ImageView image;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        init();
        setData();
    }

    private void init() {

        name = findViewById(R.id.lbl_name);
        price = findViewById(R.id.lbl_price);
        image = findViewById(R.id.image);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            product = gson.fromJson(bundle.getString("product"), Product.class);
        }
    }

    public void setData() {
        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        Glide.with(this).load(product.getImage()).into(image);
    }

    public void btnAddToCartClicked(View view) {
        addToCart(product);
    }

    private void addToCart(Product product) {

        CartService cartService = new CartService();
        cartService.addOrUpdate(this, product.getId(), 1);
        Toast.makeText(getApplicationContext(), "Product Added " + product.getName(), Toast.LENGTH_LONG).show();
    }
}

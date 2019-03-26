package com.ludowica.goodlooks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.model.Product;

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
}

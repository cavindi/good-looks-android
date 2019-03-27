package com.ludowica.goodlooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.adapter.ProductAdapter;
import com.ludowica.goodlooks.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validate();
    }

    private void validate(){
            Intent intent = new Intent(this, HomeActivity.class);
            this.startActivity(intent);
    }
}

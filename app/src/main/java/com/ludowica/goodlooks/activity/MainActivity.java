package com.ludowica.goodlooks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.adapter.ProductAdapter;
import com.ludowica.goodlooks.model.Product;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void retrieveProducts() {
        ProductService productService = ApiClient.getClient().create(ProductService.class);

        Call<List<Product>> call = productService.getAll();
       call.enqueue(new Callback<List<Product>>() {
           @Override
           public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
               List<Product> products = response.body();
               productList.clear();
               productList.addAll(products);
               System.out.println(productList);
               /*productAdapter.notifyDataSetChanged();*/

           }

           @Override
           public void onFailure(Call<List<Product>> call, Throwable t) {

           }
       });
    }

}

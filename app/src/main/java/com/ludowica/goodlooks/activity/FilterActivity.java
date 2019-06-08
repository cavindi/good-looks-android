package com.ludowica.goodlooks.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.adapter.ProductAdapter;
import com.ludowica.goodlooks.model.Product;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = FilterActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private SearchView searchView;
    private SearchView.OnQueryTextListener queryTextListener;

    private String key = "";
    private List<Product> productList;
    private ProductAdapter productsListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        init();

        Intent intent = getIntent();

        if (intent.hasExtra("key")) {
            key = intent.getStringExtra("key");
            productsListAdapter.getFilter().filter(key);
            getSupportActionBar().setTitle(key);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String query) {
                    productsListAdapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    productsListAdapter.getFilter().filter(query);
                    return false;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productList = new ArrayList<>();
        productsListAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productsListAdapter);

        getProducts();
    }

    private void getProducts() {

        ProductService productService = ApiClient.getClient().create(ProductService.class);

        Call<List<Product>> call = productService.getAll();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                productList.clear();
                productList.addAll(products);
                productsListAdapter.getFilter().filter(key);
                productsListAdapter.notifyDataSetChanged();
                Log.d(TAG, "products" + products.size());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}

package com.ludowica.goodlooks.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HomeFragment extends Fragment {

    private List<Product> productList;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext(), productList);
        recyclerView.setAdapter(productAdapter);

        retrieveProducts();

        return view;
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
                productAdapter.notifyDataSetChanged();
                Log.d(TAG, "Number of products received: " + products.size());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }


}

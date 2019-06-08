package com.ludowica.goodlooks.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.adapter.CategoryAdapter;
import com.ludowica.goodlooks.model.Category;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private RecyclerView category_list;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;

    private static final String TAG = CategoryFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        category_list = view.findViewById(R.id.category_list);
        category_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        category_list.setAdapter(categoryAdapter);

        retrieveCategory();

        return view;
    }

    private void retrieveCategory() {
        CategoryService categoryService = ApiClient.getClient().create(CategoryService.class);

        Call<List<Category>> call = categoryService.getAll();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = response.body();
                categoryList.clear();
                categoryList.addAll(categories);
                categoryAdapter.notifyDataSetChanged();
                Log.d(TAG, "Number of products received: " + categories.size());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}

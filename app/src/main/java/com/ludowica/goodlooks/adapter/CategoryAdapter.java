package com.ludowica.goodlooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.activity.FilterActivity;
import com.ludowica.goodlooks.activity.ProductDetailActivity;
import com.ludowica.goodlooks.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_category, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Category category = categories.get(i);
        myViewHolder.name.setText(category.getName());

        Glide.with(context).load(category.getImage()).into(myViewHolder.image);

        myViewHolder.card.setOnClickListener(view -> {
            Intent intent = new Intent(context, FilterActivity.class); //create activity
            intent.putExtra("key", String.valueOf(category.getId()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;
        public RelativeLayout card;

        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            image = view.findViewById(R.id.image);
            card = view.findViewById(R.id.card);
        }
    }
}

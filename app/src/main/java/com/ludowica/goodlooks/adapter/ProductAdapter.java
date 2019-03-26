package com.ludowica.goodlooks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> productList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        public ImageView image;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.lbl_name);
            price = view.findViewById(R.id.lbl_price);
            image = view.findViewById(R.id.image);
            cardView = view.findViewById(R.id.card_product);
        }
    }

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_product, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        Glide.with(context).load(product.getImage()).into(holder.image);

/*        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            Gson gson = new Gson();
            String productJson = gson.toJson(product);
            intent.putExtra("product", productJson);
            context.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}

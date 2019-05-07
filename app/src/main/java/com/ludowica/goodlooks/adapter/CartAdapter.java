package com.ludowica.goodlooks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.activity.ProductDetailActivity;
import com.ludowica.goodlooks.model.CartProduct;
import com.ludowica.goodlooks.model.Product;
import com.ludowica.goodlooks.services.CartService;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private List<CartProduct> cartProductList;

    public CartAdapter(Context context, List<CartProduct> cartProductList) {
        this.context = context;
        this.cartProductList = cartProductList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_cart, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final CartProduct cartProduct = cartProductList.get(i);
        final Product product = cartProduct.getProduct();

        myViewHolder.name.setText(product.getName());
        myViewHolder.quantity.setText(String.valueOf(cartProduct.getQuantity()));
        myViewHolder.price.setText(context.getString(R.string.price, product.getPrice()));
        Glide.with(context).load(product.getImage()).into(myViewHolder.image);

        myViewHolder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            Gson gson = new Gson();
            String productJson = gson.toJson(product);
            intent.putExtra("product", productJson);
            context.startActivity(intent);
        });

        if (Integer.parseInt(myViewHolder.quantity.getText().toString()) <= 1) {
            myViewHolder.btnSub.setEnabled(false);
        }

        myViewHolder.btnAdd.setOnClickListener(view -> {

            int qty = Integer.parseInt(myViewHolder.quantity.getText().toString());
            qty++;
            myViewHolder.quantity.setText(String.valueOf(qty));
            myViewHolder.btnSub.setEnabled(true);

            CartService cartService = new CartService();
            cartService.addOrUpdate(context, cartProduct.getProduct().getId(), qty);
        });

        myViewHolder.btnSub.setOnClickListener(view -> {

            int qty = Integer.parseInt(myViewHolder.quantity.getText().toString());
            qty--;
            myViewHolder.quantity.setText(String.valueOf(qty));

            if (qty <= 1) {
                myViewHolder.btnSub.setEnabled(false);
            }
            CartService cartService = new CartService();
            cartService.addOrUpdate(context, cartProduct.getProduct().getId(), qty);
        });

        myViewHolder.btnRemove.setOnClickListener(view -> {
            CartService cartService = new CartService();
            cartService.deleteCartItem(context, cartProduct.getProduct().getId(), 0);
            cartProductList.remove(myViewHolder.getAdapterPosition());
            notifyItemRemoved(myViewHolder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return cartProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, quantity;
        public ImageView image;
        public Button btnAdd, btnSub, btnRemove;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.lbl_name);
            price = view.findViewById(R.id.lbl_price);
            quantity = view.findViewById(R.id.lbl_quantity);
            image = view.findViewById(R.id.image);
            btnAdd = view.findViewById(R.id.btn_add);
            btnSub = view.findViewById(R.id.btn_sub);
            btnRemove = view.findViewById(R.id.btn_remove);
            cardView = view.findViewById(R.id.card);
        }
    }

}

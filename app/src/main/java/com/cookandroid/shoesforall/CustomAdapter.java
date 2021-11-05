package com.cookandroid.shoesforall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Shoes> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Shoes> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.main_recyclerview,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Shoes shoes = arrayList.get(position);
        Glide.with(holder.itemView).load(shoes.getImage()).into(holder.homeimg_btn);

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageButton homeimg_btn;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.homeimg_btn = itemView.findViewById(R.id.homeimg_btn);
        }
    }
}

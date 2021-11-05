package com.cookandroid.shoesforall;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

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
        holder.brand_text.setText(arrayList.get(position).getBrand());
        holder.description_text.setText(arrayList.get(position).getDescription());
        holder.information_text.setText(arrayList.get(position).getInformation());
        holder.price_text.setText(arrayList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageButton homeimg_btn;
        TextView brand_text,description_text,information_text,name_text,price_text;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.homeimg_btn = itemView.findViewById(R.id.homeimg_btn);

            itemView.setClickable(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context,detail_page.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("TEXT",arrayList.get(pos).getPrice());
                        context.startActivity(intent);
                    }
                }
            });
            this.brand_text = itemView.findViewById(R.id.brand_text);
            this.description_text = itemView.findViewById(R.id.description_text);
            this.information_text = itemView.findViewById(R.id.information_text);
            this.name_text = itemView.findViewById(R.id.name_text);
            this.price_text = itemView.findViewById(R.id.price_text);
        }
    }
}

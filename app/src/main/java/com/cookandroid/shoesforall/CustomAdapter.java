package com.cookandroid.shoesforall;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.cookandroid.shoesforall.DataModel.Shoes;

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

        holder.brand_text.setText(shoes.getBrand());
        holder.brand_text.setVisibility(View.GONE);

        holder.description_text.setText(shoes.getDescription());
        holder.description_text.setVisibility(View.GONE);

        holder.information_text.setText(shoes.getInformation());
        holder.information_text.setVisibility(View.GONE);

        holder.price_text.setText(shoes.getPrice());
        holder.price_text.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView homeimg_btn;
        TextView brand_text,description_text,information_text,name_text,price_text;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.homeimg_btn = itemView.findViewById(R.id.homeimg_btn);
            this.brand_text = itemView.findViewById(R.id.brand_text);
            this.description_text = itemView.findViewById(R.id.description_text);
            this.information_text = itemView.findViewById(R.id.information_text);
            this.name_text = itemView.findViewById(R.id.name_text);
            this.price_text = itemView.findViewById(R.id.price_text);

            itemView.setClickable(true);
            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(context,detail_page.class);

                    intent.putExtra("IMAGE",arrayList.get(pos).getImage());
                    intent.putExtra("PRICE",arrayList.get(pos).getPrice());
                    intent.putExtra("BRAND",arrayList.get(pos).getBrand());
                    intent.putExtra("DESCRIPTION",arrayList.get(pos).getDescription());
                    intent.putExtra("INFORMATION",arrayList.get(pos).getInformation());
                    intent.putExtra("NAME",arrayList.get(pos).getName());

                    context.startActivity(intent);
                }
            });

        }
    }
}

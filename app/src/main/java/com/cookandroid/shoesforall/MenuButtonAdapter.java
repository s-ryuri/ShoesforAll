package com.cookandroid.shoesforall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MenuButtonAdapter extends RecyclerView.Adapter<MenuButtonAdapter.MenuViewHolder>{

    private ArrayList<MenuButton> arrayList;
    private Context context;

    public MenuButtonAdapter(ArrayList<MenuButton> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_shoes_recyclerview,parent,false);
        MenuViewHolder holder = new MenuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        MenuButton menubtn = arrayList.get(position);
        Glide.with(holder.itemView).load(menubtn.getImage()).into(holder.menu_shoes_picture);

        holder.menu_shoes_picture.setVisibility(View.VISIBLE);
        holder.show_text.setText(menubtn.getDescription());

        holder.menu_brand_text.setText(menubtn.getBrand());
        holder.menu_price_text.setText(menubtn.getPrice());
        holder.menu_description_text.setText(menubtn.getDescription());
        holder.menu_information_text.setText(menubtn.getInformation());
        holder.menu_name_text.setText(menubtn.getName());

    }


    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        ImageView menu_shoes_picture;
        TextView show_text,menu_brand_text,menu_description_text,menu_information_text,menu_name_text,menu_price_text;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            this.menu_shoes_picture = itemView.findViewById(R.id.menu_shoes_picture);
            this.show_text = itemView.findViewById(R.id.show_text);
            this.menu_brand_text = itemView.findViewById(R.id.menu_brand_text);
            this.menu_price_text = itemView.findViewById(R.id.menu_price_text);
            this.menu_description_text = itemView.findViewById(R.id.menu_description_text);
            this.menu_information_text = itemView.findViewById(R.id.menu_information_text);
            this.menu_name_text = itemView.findViewById(R.id.menu_name_text);

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

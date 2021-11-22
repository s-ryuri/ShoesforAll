package com.cookandroid.shoesforall;

import android.content.Context;
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
        Log.d("picture","123");
        holder.menu_shoes_picture.setVisibility(View.VISIBLE);
        holder.show_text.setText(menubtn.getDescription());

    }


    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{

        ImageView menu_shoes_picture;
        TextView show_text;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            this.menu_shoes_picture = itemView.findViewById(R.id.menu_shoes_picture);
            this.show_text = itemView.findViewById(R.id.show_text);
        }
    }
}

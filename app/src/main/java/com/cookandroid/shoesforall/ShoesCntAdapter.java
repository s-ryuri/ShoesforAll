package com.cookandroid.shoesforall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

interface ClickListener {

    void onPlusClicked(Shoes_cnt shoes);

    void onMinusClicked(Shoes_cnt shoes);

    void onDeleteClicked(Shoes_cnt shoes);
}
public class ShoesCntAdapter extends RecyclerView.Adapter<ShoesCntAdapter.CustomViewHolder> {


    private final ClickListener listener;


    private ArrayList<Shoes_cnt> arrayList;
    private Context context;

    public ShoesCntAdapter(ArrayList<Shoes_cnt> arrayList,Context context,ClickListener listener) {
        this.listener = listener;
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ShoesCntAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoes_cnt_recyclerview,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view, listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesCntAdapter.CustomViewHolder holder, int position) {

        holder.shoesSize_txt.setText(arrayList.get(position).getShoesSize_txt());
        holder.shoesSize_cnt.setText(arrayList.get(position).getShoesSize_cnt().toString()); //이게 진짜 제일 중요하네
        holder.itemView.setTag(position);

        holder.x_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shoesSize_cnt.setText("1");
                arrayList.remove(holder.getAdapterPosition());
                //arrayList.get(holder.getAdapterPosition()).setShoesSize_cnt(1);
                //arrayList.get(holder.getAdapterPosition()).getShoesSize_cnt().toString()
                holder.listener.onDeleteClicked(new Shoes_cnt(holder.shoesSize_txt.getText().toString(),Integer.parseInt(holder.shoesSize_cnt.getText().toString())));
            }
        });

        holder.plus_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                arrayList.get(holder.getAdapterPosition()).setShoesSize_cnt(arrayList.get(holder.getAdapterPosition()).getShoesSize_cnt() + 1);

                holder.shoesSize_cnt.setText(arrayList.get(holder.getAdapterPosition()).getShoesSize_cnt().toString()); // 1값을 증가시킴

                holder.listener.onPlusClicked(new Shoes_cnt(holder.shoesSize_txt.getText().toString(),Integer.parseInt(holder.shoesSize_cnt.getText().toString())));
            }
        });

        holder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(holder.getAdapterPosition()).getShoesSize_cnt() > 1){
                    arrayList.get(holder.getAdapterPosition()).setShoesSize_cnt(arrayList.get(holder.getAdapterPosition()).getShoesSize_cnt() - 1);
                }
                else{
                    Toast.makeText(context.getApplicationContext(), "더 이상 줄일 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
                holder.shoesSize_cnt.setText(arrayList.get(holder.getAdapterPosition()).getShoesSize_cnt().toString());
                holder.listener.onMinusClicked(new Shoes_cnt(holder.shoesSize_txt.getText().toString(),Integer.parseInt(holder.shoesSize_cnt.getText().toString())));
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView shoesSize_txt,shoesSize_cnt;
        protected ImageButton x_btn;
        protected ImageButton plus_btn,minus_btn;
        private final ClickListener listener;

        public CustomViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.shoesSize_txt = (TextView) itemView.findViewById(R.id.shoesSize_txt);
            this.x_btn = (ImageButton) itemView.findViewById(R.id.x_btn);
            this.plus_btn = (ImageButton) itemView.findViewById(R.id.plus_btn);
            this.minus_btn = (ImageButton) itemView.findViewById(R.id.minus_btn);
            this.shoesSize_cnt = (TextView) itemView.findViewById(R.id.shoesSize_cnt);
            this.listener = listener;
        }
    }
}

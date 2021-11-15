package com.cookandroid.shoesforall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShoesCntAdapter extends RecyclerView.Adapter<ShoesCntAdapter.CustomViewHolder> {

    private ArrayList<Shoes_cnt> arrayList;
    private Context context;
    public ShoesCntAdapter(ArrayList<Shoes_cnt> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoesCntAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoes_cnt_recyclerview,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ShoesCntAdapter.CustomViewHolder holder, int position) {

        holder.shoesSize_txt.setText(arrayList.get(position).getShoesSize_txt());
        holder.shoesSize_cnt.setText(arrayList.get(position).getShoesSize_cnt());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.shoesSize_txt.getText().toString();
                Toast.makeText(v.getContext(),curName+"Hello",Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }
    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
            //리스트를 지우고 새로고침을 꼭 해줘야됨
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView shoesSize_txt,shoesSize_cnt;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.shoesSize_cnt = (TextView) itemView.findViewById(R.id.shoesSize_cnt);
            this.shoesSize_txt = (TextView) itemView.findViewById(R.id.shoesSize_txt);
        }
    }
}

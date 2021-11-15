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

    void onPlusClicked(int position,String ssize);

    void onMinusClicked(int position,String ssize);

    void onDeleteClicked(int position,String ssize);
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
        holder.shoesSize_cnt.setText(arrayList.get(position).getShoesSize_cnt());
        holder.itemView.setTag(position);


        holder.x_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                remove(holder.getAdapterPosition());
                holder.listener.onDeleteClicked(holder.getAdapterPosition(),holder.shoesSize_txt.getText().toString());
            }
        });

        holder.plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.listener.onDeleteClicked(holder.getAdapterPosition(),holder.shoesSize_txt.getText().toString());
            }
        });

        holder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.listener.onDeleteClicked(holder.getAdapterPosition(),holder.shoesSize_txt.getText().toString());
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


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView shoesSize_txt,shoesSize_cnt;
        protected ImageButton x_btn;
        protected ImageButton plus_btn,minus_btn;
        private final ClickListener listener;
       // protected TextView shoesize_txt;

//        protected ArrayList<Shoes_cnt> shoes_list;
//        protected ShoesCntAdapter shoesCntAdapter;
//        protected RecyclerView shoes_cnt_recyclerview;
//        protected Spinner spinner;
//        HashMap<String,Integer> size_map = new HashMap<String,Integer>();

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

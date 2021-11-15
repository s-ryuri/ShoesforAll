package com.cookandroid.shoesforall;


import static android.widget.ArrayAdapter.createFromResource;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class detail_page extends AppCompatActivity {

    private ImageView detail_image;
    private TextView shoesName_txt,price_txt,description_txt,information_txt,shoesize_txt,total_shoes_cost;
    private ImageButton back_btn,home_detail_btn;
    private String imgUrl;
    private Spinner spinner;

    private ImageButton x_btn;
    private Button buying_btn,btnOk;

    private ArrayList<Shoes_cnt> shoes_list;
    private ShoesCntAdapter shoesCntAdapter;
    private RecyclerView shoes_cnt_recyclerview;
    HashMap<String,Integer> size_map = new HashMap<String,Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        Intent intent = getIntent();

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        detail_image = (ImageView) findViewById(R.id.detail_image);
        shoesName_txt = (TextView) findViewById(R.id.shoesName_txt);
        price_txt = (TextView) findViewById(R.id.price_txt);
        description_txt = (TextView) findViewById(R.id.description_txt);
        information_txt = (TextView) findViewById(R.id.information_txt);
        home_detail_btn = (ImageButton)findViewById(R.id.home_detail_btn);

        buying_btn = (Button)findViewById(R.id.buying_btn);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_order_bot_sheet,null,false);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);

        shoes_cnt_recyclerview = view.findViewById(R.id.shoes_cnt_recyclerview);
        shoes_cnt_recyclerview.setHasFixedSize(true);

        shoes_list = new ArrayList<Shoes_cnt>();
//        shoesCntAdapter = new ShoesCntAdapter(shoes_list,this);

        shoesCntAdapter = new ShoesCntAdapter(shoes_list,getApplicationContext(),new ClickListener(){
            @Override
            public void onDeleteClicked(int position,String ssize) {

                shoes_list.remove(position);
                shoesCntAdapter.notifyDataSetChanged();
                size_map.remove(ssize);
                shoesize_txt.setText("상품 " + Integer.toString(shoes_list.size())+"개");
                total_shoes_cost.setText(Integer.toString(shoes_list.size() * Integer.parseInt(price_txt.getText().toString())) + "원");

            }

            @Override
            public void onPlusClicked(int position,String ssize) {

            }

            @Override
            public void onMinusClicked(int position,String ssize) {

            }
        });
        shoes_cnt_recyclerview.setAdapter(shoesCntAdapter);


        //신발 이름이랑, 신발 가격 초기화
        shoesize_txt = view.findViewById(R.id.shoesize_txt);
        total_shoes_cost = view.findViewById(R.id.total_shoes_cost);


        buying_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });


        imgUrl = intent.getStringExtra("IMAGE"); //링크로 받음
        Glide.with(this).load(imgUrl).into(this.detail_image); //로드해서 이미지에 올린다

        shoesName_txt.setText(intent.getStringExtra("NAME"));
        price_txt.setText(intent.getStringExtra("PRICE"));
        description_txt.setText(intent.getStringExtra("DESCRIPTION"));
        information_txt.setText(intent.getStringExtra("INFORMATION"));


        btnOk = view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_SHORT).show();
                //size_map.clear();
                bottomSheetDialog.dismiss();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(detail_page.this,shoes_home.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent1);
            }
        });

        home_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(detail_page.this,shoes_home.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent1);
            }
        });


        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.test,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s_size = parent.getItemAtPosition(position).toString();
                if(position == 0){
                    return;
                }else{
                    if(size_map.containsKey(s_size)){
                        //이미 가지고 있다면
                        AlertDialog.Builder ad = new AlertDialog.Builder(detail_page.this);
                        ad.setMessage("이미 선택되어 있는 옵션입니다.");// 다시 사이즈 선택으로
                        spinner.setSelection(0);
                        ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        ad.show();
                    }else{
                        //가지고 있지 않으면 리스트뷰에 추가
                        spinner.setSelection(0);// 다시 사이즈 선택으로
                        size_map.put(s_size,1);
                        Shoes_cnt shoes_size_cnt = new Shoes_cnt(s_size,"1");
                        shoes_list.add(shoes_size_cnt);
                        shoesCntAdapter.notifyDataSetChanged();
                        shoesize_txt.setText("상품 " + Integer.toString(shoes_list.size())+"개");
                        total_shoes_cost.setText(Integer.toString(shoes_list.size() * Integer.parseInt(price_txt.getText().toString())) + "원");

                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),"넌 뭐냐?",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
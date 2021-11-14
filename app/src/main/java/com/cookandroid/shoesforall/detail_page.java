package com.cookandroid.shoesforall;


import static android.widget.ArrayAdapter.createFromResource;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
    private ArrayAdapter<CharSequence> sizeAdapter;
    private Button buying_btn,btnOk,plus_btn;
    private ListView listView1;
    HashMap<String,Integer> size_map = new HashMap<String,Integer>();
    final ArrayList<String> items = new ArrayList<String>();
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

        listView1 = view.findViewById(R.id.listview1);

        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView1.setAdapter(listadapter);





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
                size_map.clear();
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


        //spinner.setSelection(1); //스피너 처음 선택했을 때 뜨는 화면
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
                        //Toast.makeText(getApplication(),Integer.toString(size_map.size()),Toast.LENGTH_SHORT).show();
                        items.add(s_size.toString());
                        listadapter.notifyDataSetChanged();
                        shoesize_txt.setText("상품 " + Integer.toString(items.size())+"개");
                        total_shoes_cost.setText(Integer.toString(items.size() * 156000) + "원");
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
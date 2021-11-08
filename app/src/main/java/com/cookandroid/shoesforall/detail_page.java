package com.cookandroid.shoesforall;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;



public class detail_page extends AppCompatActivity {

    private ImageView detail_image;
    private TextView shoesName_txt,price_txt,description_txt,information_txt;
    private ImageButton back_btn,home_detail_btn;
    private Button buying_btn;
    private String imgUrl;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        Intent intent = getIntent();

        buying_btn = (Button)findViewById(R.id.buying_btn);
        back_btn = (ImageButton)findViewById(R.id.back_btn);
        detail_image = (ImageView) findViewById(R.id.detail_image);
        shoesName_txt = (TextView) findViewById(R.id.shoesName_txt);
        price_txt = (TextView) findViewById(R.id.price_txt);
        description_txt = (TextView) findViewById(R.id.description_txt);
        information_txt = (TextView) findViewById(R.id.information_txt);
        home_detail_btn = (ImageButton)findViewById(R.id.home_detail_btn);
        spinner = (Spinner) findViewById(R.id.spinner);



        imgUrl = intent.getStringExtra("IMAGE"); //링크로 받음
        Glide.with(this).load(imgUrl).into(this.detail_image); //로드해서 이미지에 올린다

        shoesName_txt.setText(intent.getStringExtra("NAME"));
        price_txt.setText(intent.getStringExtra("PRICE"));
        description_txt.setText(intent.getStringExtra("DESCRIPTION"));
        information_txt.setText(intent.getStringExtra("INFORMATION"));

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
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.order_bottom_sheet,null,false);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);

        buying_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

    }
}
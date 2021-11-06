package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class detail_page extends AppCompatActivity {
    private ViewFlipper viewFlipper1;
    private ImageView detail_image;
    private TextView shoesName_txt,price_txt,description_txt,information_txt;
    private ImageButton back_btn,home_detail_btn;
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

        //detail_image.setImageBitmap(intent.getParcelableExtra("IMAGE"));
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


    }
}
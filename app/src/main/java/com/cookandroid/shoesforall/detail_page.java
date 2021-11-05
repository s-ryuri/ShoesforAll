package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class detail_page extends AppCompatActivity {
    private ViewFlipper viewFlipper1;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        btn1 = (Button) findViewById(R.id.btn1);
        Intent intent = getIntent();
        btn1.setText(intent.getStringExtra("TEXT"));

    }
}
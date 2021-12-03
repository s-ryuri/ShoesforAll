package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class order_page extends AppCompatActivity {


    private Spinner delivery_spinner;
    private EditText order_edt;
    private ArrayAdapter<CharSequence> delivery_adapter;
    private TextView buyerName, order_page_shoes_price,order_page_shoes_cnt,order_page_shoes_size,order_page_shoes_name;
    private Button order_buy_btn;
    private TabHost tabHost;
    private String data;
    private ImageView order_page_shoes_picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        order_buy_btn = (Button)findViewById(R.id.order_buy_btn);
        order_page_shoes_price = (TextView)findViewById(R.id.order_page_shoes_price);
        order_page_shoes_cnt = (TextView)findViewById(R.id.order_page_shoes_cnt);
        order_page_shoes_size = (TextView)findViewById(R.id.order_page_shoes_size);
        order_page_shoes_name = (TextView) findViewById(R.id.order_page_shoes_name);
        buyerName = (TextView) findViewById(R.id.buyerName);
        order_page_shoes_picture = (ImageView) findViewById(R.id.order_page_shoes_picture);
        order_edt = (EditText)findViewById(R.id.order_edt);

        delivery_spinner = (Spinner) findViewById(R.id.delivery_spinner);
        delivery_adapter = ArrayAdapter.createFromResource(this,R.array.delivery, R.layout.order_spinner_style);
        delivery_adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        delivery_spinner.setAdapter(delivery_adapter);

        Intent delivery_intent = getIntent();
        data = delivery_intent.getStringExtra("total_cost");
        String total_count = delivery_intent.getStringExtra("total_count");
        order_page_shoes_price.setText(data+"");
        order_page_shoes_cnt.setText("수량 : "+total_count+"개");
        order_buy_btn.setText(data +"원 결제하기");
        order_page_shoes_name.setText(delivery_intent.getStringExtra("shoes_name"));


        Glide.with(this).load(delivery_intent.getStringExtra("shoes_image")).override(140,140).into(this.order_page_shoes_picture); //로드해서 이미지에 올린다

        String options = "옵션 : ";
        String total_shoes_cnt = delivery_intent.getStringExtra("shoes_count");
        for(int i = 0;i<Integer.parseInt(total_shoes_cnt);i++){
            options += delivery_intent.getStringExtra("shoes_size" + Integer.toString(i+1));
            if(i != Integer.parseInt(total_shoes_cnt) - 1){
                options += ", ";
            }

        }
        order_page_shoes_size.setText(options);


        delivery_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 6){
                    order_edt.setVisibility(View.VISIBLE);
                }else{
                    order_edt.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec ts1 = tabHost.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("환불") ;
        tabHost.addTab(ts1)  ;

        TabHost.TabSpec ts2 = tabHost.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("교환") ;
        tabHost.addTab(ts2)  ;

    }


    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.order_buy_btn:
                intent = new Intent(order_page.this, PayActivity.class);
                intent.putExtra("TYPE", "BUY");
                intent.putExtra("PRICE", data);
                intent.putExtra("BUYER", buyerName.getText());

                intent.putExtra("ITEM", order_page_shoes_name.getText());
                startActivity(intent);
                break;
        }

    }
}
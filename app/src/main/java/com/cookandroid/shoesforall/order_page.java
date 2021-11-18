package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class order_page extends AppCompatActivity {


    private Spinner delivery_spinner;
    private EditText order_edt;
    private ArrayAdapter<CharSequence> delivery_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        order_edt = (EditText)findViewById(R.id.order_edt);
        order_edt.setSelection(0);
        delivery_spinner = (Spinner) findViewById(R.id.delivery_spinner);
        delivery_adapter = ArrayAdapter.createFromResource(this,R.array.delivery, R.layout.order_spinner_style);
        //
        delivery_adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        delivery_spinner.setAdapter(delivery_adapter);


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



    }
}
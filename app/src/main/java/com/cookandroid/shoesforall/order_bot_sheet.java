package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class order_bot_sheet extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter<CharSequence> sizeAdapter;
    private TextView shoesize_txt;
    private ImageButton plus_btn;
    private String[] items = {"A","B","C"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_bot_sheet);

        shoesize_txt = (TextView)findViewById(R.id.shoesize_txt);
        plus_btn = (ImageButton)findViewById(R.id.plus_btn);


        spinner = (Spinner) findViewById(R.id.spinner);
//        sizeAdapter = ArrayAdapter.createFromResource(this,R.array.test,android.R.layout.simple_spinner_dropdown_item);
//
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
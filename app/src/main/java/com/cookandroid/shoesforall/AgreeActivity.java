package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;

public class AgreeActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private Button backButton;
    private Button emailSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree);

        backButton = (Button) findViewById(R.id.backButton);
        emailSignupButton = (Button) findViewById(R.id.emailSignupButton);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        emailSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(AgreeActivity.this, SignUpActivity.class);
                startActivity(nextIntent);
                finish();
            }
        });

        setCheckboxListener();

    }

    private void setCheckboxListener() {
        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                    checkBox3.setChecked(true);
                    checkBox4.setChecked(true);
                    checkBox5.setChecked(true);
                } else {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    checkBox5.setChecked(false);
                }
                checkChecked();
            }
        });


        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox1.isChecked()) {
                    checkChecked(); }
                else {checkBox.setChecked(false);
                    checkChecked(); }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox2.isChecked()) {
                    checkChecked(); }
                else {checkBox.setChecked(false);
                    checkChecked(); }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox3.isChecked()) {
                    checkChecked(); }
                else {checkBox.setChecked(false);
                    checkChecked(); }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox4.isChecked()) {
                    checkChecked(); }
                else {checkBox.setChecked(false);
                    checkChecked(); }
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox5.isChecked()) {
                    checkChecked(); }
                else {checkBox.setChecked(false);
                    checkChecked(); }
            }
        });
    }

    private void checkChecked() {
        String color;


        if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()
                && checkBox4.isChecked() && checkBox5.isChecked()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        if (checkBox1.isChecked() && checkBox2.isChecked()
                && checkBox3.isChecked() && checkBox4.isChecked()) {
            emailSignupButton.setEnabled(true);
            color = "#6782b7";
        } else {
            emailSignupButton.setEnabled(false);
            color = "#aaaaaa";
        }

        emailSignupButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.arrow1:
                Intent intent = new Intent(AgreeActivity.this, WebViewActivity.class);
                intent.putExtra("TYPE", "AGREE");
                intent.putExtra("NUMBER", 1);
                startActivity(intent);
                break;
            case R.id.arrow2:
                intent = new Intent(AgreeActivity.this, WebViewActivity.class);
                intent.putExtra("TYPE", "AGREE");
                intent.putExtra("NUMBER", 2);
                startActivity(intent);

            case R.id.arrow3:
                intent = new Intent(AgreeActivity.this, WebViewActivity.class);
                intent.putExtra("TYPE", "AGREE");
                intent.putExtra("NUMBER", 3);
                startActivity(intent);
                break;
            case R.id.arrow4:
                intent = new Intent(AgreeActivity.this, WebViewActivity.class);
                intent.putExtra("TYPE", "AGREE");
                intent.putExtra("NUMBER", 4);
                startActivity(intent);
                break;
            case R.id.arrow5:
                intent = new Intent(AgreeActivity.this, WebViewActivity.class);
                intent.putExtra("TYPE", "AGREE");
                intent.putExtra("NUMBER", 5);
                startActivity(intent);
                break;
        }

    }
}
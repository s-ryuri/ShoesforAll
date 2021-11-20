package com.cookandroid.shoesforall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class FindPasswordActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private CardView findPasswordButton;
    private TextInputEditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        findPasswordButton = (CardView) findViewById(R.id.findPasswordButton);
        emailEditText = (TextInputEditText) findViewById(R.id.emailEditText);

        findPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth = FirebaseAuth.getInstance();

                String emailAddress = emailEditText.getText().toString();

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(FindPasswordActivity.this, "비밀번호 재설정을 위해 이메일 인증을 부탁드립니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(FindPasswordActivity.this, "존재하지 않는 계정입니다.", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });
    }
}
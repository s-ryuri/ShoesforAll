package com.cookandroid.shoesforall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button backButton;
    private Button emailSignupButton;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backButton = (Button) findViewById(R.id.backButton);
        emailSignupButton = (Button) findViewById(R.id.emailSignupButton);
        emailEditText = (TextInputEditText) findViewById(R.id.emailEditText);
        passwordEditText = (TextInputEditText) findViewById(R.id.passwordEditText);

        auth = FirebaseAuth.getInstance();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(nextIntent);
                finish();
            }
        });

        emailSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupEmail();
            }
        });
    }

    private void signupEmail() {
        auth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUpActivity.this, "회원가입에 성공하셨습니다. 이메일 인증을 받아주세요.", Toast.LENGTH_LONG).show();
                            sendVerificationEmail();
                        } else {// If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "회원가입 실패.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void sendVerificationEmail() {
        FirebaseUser user = auth.getCurrentUser();
        String url = "https://us-central1-alicorn-ff5a3.cloudfunctions.net/activateUser?uid=" + user.getUid();
        Log.e("url", url);

        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                        .setUrl(url)
                        .build();


        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                        }
                    }
                });
    }
}
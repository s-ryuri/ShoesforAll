package com.cookandroid.shoesforall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignUp2Activity extends AppCompatActivity {

    private static final String TAG = "test";
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private Button backButton;
    private Button SignupButton;
    private TextInputEditText nameEditText;
    private TextInputEditText phoneNumberEditText;
    private TextInputEditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        backButton = (Button) findViewById(R.id.backButton);
        SignupButton = (Button) findViewById(R.id.SignupButton);
        nameEditText = (TextInputEditText) findViewById(R.id.nameEditText);
        phoneNumberEditText = (TextInputEditText) findViewById(R.id.phoneNumberEditText);
        addressEditText = (TextInputEditText) findViewById(R.id.addressEditText);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUp2Activity.this, "이 페이지에선 뒤로 가실 수 없습니다.", Toast.LENGTH_LONG).show();

            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> data = new HashMap<>();
                data.put("name", nameEditText.getText().toString());
                data.put("phoneNumber", phoneNumberEditText.getText().toString());
                data.put("address", addressEditText.getText().toString());
                data.put("timestamp", new Timestamp(new Date()));

                db.collection("Users").document(auth.getUid())
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignUp2Activity.this, "회원가입을 축하합니다. 즐거운 쇼핑 되십시오.", Toast.LENGTH_LONG).show();
                                Intent intent;
                                intent = new Intent(SignUp2Activity.this,shoes_home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "Error writing document", e);
                            }
                        });
            }
        });
    }
}
package com.cookandroid.shoesforall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    int GOOGLE_LOGIN_CODE = 9001;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInClient signInIntent;

    Button emailEditText = findViewById(R.id.emailEditText);
    Button passwordEditText = findViewById(R.id.passwordEditText);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth.getInstance();

        Button googleLoginButton = findViewById(R.id.googleLoginButton);
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogin();
                //로그인 해서 shoes_home으로 감
            }

        });
        Button emailLoginButton = findViewById(R.id.emailLoginButton);
        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 해서 shoes_home으로 감
                signinEmail();
                Intent intent = new Intent(LoginActivity.this,shoes_home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }

        });
        Button emailSignupButton = findViewById(R.id.emailSignupButton);
        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //startActivity(Intent(this, AgreeActivity::class.java))
            }

        });
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("565770065097-ras64cra3is3ich0a63eufk6bnbpr6t5.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    /*public void onResume() {
        super.onResume()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth?.currentUser
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid).get().addOnSuccessListener {
                val userData = it.toObject<UserDTO>()
                val provider = try {
                    currentUser.providerData[1].providerId
                } catch (e: Exception) {
                    currentUser.providerData[0].providerId
                }
                Log.e("${currentUser.uid}", userData?.activation.toString())

                if (provider == "password") {
                    if (userData?.activation == true) {
                        moveMainpage(currentUser)
                    } else { Toast.makeText(this, "회원가입을 위해 이메일 인증을 부탁드립니다.", Toast.LENGTH_LONG).show()

                    }
                } else {

                    //  moveMainpage(currentUser)
                }
                //db.collection("users").document(currentUser.uid).update()
                //  moveMainpage(currentUser)
                //Log.e(auth?.currentUser?.providerId, current)
            }
        }
    }
    */


    public void googleLogin() {
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 구글로그인 버튼 응답
        if (requestCode == GOOGLE_LOGIN_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // 구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                             moveMainpage(user);
                        }
                        else {
                            // 로그인 실패
                            //Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
    public void signinEmail() {

    }


    public void  moveMainpage(FirebaseUser user) {
            finish();
    }

}
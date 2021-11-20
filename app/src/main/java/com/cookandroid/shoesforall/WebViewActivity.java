package com.cookandroid.shoesforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    Intent intent;
    private static final String TAG = "WebView";
    private String type;
    private Integer number;
    private String url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        intent = getIntent();

        webView = (WebView) findViewById(R.id.webView);
        type = intent.getStringExtra("TYPE");
        number = intent.getIntExtra("NUMBER", 0);
        Toast.makeText(WebViewActivity.this, type, Toast.LENGTH_LONG).show();

        setWebView();
    }

    private void setWebView() {
            switch (number) {
                case 1:
                    url = "https://www.drugunicorn.com/agree.html";
                    break;
                case 2:
                    url = "https://www.drugunicorn.com/agree.html";
                    break;
                case 3:
                    url = "https://www.drugunicorn.com/agree.html";
                    break;
                case 4:
                    url = "https://www.drugunicorn.com/agree.html";
                    break;
                case 5:
                    url = "https://www.drugunicorn.com/agree.html";
                    break;
                default:
                    url = "https://m.google.com/";
                    break;
            }
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient()); // 현재 앱을 나가서 새로운 브라우저를 열지 않도록 함.
        webView.setWebChromeClient(new WebChromeClient());
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButton:
                finish();
        }
    }
}
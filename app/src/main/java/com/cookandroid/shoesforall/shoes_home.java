package com.cookandroid.shoesforall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.method.Touch;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class shoes_home extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction ft;
    private FragmentManager fm;

    private brand_fragment brand;
    private home_fragment home;
    private like_fragment like;
    private menu_fragment menu_search;
    private mypage_fragment mypage;

    private long backBtnTime = 0;

    @Override
    public void onBackPressed() {
        //뒤로가기 기능 활성화
        long curTime = System.currentTimeMillis(); //현재 시간을 밀리초로
        long gapTime = curTime - backBtnTime; //시간 간격

        if(gapTime >= 0 && gapTime <= 2000){
            ActivityCompat.finishAffinity(this); //액티비티 종료
            System.exit(0); // 프로세스 종료
        }else{
            //한 번 눌렀을 때
            backBtnTime = curTime; //현재 시간으로
            Toast.makeText(getApplicationContext(),"한 번 더 누르면 종료됩니다.!",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavi);
        bottomNavigationView.setSelectedItemId(R.id.home_btn);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.menu_search_btn:
                        setFrag(0);
                        break;
                    case R.id.brand_btn:
                        setFrag(1);
                        break;
                    case R.id.home_btn:
                        setFrag(2);
                        break;
                    case R.id.like_btn:
                        setFrag(3);
                        break;
                    case R.id.my_page_btn:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

        brand = new brand_fragment();
        home = new home_fragment();
        like = new like_fragment();
        menu_search = new menu_fragment();
        mypage = new mypage_fragment();
        setFrag(2);



    }
    //fragment 교체 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //프래그먼트 교체
        switch(n){
            case 0:
                ft.replace(R.id.main_frame,menu_search);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,brand);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,home);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame,like);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame,mypage);
                ft.commit();
                break;
        }
    }
}
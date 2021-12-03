package com.cookandroid.shoesforall;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends AppCompatActivity {
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
          // 검색어를 입력할 Input 창
    private ArrayAdapter<String> adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private EditText search_screen_edt;
    private TextView cancel_btn,popular;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);
        search_screen_edt = (EditText) findViewById(R.id.search_screen_edt);
        search_screen_edt.requestFocus();

        search_screen_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.VISIBLE);
                popular.setVisibility(View.VISIBLE);
            }

        });
        search_screen_edt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        Intent intent = new Intent(getApplicationContext(),ShowShoesBrand.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("brand","nike");
                        search_screen_edt.setText(null);
                        startActivity(intent);
                        break;
                    case KeyEvent.KEYCODE_DEL:

                        break;
                }
                return true;
            }
        });
        search_screen_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = search_screen_edt.getText().toString();
                search(text);
            }
        });
        popular = (TextView)findViewById(R.id.popular);
        cancel_btn = (TextView)findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                popular.setVisibility(View.GONE);
                search_screen_edt.requestFocus();
                search_screen_edt.setText(null);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = parent.getItemAtPosition(position).toString();
                //search_screen_edt.setText(data);
                Intent intent = new Intent(getApplicationContext(),ShowShoesBrand.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("brand","nike");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        //토스트 메시지
        finish();   //현재 액티비티 종료
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList(){
        list.add("나이키");
        list.add("아디다스");
        list.add("쿠션화");
        list.add("제어화");
        list.add("아식스");
        list.add("줌 페가수스");
        list.add("데상트");
    }
}
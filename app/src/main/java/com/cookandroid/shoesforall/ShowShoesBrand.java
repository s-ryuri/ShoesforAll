package com.cookandroid.shoesforall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class ShowShoesBrand extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView menu_recyclerView;
    private RecyclerView.Adapter menu_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MenuButton> menu_arraylist;
    private ProgressDialog progressDialog;
    private String []shoesName = {"control_shoes","cushion_shoes","stabilization_shoes"};
    private String findItem;
    private String whereString,target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shoes_brand);

        Intent getIntent = getIntent();

        findItem = getIntent().getStringExtra("brand");
        Log.d("신발값","result : " + findItem);
        target = "brand";
        if(findItem == null){
            findItem = getIntent().getStringExtra("shoes");
            target = "shoes";
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("로딩중입니다");
        progressDialog.show();

        menu_recyclerView = (RecyclerView) findViewById(R.id.menu_recyclerView);
        menu_recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        menu_recyclerView.setLayoutManager(layoutManager);

        menu_arraylist = new ArrayList<MenuButton>();

        db = FirebaseFirestore.getInstance();
        menu_adapter = new MenuButtonAdapter(menu_arraylist,this);
        menu_recyclerView.setAdapter(menu_adapter);
        EventChangeListener();

    }
    private void EventChangeListener() {
        for(int i = 0; i<3;i++){
            final int index = i;
            db.collection(shoesName[index]).whereEqualTo(target,findItem)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if(error != null){
                                if(progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }
                                Log.e("Firestore error",error.getMessage());
                                return;
                            }
                            for(DocumentChange dc : value.getDocumentChanges()){
                                if(dc.getType() == DocumentChange.Type.ADDED){
                                    menu_arraylist.add(dc.getDocument().toObject(MenuButton.class));
                                }
                                menu_adapter.notifyDataSetChanged();
                                if(progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }
                            }
                        }
                    });
        }
    }

}
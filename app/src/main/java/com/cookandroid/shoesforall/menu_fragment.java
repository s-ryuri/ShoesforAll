package com.cookandroid.shoesforall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

//여기서 누르면 ShowMenuShoes로
public class menu_fragment extends Fragment {
    private View view;

    private ImageView[] btn = new ImageView[6];
    private Integer[] btnID= {R.id.adidasBtn,R.id.descenteBtn,R.id.miznoBtn,R.id.asicsBtn,R.id.brooksBtn,R.id.sauconyBtn};
    private String [] brandName = {"adidas","descente","mizno","asics","brooks","saucony"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_search,container,false);

        for(int i = 0;i<6;i++){
            final int index = i;
            btn[index] = (ImageView) view.findViewById(btnID[index]);
            btn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ShowShoesBrand.class);
                    intent.putExtra("brand",brandName[index]);
                    startActivity(intent);
                }
            });
        }
//        btn[0] = (Button)view.findViewById(R.id.adidasBtn);
//        adidasBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //EventChangeListener();
//                Intent intent = new Intent(getActivity(),ShowShoesBrand.class);
//                intent.putExtra("brand",adidasBtn.getText().toString());
//                startActivity(intent);
//            }
//        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}

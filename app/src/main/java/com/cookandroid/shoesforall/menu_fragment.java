package com.cookandroid.shoesforall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;

//여기서 누르면 ShowMenuShoes로
public class menu_fragment extends Fragment {
    private View view;

    private ImageView[] btn = new ImageView[6];
    private Integer[] btnID= {R.id.adidasBtn,R.id.descenteBtn,R.id.miznoBtn,R.id.asicsBtn,R.id.brooksBtn,R.id.sauconyBtn};
    private String [] brandName = {"adidas","descente","mizno","asics","brooks","saucony"};

    private TextView[] btnTxt = new TextView[3];
    private Integer[] btnTxtID = {R.id.control_shoes,R.id.cushion_shoes,R.id.stablize_shoes};
    private String [] ShoesName = {"control_shoes","cushion_shoes","stabilization_shoes"};
    private TextView member,cancel_btn;
    private EditText editSearch;

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
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            });
        }

        for(int i = 0;i<3;i++){
            final int index = i;
            btnTxt[index] = (TextView) view.findViewById(btnTxtID[index]);
            btnTxt[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ShowShoesBrand.class);
                    intent.putExtra("shoes",ShoesName[index]);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            });
        }

        editSearch = (EditText) view.findViewById(R.id.editSearch);
        editSearch.requestFocus();
        cancel_btn = (TextView)view.findViewById(R.id.cancel_btn);

        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}

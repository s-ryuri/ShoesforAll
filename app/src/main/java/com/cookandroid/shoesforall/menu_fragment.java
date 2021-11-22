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

    private Button[] btn = new Button[4];
    private Integer[] btnID= {R.id.adidasBtn,R.id.desenteBtn,R.id.miznoBtn,R.id.asicsBtn};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_search,container,false);

        for(int i = 0;i<4;i++){
            final int index = i;
            btn[index] = (Button)view.findViewById(btnID[index]);
            btn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ShowShoesBrand.class);
                    intent.putExtra("brand",btn[index].getText().toString());
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

package com.cookandroid.shoesforall;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class home_fragment extends Fragment {

    private View view;

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Shoes> arrayList;
    private ProgressDialog progressDialog;
    private String []shoesName = {"control_shoes","cushion_shoes","stabilization_shoes"};
    private String TAG = "heee";
    private ImageView[] brandBtn = new ImageView[6];
    private Integer[] btnID = {R.id.category_adidas,R.id.category_asics,R.id.category_mizno,R.id.category_descente,R.id.category_nike,R.id.category_brooks,R.id.category_saucony};
    private String [] brandName = {"adidas","asics","mizno","descente","nike","brooks","saucony"};
    private TextView member;
    private ImageButton search_btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home,container,false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("로딩중");
        progressDialog.show();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        arrayList = new ArrayList<Shoes>();

        db = FirebaseFirestore.getInstance();
        adapter = new CustomAdapter(arrayList,getActivity());

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for(int i = 0;i<brandBtn.length;i++){
            final int index = i;
            brandBtn[index] = (ImageView) view.findViewById(btnID[index]);
            brandBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ShowShoesBrand.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("brand",brandName[index]);
                    startActivity(intent);
                }
            });
        }
        EventChangeListener();
        member = (TextView) view.findViewById(R.id.member);
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sminju98/ShoesforAll"));
                startActivity(intent);
            }
        });

        search_btn = (ImageButton) view.findViewById(R.id.search_btn);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return view;
    }
    private void EventChangeListener() {
        for(int i = 0; i<3;i++){
            final int index = i;
            db.collection(shoesName[index]).orderBy("brand", Query.Direction.ASCENDING)
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
                                    arrayList.add(dc.getDocument().toObject(Shoes.class));
                                }
                                adapter.notifyDataSetChanged();
                                if(progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }
                            }
                        }
                    });
        }



    }
}

package com.cookandroid.shoesforall;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class home_fragment extends Fragment {
    private View view;
    private ImageButton ultra_image;

    private ImageButton[] ultraImage = new ImageButton[7];
    final private int[] imageid = {R.id.ultra_image, R.id.ultra_image1, R.id.ultra_image2, R.id.ultra_image3, R.id.ultra_image4,R.id.ultra_image5,R.id.ultra_image6};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home,container,false);

        //버튼 여러 개 초기화
        for(int i = 0;i<7;i++){
            final int index = i;
            ultraImage[index] = (ImageButton) view.findViewById(imageid[i]);
            ultraImage[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),detail_page.class); //fragment라서 activity intent와는 다른 방식
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }
            });
        }

        return view;
    }
}

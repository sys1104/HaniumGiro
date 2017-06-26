package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by YSSONG on 2017-04-16.
 */

public class Tab2Chat extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2chat, container, false);
        Button b = (Button)rootView.findViewById(R.id.button_seeDetail);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               updateDetail();
            }
        }); //endOnClickListener
        return rootView;

    }//endOnCreate
    public void updateDetail() {
        Intent intent = new Intent(getActivity(), SeeDetail.class);
        startActivity(intent);
    }
}//endFragment

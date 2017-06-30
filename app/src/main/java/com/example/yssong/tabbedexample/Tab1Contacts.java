package com.example.yssong.tabbedexample;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

/**
 * Created by YSSONG on 2017-04-16.
 */

public class Tab1Contacts  extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bill_list, container, false);
        final  Button b = (Button)rootView.findViewById(R.id.friend1);

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
}

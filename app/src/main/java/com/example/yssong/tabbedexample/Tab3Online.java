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

public class Tab3Online extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3online, container, false);
        Button regisbtn = (Button)rootView.findViewById(R.id.register_account);
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_account();
            }
        });

        Button phisbtn = (Button)rootView.findViewById(R.id.pay_history);
        phisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay_history();
            }
        });

        Button opbtn = (Button)rootView.findViewById(R.id.option);
        opbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option();
            }
        }); //endOnClickListener
        return rootView;



    }//endOnCreate
    public void register_account() {
        Intent intent = new Intent(getActivity(), Register.class);
        startActivity(intent);
    }

    public void pay_history() {
        Intent intent = new Intent(getActivity(), PayHistory.class);
        startActivity(intent);
    }

    public void option() {
        Intent intent = new Intent(getActivity(), Option.class);
        startActivity(intent);
    }
}//endFragment
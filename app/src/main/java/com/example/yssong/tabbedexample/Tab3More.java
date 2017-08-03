package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;


/**
 * Created by YSSONG on 2017-04-16.
 */

public class Tab3More extends Fragment {
    private IntentIntegrator qrScan;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_more, container, false);
        Button regisbtn = (Button)rootView.findViewById(R.id.register_account);
        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_account();
            }
        });
        Button qrbtn = (Button)rootView.findViewById(R.id.qrCode);
        Button opbtn = (Button)rootView.findViewById(R.id.option);

        opbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option();
            }
        }); //endOnClickListener
        qrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QRCodeScanner.class);
                startActivity(intent);
            }
        }); //endOnClickListener
        return rootView;

    }//endOnCreate
    public void register_account() {
        Intent intent = new Intent(getActivity(), AccountList.class);
        startActivity(intent);
    }

    public void option() {
        Intent intent = new Intent(getActivity(), Option.class);
        startActivity(intent);
    }

}//endFragment

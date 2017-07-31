package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by YSSONG on 2017-04-16.
 */

public class Tab3More extends Fragment {

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
                qrCode();
            }
        }); //endOnClickListener
        return rootView;



    }//endOnCreate
    public void register_account() {
        Intent intent = new Intent(getActivity(), Register.class);
        startActivity(intent);
    }

    public void option() {
        Intent intent = new Intent(getActivity(), Option.class);
        startActivity(intent);
    }
    public void qrCode() {
        new IntentIntegrator(getActivity()).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode == 0) {

            if(resultCode == Activity.RESULT_OK)
            {
                String contents = data.getStringExtra("SCAN_RESULT");
                //위의 contents 값에 scan result가 들어온다.

                Toast toast = Toast.makeText(getContext(), contents, LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(contents));
                startActivity(intent);
            }
            else if(resultCode == Activity.RESULT_CANCELED)
            {
                Toast toast = Toast.makeText(getContext(), "error", LENGTH_LONG);
                toast.show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}//endFragment
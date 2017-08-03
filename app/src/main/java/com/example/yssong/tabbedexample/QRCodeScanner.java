package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by thddu on 2017-08-03.
 */

public class QRCodeScanner extends Activity {
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_scanner);
        Button goBack =(Button)findViewById(R.id.go_back);
        qrScan = new IntentIntegrator(this);
        //scan option
        qrScan.setPrompt("Scanning...");
        //qrScan.setOrientationLocked(false);
        qrScan.initiateScan();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        AfterLogin.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QRCodeScanner.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(QRCodeScanner.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
                    startActivity(intent);
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

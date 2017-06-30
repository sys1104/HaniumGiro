package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by YSSONG on 2017-06-08.
 */

public class EnterPwd extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_pwd);
        Button b = (Button) findViewById(R.id.OK);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        SeeDetail.class); // 다음 넘어갈 클래스 지정
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); //Back Stack 초기화
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // 현재화면이 RootTask가 됨
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
}

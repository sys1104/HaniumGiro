package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by JIHYUN2 on 2017-06-08.
 * 납부후 간편비밀버호를 입력처리를 하는 클래스
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
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });

    }
/*
   public void onClick(View v){
       Toast.makeText(this,"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
   }
   */

}

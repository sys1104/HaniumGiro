package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by thddu on 2017-06-29.
 */

public class RegisterEasyPW extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_register_easypw);
        Button btnNext = (Button) findViewById(R.id.btnOK);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        RegisterEasyPWAgain.class); // 다음 넘어갈 클래스 지정
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); //Back Stack 초기화
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // 현재화면이 RootTask가 됨
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
}

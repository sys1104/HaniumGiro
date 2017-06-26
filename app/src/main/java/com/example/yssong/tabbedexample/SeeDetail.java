package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by YSSONG on 2017-06-07.
 */

public class SeeDetail extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seedetail);
        Button b =(Button)findViewById(R.id.button_Pay);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        EnterPwd.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
}

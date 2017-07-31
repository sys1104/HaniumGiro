package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by LimHJ on 2017-06-25.
 */

public class RegisterAccountOK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_account_ok);

        Button backbtn=(Button) findViewById(R.id.go_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(RegisterAccountOK.this, AccountList.class);
                backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // ABCDE순서로 엑티비티가 스택에 있을때
                startActivity(backIntent);                              // 엑티비티 E에서 C를 호출하면 DE는 삭제되고 스택에서는 ABC만 존재
                finish();
            }
        });

    }
}
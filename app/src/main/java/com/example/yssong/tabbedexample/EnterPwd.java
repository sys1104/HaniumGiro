package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by JIHYUN2 on 2017-06-08.
 * 납부후 간편비밀버호를 입력처리를 하는 클래스
 */

public class EnterPwd extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_pwd);
        Button b = (Button) findViewById(R.id.OK);

        Intent intent = this.getIntent();
        final String b_type = intent.getStringExtra("b_type");
        final String price = intent.getStringExtra("price");
        final String year_month = intent.getStringExtra("year_month");
        final String due = intent.getStringExtra("due");
        final String c_account = intent.getStringExtra("c_account");
        final String c_giroid = intent.getStringExtra("c_giroid");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        BillView.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("b_type",b_type);
                intent.putExtra("price",price);
                intent.putExtra("year_month",year_month);
                intent.putExtra("due",due);
                intent.putExtra("c_account",c_account);
                intent.putExtra("c_giroid",c_giroid);

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

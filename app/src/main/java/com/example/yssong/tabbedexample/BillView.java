package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by YSSONG on 2017-06-07.
 */

public class BillView extends AppCompatActivity {

    TextView textView1;
    TextView textView2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_view);
        Button b = (Button) findViewById(R.id.button_seeDetail);
//        Button home = (Button) findViewById(R.id.home);

        textView1 = (TextView) findViewById(R.id.textView01);
        textView2 = (TextView) findViewById(R.id.textView02);

        Intent intent = this.getIntent();
        final String b_type = intent.getStringExtra("b_type");
        final String price = intent.getStringExtra("price");
        final String year_month = intent.getStringExtra("year_month");
        final String due = intent.getStringExtra("due");
        final String c_account = intent.getStringExtra("c_account");
        final String c_giroid = intent.getStringExtra("c_giroid");



        textView1.setText(b_type);
        textView2.setText(year_month.substring(5,7)+ " 월\n" + b_type + " 요금은\n" + price + "원 입니다.");


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        BillDetail.class); // 다음 넘어갈 클래스 지정
                intent.putExtra("b_type",b_type);
                intent.putExtra("price",price);
                intent.putExtra("year_month",year_month);
                intent.putExtra("due",due);
                intent.putExtra("c_account",c_account);
                intent.putExtra("c_giroid",c_giroid);

                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });


//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        AfterLogin.class); // 다음 넘어갈 클래스 지정
//                startActivity(intent); // 다음 화면으로 넘어간다
//            }
//        });

    }
}

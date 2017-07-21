package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JIHYUN2 on 2017-06-29.
 */

public class BillDetail extends AppCompatActivity{

    TextView textView1;
    TextView textView2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_detail);
        Button b = (Button) findViewById(R.id.button_Pay);

        textView1 = (TextView) findViewById(R.id.detailView01);
        textView2 = (TextView) findViewById(R.id.detailView02);

        Intent intent = this.getIntent();
        final String b_type = intent.getStringExtra("b_type");
        final String price = intent.getStringExtra("price");
        final String year_month = intent.getStringExtra("year_month");
        final String due = intent.getStringExtra("due");
        final String c_account = intent.getStringExtra("c_account");
        final String c_giroid = intent.getStringExtra("c_giroid");

        textView1.setText(b_type);
        textView2.setText("<전자납부번호 : " + c_giroid+
                            ">\n" + year_month.substring(5,7)+ " 월 " + b_type + " 요금은\n" + price + "원 입니다.\n" +
                            "<납부계좌번호 : " +c_account +
                            ">\n<납부기한 : " +due +
                            "> \n<지로 발행일 : " + year_month +">");


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

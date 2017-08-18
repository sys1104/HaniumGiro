package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JIHYUN2 on 2017-06-08.
 * 납부후 간편비밀버호를 입력처리를 하는 클래스
 */

public class EnterPwd extends AppCompatActivity {

    loadJsp task;
    MainActivity temp = new MainActivity();
    String ID = temp.u_id;

    String b_type = "";
    String price="";
    String bill_id="";
    final String p_date = DateFormat.getDateTimeInstance().format(new Date());


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_pwd);
        Button b = (Button) findViewById(R.id.OK);

        Intent intent = this.getIntent();
        b_type = intent.getStringExtra("b_type");
        price = intent.getStringExtra("price");
        final String year_month = intent.getStringExtra("year_month");
        final String due = intent.getStringExtra("due");
        final String c_account = intent.getStringExtra("c_account");
        final String c_giroid = intent.getStringExtra("c_giroid");
        bill_id = intent.getStringExtra("bill_id");



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
                intent.putExtra("bill_id","");
                startActivity(intent); // 다음 화면으로 넘어간다
                task = new loadJsp();
                task.execute();
            }
        });

    }
    /*
       public void onClick(View v){
           Toast.makeText(this,"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
       }
       */
    class loadJsp extends AsyncTask<Void, String, Void> {

        final String test1 = ID;
        final String test2 = price;
        final String test3 = p_date;
        final String test4 = b_type;
        final String test5 = bill_id;

        @Override
        protected Void doInBackground(Void... param) {
            // TODO Auto-generated method stub


            try {
                HttpClient client = new DefaultHttpClient();


                // jsp 주소
                String postURL = "http://211.253.26.217:1024/insertPaid.jsp";


                HttpPost post = new HttpPost(postURL);
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                //파

                params.add(new BasicNameValuePair("price",test2 ));
                params.add(new BasicNameValuePair("p_date", test3));
                params.add(new BasicNameValuePair("b_type", test4));
                params.add(new BasicNameValuePair("id", test1));
                params.add(new BasicNameValuePair("bill_id", test5));



                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                post.setEntity(ent);

                HttpResponse responsePOST = client.execute(post);
                HttpEntity resEntity = responsePOST.getEntity();
                if (resEntity != null) {
                    Log.i("RESPONSE", EntityUtils.toString(resEntity));
                }

            }//endTry
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;


        } //end doInBackground

    } //endLoadJsp

}

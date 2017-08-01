package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.ArrayList;

/**
 * Created by thddu on 2017-06-28.
 */

public class Join extends AppCompatActivity {

    EditText join_id,join_pw,join_pwAgain,join_name,join_phoneNum,join_email,
            join_birthYearMonthDay,join_address;
    loadJsp task;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        join_id = (EditText) findViewById(R.id.join_id);
        join_pw = (EditText) findViewById(R.id.join_pw);
        join_pwAgain = (EditText) findViewById(R.id.join_pwAgain);
        join_name = (EditText) findViewById(R.id.join_name);
        join_birthYearMonthDay = (EditText) findViewById(R.id.join_birthYearMonthDay);
        join_phoneNum = (EditText) findViewById(R.id.join_phoneNum);
        join_email = (EditText) findViewById(R.id.join_email);
        join_address = (EditText) findViewById(R.id.join_address);

        Button btnNext = (Button) findViewById(R.id.btnOK);
        Button btnDoubleCheck = (Button) findViewById(R.id.join_idCheck);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(join_id.getText().toString().equals("")||join_pw.getText().toString().equals("")||
                        join_pwAgain.getText().toString().equals("")||join_name.getText().toString().equals("")||
                        join_birthYearMonthDay.getText().toString().equals("")||join_phoneNum.getText().toString().equals("")||
                        join_email.getText().toString().equals("")||join_address.getText().toString().equals("")) {
                    Toast.makeText(Join.this, "모든 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent completeIntent = new Intent(Join.this, RegisterAuth.class);
                    Join.this.startActivity(completeIntent);
                    task = new loadJsp();
                    task.execute();
                }
            }
        });


    }
    class loadJsp extends AsyncTask<Void, String, Void> {
        final String id = join_id.getText().toString();
        final String pw = join_pw.getText().toString();
        final String pwAgain = join_pwAgain.getText().toString();
        final String name = join_name.getText().toString();
        final String birthYearMonthDay = join_birthYearMonthDay.getText().toString();
        final String phoneNum = join_phoneNum.getText().toString();
        final String email = join_email.getText().toString();
        final String address = join_address.getText().toString();


        @Override
        protected Void doInBackground(Void... param) {
            // TODO Auto-generated method stub

            try {
                HttpClient client = new DefaultHttpClient();


                // jsp 주소
                String postURL = "http://211.253.26.217:1024/join.jsp";


                HttpPost post = new HttpPost(postURL);
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                //파

                params.add(new BasicNameValuePair("id", id));
                params.add(new BasicNameValuePair("pw", pw));
                params.add(new BasicNameValuePair("pwAgain", pwAgain));
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("birthYearMonthDay", birthYearMonthDay));
                params.add(new BasicNameValuePair("phoneNum", phoneNum));
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("address", address));


                UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
                post.setEntity(ent);

                HttpResponse responsePOST = client.execute(post);
                HttpEntity resEntity = responsePOST.getEntity();
                if (resEntity != null) {
                    Log.i("RESPONSE", EntityUtils.toString(resEntity));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;


        }

    }
}
package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * Created by LimHJ on 2017-06-25.
 */

public class RegisterDetail extends AppCompatActivity {

    EditText f_institution, m_name, a_num, a_holder, a_pw;

    loadJsp task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerdetail);

        f_institution = (EditText) findViewById(R.id.choose_financial_institution);
        m_name = (EditText) findViewById(R.id.manage_name);
        a_num = (EditText) findViewById(R.id.accountnumber);
        a_holder = (EditText) findViewById(R.id.accountholder);
        a_pw = (EditText) findViewById(R.id.accountpassword);

        Button regibtn = (Button) findViewById(R.id.register);
        regibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent completeIntent = new Intent(RegisterDetail.this, RegisterComplete.class);
                RegisterDetail.this.startActivity(completeIntent);
                task = new loadJsp();
                task.execute();
            }
        });

    }

    class loadJsp extends AsyncTask<Void, String, Void> {
        final String test1 = f_institution.getText().toString();
        final String test2 = m_name.getText().toString();
        final String test3 = a_num.getText().toString();
        final String test4 = a_holder.getText().toString();
        final String test5 = a_pw.getText().toString();



        @Override
        protected Void doInBackground(Void... param) {
            // TODO Auto-generated method stub

            try {
                HttpClient client = new DefaultHttpClient();


                // jsp 주소
                String postURL = "http://211.253.26.217:1024/insert.jsp";


                HttpPost post = new HttpPost(postURL);
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

                //파

                params.add(new BasicNameValuePair("fi", test1));
                params.add(new BasicNameValuePair("mn", test2));
                params.add(new BasicNameValuePair("an", test3));
                params.add(new BasicNameValuePair("ah", test4));
                params.add(new BasicNameValuePair("ap", test5));


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
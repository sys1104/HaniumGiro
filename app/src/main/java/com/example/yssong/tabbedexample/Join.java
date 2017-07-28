package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by thddu on 2017-06-28.
 */

public class Join extends AppCompatActivity {
    EditText join_id,join_pw,join_pwAgain,join_name,join_phoneNum,join_email,
            join_birthYearMonthDay,join_address;
    String result;
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

        final String id = join_id.getText().toString();
        final String pw = join_pw.getText().toString();
        final String pwAgain = join_pwAgain.getText().toString();
        final String name = join_name.getText().toString();
        final String birthYearMonthDay = join_birthYearMonthDay.getText().toString();
        final String phoneNum = join_phoneNum.getText().toString();
        final String email = join_email.getText().toString();
        final String address = join_address.getText().toString();



        /*View.OnClickListener btnListener = null;
        btnNext.setOnClickListener(btnListener);
        btnDoubleCheck.setOnClickListener(btnListener);*/

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result = new CustomTask().execute(id, pw, name, birthYearMonthDay, phoneNum, email, address).get();
                    Log.i("로그값", result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (result.equals("false")) {
                    Toast.makeText(Join.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    join_id.setText("");
                } else if (result.equals("ture")) {
                    Toast.makeText(Join.this, "회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), // 현재 화면의 제어권자
                            RegisterAuth.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent); // 다음 화면으로 넘어간다
                } else
                    Toast.makeText(Join.this, "오류.", Toast.LENGTH_SHORT).show();
            }
        });

        btnDoubleCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result = new CustomTask().execute(id, pw, name, birthYearMonthDay, phoneNum, email, address).get();
                    Log.i("로그값", result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (result.equals("false")) {
                    Toast.makeText(Join.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                } else if (result.equals("ture")) {
                    Toast.makeText(Join.this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*btnListener = new View.OnClickListener() {
            String result;

            @Override
            public void onClick(View view) {
                final String id = join_id.getText().toString();
                final String pw = join_pw.getText().toString();
                final String pwAgain = join_pwAgain.getText().toString();
                final String name = join_name.getText().toString();
                final String birthYearMonthDay = join_birthYearMonthDay.getText().toString();
                final String phoneNum = join_phoneNum.getText().toString();
                final String email = join_email.getText().toString();
                final String address = join_address.getText().toString();

                try {
                    result = new CustomTask().execute(id, pw, name, birthYearMonthDay, phoneNum, email, address).get();
                    Log.i("로그값", result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                switch (view.getId()) {
                    case R.id.btnOK: // 회원가입버튼 눌렀을경우
                        if (result.equals(" false")) {
                            Toast.makeText(Join.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            join_id.setText("");
                        } else if (result.equals(" ture")) {
                            Toast.makeText(Join.this, "회원가입을 축하합니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), // 현재 화면의 제어권자
                                    RegisterAuth.class); // 다음 넘어갈 클래스 지정
                            startActivity(intent); // 다음 화면으로 넘어간다
                        } else
                            Toast.makeText(Join.this, "오류.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.join_idCheck: // 중복확인버튼 눌렀을경우
                        if (result.equals(" false")) {
                            Toast.makeText(Join.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        } else if (result.equals(" ture")) {
                            Toast.makeText(Join.this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };*/


    }
    /*class loadJsp extends AsyncTask<Void, String, Void> {
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

    }*/

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://211.253.26.217:1024/join.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]+"&name="+strings[2]+"&birthYearMonthDay="+strings[3]
                        +"&phoneNum="+strings[4]+"&email="+strings[5]+"&address="+strings[6];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
}
package com.example.yssong.tabbedexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.tsengvn.typekit.TypekitContextWrapper;

public class MainActivity extends AppCompatActivity {

    EditText userId, userPwd;
    Button login, join;
    String loginid, loginpwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FirebaseInstanceId.getInstance().getToken();

        String token = FirebaseInstanceId.getInstance().getToken();
        userId = (EditText) findViewById(R.id.editText2);
        userPwd = (EditText) findViewById(R.id.editText3);


        login = (Button) findViewById(R.id.button_Login);
        join = (Button) findViewById(R.id.button_Join);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginid = userId.getText().toString();
                loginpwd = userPwd.getText().toString();

                try {
                    String result  = new CustomTask().execute(loginid,loginpwd).get();
                    Log.i("로그값",result);
                    if(result.equals(" true")) {
                        Toast.makeText(MainActivity.this,"로그인",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                        startActivity(intent);
                        finish();
                    } else if(result.equals(" false")) {
                        Toast.makeText(MainActivity.this,"비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                        userId.setText("");
                        userPwd.setText("");
                    } else if(result.equals(" noid")) {
                        Toast.makeText(MainActivity.this,"존재하지 않는 아이디입니다",Toast.LENGTH_SHORT).show();
                        userId.setText("");
                        userPwd.setText("");
                    }
                }catch (Exception e) {}


            }

        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        JoinPolicy.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    } //endOnCreate
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://211.253.26.217:1024/login.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pwd="+strings[1];
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

package com.example.yssong.tabbedexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText userId, userPwd;
    Button login, join;
    CheckBox chkAutoLogin;
    String loginid, loginpwd, autoId, autoPw;
    String result;
    String check = null;
    Boolean autoLoginEnabled;
    public static String u_id = null;
    private static final String TAG_check = "check";
    private static final String TAG_u_id = "u_id";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        SharedPreferences autoLogin = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        userId = (EditText) findViewById(R.id.editText2);
        userPwd = (EditText) findViewById(R.id.editText3);
        autoId = autoLogin.getString("inputId",null);
        autoPw = autoLogin.getString("inputPwd",null);
        autoLoginEnabled = autoLogin.getBoolean("autoLoginEnabled",false);
        login = (Button) findViewById(R.id.button_Login);
        join = (Button) findViewById(R.id.button_Join);
        chkAutoLogin = (CheckBox) findViewById(R.id.chkAutoLogin);

        /** 토큰값 출력해보기 **/
        /*Toast toast = Toast.makeText(this, FirebaseInstanceId.getInstance().getToken(),
         Toast.LENGTH_SHORT);
        toast.show();*/
        if(autoId != null && autoPw != null && autoLoginEnabled) { //자동로그인 체크 되어있을시 아이디,비밀번호 확인 후 자동로그인
            try {
                result = new CustomTask().execute(autoId, autoPw).get();
                Log.i("로그값", result);
                JSONArray jsonArray = new JSONArray(result);
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        check = jobj.getString(TAG_check);
                        u_id = jobj.getString(TAG_u_id);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } //endCatch
                if (check.equals("false")) {
                    Toast.makeText(MainActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                    userId.setText("");
                    userPwd.setText("");
                } else if (check.equals("noid")) {
                    Toast.makeText(MainActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    userId.setText("");
                    userPwd.setText("");
                } else {
                    Toast.makeText(MainActivity.this, autoId + " 아이디로 자동로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                    startActivity(intent);
                    finish();
                }
            } catch (Exception e) {
            }
        }

        else if(autoId == null && autoPw == null) //자동로그인이 아닐 때
        {
            chkAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginid = userId.getText().toString();
                    loginpwd = userPwd.getText().toString();
                    if (loginid.equals("") && loginpwd.equals("")) {
                        Toast.makeText(MainActivity.this, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            result = new CustomTask().execute(loginid, loginpwd).get();
                            Log.i("로그값", result);

                            JSONArray jsonArray = new JSONArray(result);
                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jobj = jsonArray.getJSONObject(i);
                                    check = jobj.getString(TAG_check);
                                    u_id = jobj.getString(TAG_u_id);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (check.equals("false")) {
                                Toast.makeText(MainActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                                userId.setText("");
                                userPwd.setText("");
                            } else if (check.equals("noid")) {
                                Toast.makeText(MainActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                                userId.setText("");
                                userPwd.setText("");
                            } else {
                                if(chkAutoLogin.isChecked())
                                    {
                                        SharedPreferences autoLogin = getSharedPreferences("autoLogin",Activity.MODE_PRIVATE);
                                        SharedPreferences.Editor autoLoginEdit = autoLogin.edit();
                                        autoLoginEdit.putString("inputId", userId.getText().toString());
                                        autoLoginEdit.putString("inputPwd",userPwd.getText().toString());
                                        autoLoginEdit.putBoolean("autoLoginEnabled",true);
                                        //꼭 commit()을 해줘야 값이 저장됩니다 ㅎㅎ
                                        autoLoginEdit.apply();
                                    }
                                Toast.makeText(MainActivity.this, "로그인.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (Exception e) {
                        }


                    }
                }

            });
        }
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

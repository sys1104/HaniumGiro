package com.example.yssong.tabbedexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountList extends AppCompatActivity {

    private static String TAG = "jsontest";

    private static final String TAG_fi = "fi";
    private static final String TAG_an = "an";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_list);

        mlistView = (ListView) findViewById(R.id.jsonlistview);
        mArrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://211.253.26.217:1024/json.jsp");


        Button addbtn = (Button) findViewById(R.id.add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(AccountList.this, RegisterAccount.class);
                AccountList.this.startActivity(registerIntent);
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(AccountList.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);


                mJsonString = result;
                showResult();

        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONArray jsonArray = new JSONArray(mJsonString);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject jobj = jsonArray.getJSONObject(i);

                String fi = jobj.getString(TAG_fi);
                String an = jobj.getString(TAG_an);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_fi, fi);
                hashMap.put(TAG_an, an);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    AccountList.this, mArrayList, R.layout.registerlist,
                    new String[]{TAG_fi,TAG_an},
                    new int[]{R.id.financial_institution, R.id.account_number}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}

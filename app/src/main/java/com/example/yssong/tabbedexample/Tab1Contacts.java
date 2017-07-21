package com.example.yssong.tabbedexample;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.List;

/**
 * Created by JIHYUN2 on 2017-07-03.
 * 고지서 대화목록을 볼수있는 화면
 * 아직 test DB에 연결해둠.
 * 클릭후 대화창화면으로 넘어가는 부분 수정중
 */

public class Tab1Contacts  extends Fragment {


    private static String TAG = "jsontest";

    private static final String TAG_fi = "b_type";

    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;
    String[] type = new String[100];
    String[] bPrice = new String[100];
    String[] bYM = new String[100];
    String[] bDue = new String[100];
    String[] CAccount = new String[100];
    String[] CGiroid = new String[100];


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bill_list, container, false);
      //  program();

        mlistView = (ListView) rootView.findViewById(R.id.listview);
        mArrayList = new ArrayList<>();

        Tab1Contacts.GetData task = new Tab1Contacts.GetData();
        task.execute("http://211.253.26.217:1024/BillInfo.jsp");

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SeeDetail.class);
                intent.putExtra("b_type",type[position]);
                intent.putExtra("price",bPrice[position]);
                intent.putExtra("year_month",bYM[position]);
                intent.putExtra("due",bDue[position]);
                intent.putExtra("c_account",CAccount[position]);
                intent.putExtra("c_giroid",CGiroid[position]);
                startActivity(intent);
                //position 값으로 몇번째 목록이 넘어온건지 정의 해주어야하는데
                //아직 해결하지못함.
            }
        });


        return rootView;
    }

      class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
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

                JSONObject item = jsonArray.getJSONObject(i);

                String fi = item.getString(TAG_fi);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_fi, fi);
                mArrayList.add(hashMap);

                type[i] = fi;
                bPrice[i] = item.getString("price");
                bYM[i] = item.getString("year_month");
                bDue[i] = item.getString("due");
                CAccount[i] = item.getString("c_account");
                CGiroid[i] = item.getString("c_giroid");


            }

            ListAdapter adapter = new SimpleAdapter(
                  getActivity(), mArrayList, R.layout.friend_info,
                    new String[]{TAG_fi},
                    new int[]{R.id.friend_ID}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}


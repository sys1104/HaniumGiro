package com.example.yssong.tabbedexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by YSSONG on 2017-04-16.
 */

public class Tab2History extends Fragment {

    private static String TAG = "jsontest";

    private static final String TAG_price = "price";
    private static final String TAG_item = "item";
    private static final String TAG_date = "date";


    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_histroy, container, false);

        mlistView = (ListView) rootView.findViewById(R.id.jsonlistview2);
        mArrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://211.253.26.217:1024/chat.jsp");
        return rootView;
    }

    private class GetData extends AsyncTask<String, Void, String> {
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

                JSONObject jobj = jsonArray.getJSONObject(i);

                String price = jobj.getString(TAG_price);
                String item = jobj.getString(TAG_item);
                String date = jobj.getString(TAG_date);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_price, price);
                hashMap.put(TAG_item, item);
                hashMap.put(TAG_date, date);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), mArrayList, R.layout.tab2_history_list,
                    new String[]{TAG_price,TAG_item,TAG_date},
                    new int[]{R.id.price_l, R.id.item_l, R.id.date_l}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
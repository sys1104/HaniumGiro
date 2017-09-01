package com.example.yssong.tabbedexample;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

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
    String[] type = new String[100];
    String[] bPrice = new String[100];
    String[] bYM = new String[100];
    String[] bDue = new String[100];
    String[] CAccount = new String[100];
    String[] CGiroid = new String[100];
    String[] bId = new String[100];
    String result;
    int[] icons = new int[]{
            R.drawable.icon

    };
    int icon=R.drawable.icon;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bill_list, container, false);
      //  program();

        mlistView = (ListView) rootView.findViewById(R.id.listview);
        mArrayList = new ArrayList<>();

        MainActivity temp = new MainActivity();
        String ID = temp.u_id;
        try {
            result = new CustomTask().execute(ID).get();
            Log.i("로그값", result);
            showResult(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BillView.class);
                intent.putExtra("b_type",type[position]);
                intent.putExtra("price",bPrice[position]);
                intent.putExtra("year_month",bYM[position]);
                intent.putExtra("due",bDue[position]);
                intent.putExtra("c_account",CAccount[position]);
                intent.putExtra("c_giroid",CGiroid[position]);
                intent.putExtra("bill_id",bId[position]);
                startActivity(intent);
                //position 값으로 몇번째 목록이 넘어온건지 정의 해주어야하는데
                //아직 해결하지못함.
            }
        });


        return rootView;
    }


    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://211.253.26.217:1024/BillInfo.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
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


    private void showResult(String result){
        try {
            JSONArray jsonArray = new JSONArray(result);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String fi = item.getString(TAG_fi);



                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_fi, fi);
                hashMap.put("icon",Integer.toString(icon));
                mArrayList.add(hashMap);

                type[i] = fi;
                bPrice[i] = item.getString("price");
                bYM[i] = item.getString("year_month");
                bDue[i] = item.getString("due");
                CAccount[i] = item.getString("c_account");
                CGiroid[i] = item.getString("c_giroid");
                bId[i] = item.getString("bill_id");


            }
            String[] from = {"icon",TAG_fi};
            int[] to = { R.id.mIcon,R.id.friend_ID};

            ListAdapter adapter = new SimpleAdapter(
                  getActivity(), mArrayList, R.layout.friend_info,
                    from,
                    to
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}


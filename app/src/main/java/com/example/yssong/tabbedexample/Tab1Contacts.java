package com.example.yssong.tabbedexample;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JIHYUN2 on 2017-07-03.
 */

public class Tab1Contacts  extends Fragment{

    private ListView listView;
    private FriendInfoAdapter adapter;
    private List<FriendInfo> friendInfoList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bill_list, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview);
        program();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SeeDetail.class);
                startActivity(intent);
                //position 값으로 몇번째 목록이 넘어온건지 정의 해주어야하는데
                //아직 해결하지못함.
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void program(){

        friendInfoList = new ArrayList<FriendInfo>();

        //임의로 집어넣은 데이터값
        friendInfoList.add(new FriendInfo("우유고지서"));
        friendInfoList.add(new FriendInfo("전기세고지서"));
        friendInfoList.add(new FriendInfo("수도세고지서"));


        //실제 데이터베이스에서 친구목록 가져오기
       /* try{
            JSONObject jsonObject = new JSONObject((intent.getStringExtra("FriendList")));
            JSONArray jsonArray = jsonObject.getJSONArray("response"); //배열의 변수이름 (나중에 수정하기)
            int count =0;
            String Bill_ID;
            while(count<jsonArray.length()){ //배열탐색
                JSONObject object = jsonArray.getJSONObject(count);
                Bill_ID = object.getString("Bill_ID");

                FriendInfo friendInfo = new FriendInfo(Bill_ID);
                friendInfoList.add(friendInfo);
                count++;
            }
        }catch (Exception e){ //오류가 발생했을 경우 출력됨
         e.printStackTrace();
        }*/

        adapter = new FriendInfoAdapter(getActivity(),friendInfoList);
        //friendInfoList = new List<FriendInfo>(getActivity(),android.R.layout., listView);
        listView.setAdapter(adapter);

    }


}
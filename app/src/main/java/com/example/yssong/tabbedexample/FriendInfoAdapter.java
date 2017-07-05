package com.example.yssong.tabbedexample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;




/**
 * Created by JIHYUN2 on 2017-07-03.
 */

public class FriendInfoAdapter extends BaseAdapter {
    private Context context;
    private List<FriendInfo> frinedInfo ;

    public FriendInfoAdapter(Context context,List<FriendInfo> frinedInfo ){
        this.context = context;
        this.frinedInfo = frinedInfo;
    }
    @Override
    public int getCount() {
        return frinedInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return frinedInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) { //리스트안에 입력되게될 데이터들을 불러옴
        View v = View.inflate(context,R.layout.friend_info,null);

        TextView friendID = (TextView) v.findViewById(R.id.friend_ID);
        friendID.setText(frinedInfo.get(i).getFriendID());

        v.setTag(frinedInfo.get(i).getFriendID());

        return v;
    }
}

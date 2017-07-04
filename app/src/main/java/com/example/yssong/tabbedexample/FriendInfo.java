
package com.example.yssong.tabbedexample;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by JIHYUN2 on 2017-07-03.
 */

public class FriendInfo {
    String friendID;

    public String getFriendID() {
        return friendID;
    }
    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public FriendInfo(String friendID) {
        this.friendID = friendID;
    }


}

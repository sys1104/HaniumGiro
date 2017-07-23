package com.example.yssong.tabbedexample;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by thddu on 2017-07-23.
 * 커스텀 폰트 정의하는 클래스
 */

public class ApplicationBase extends Application {
    @Override public void onCreate() {
        super.onCreate();
        // 폰트 정의
        Typekit.getInstance()
                .add("Title",Typekit.createFromAsset(this, "BMJUA.ttf"));

    }
}



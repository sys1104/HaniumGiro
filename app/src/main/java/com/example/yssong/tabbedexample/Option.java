package com.example.yssong.tabbedexample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by LimHJ on 2017-06-25.
 */

public class Option extends AppCompatActivity {
    boolean sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);
        Switch pushSw = (Switch) findViewById(R.id.push_switch);
        pushSw.setChecked(getData()); // SharedPreference에서 받아온 값으로 On/Off 초기화
        pushSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw = true;
                    Toast.makeText(Option.this, "푸시메시지를 받습니다", Toast.LENGTH_SHORT).show();
                } else {
                    sw = false;
                    Toast.makeText(Option.this, "푸시메시지를 받지 않습니다", Toast.LENGTH_SHORT).show();
                }
                putData(); //SharedPreferences에 데이터 저장
            }
        }); //Listener End

    } //end Oncreate
    public void putData()
    {
        SharedPreferences pushSwitch = getSharedPreferences("pushSwitch", MODE_PRIVATE);
        SharedPreferences.Editor editor = pushSwitch.edit();
        editor.putBoolean("pushSwitch", sw);
        editor.apply();
    }
    public boolean getData()
    {
        SharedPreferences pushSwitch = getSharedPreferences("pushSwitch", MODE_PRIVATE);
        sw=pushSwitch.getBoolean("pushSwitch",true);
        return sw;
    }
}

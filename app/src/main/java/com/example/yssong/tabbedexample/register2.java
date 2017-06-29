package com.example.yssong.tabbedexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by LimHJ on 2017-06-25.
 */

public class register2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);

        Button regibtn=(Button) findViewById(R.id.register);
        regibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent completeIntent = new Intent(register2.this, RegisterComplete.class);
                register2.this.startActivity(completeIntent);
            }
        });
    }
}
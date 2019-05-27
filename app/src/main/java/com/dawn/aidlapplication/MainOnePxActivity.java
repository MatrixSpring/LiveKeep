package com.dawn.aidlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.dawn.aidlapplication.onepx.ActivityKeepLive;
import com.dawn.aidlapplication.onepx.KeepLiveUtils;

public class MainOnePxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        KeepLiveUtils.getInstance().registerBroadcast(MainOnePxActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepLiveUtils.getInstance().unRegisterBroadcast(MainOnePxActivity.this);
    }
}

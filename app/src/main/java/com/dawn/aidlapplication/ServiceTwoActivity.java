package com.dawn.aidlapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dawn.aidlapplication.R;
import com.dawn.aidlapplication.priority.ForegroundService;
import com.dawn.aidlapplication.service.LocalService;
import com.dawn.aidlapplication.service.RemoteService;

public class ServiceTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forground_service);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);

        startService(new Intent(this,LocalService.class));
        startService(new Intent(this,RemoteService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

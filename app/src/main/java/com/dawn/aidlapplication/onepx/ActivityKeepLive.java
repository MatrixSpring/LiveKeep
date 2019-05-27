package com.dawn.aidlapplication.onepx;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class ActivityKeepLive extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, ActivityKeepLive.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.TOP|Gravity.LEFT);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.x = 0;
        layoutParams.y=0;
        window.setAttributes(layoutParams);
        Log.d("ddddddddddd","ddddddddddd onCreate ");
        KeepLiveUtils.getInstance().setKeepLive(ActivityKeepLive.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ddddddddddd","ddddddddddd onDestroy ");
    }
}

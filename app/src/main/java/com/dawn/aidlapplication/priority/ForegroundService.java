package com.dawn.aidlapplication.priority;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class ForegroundService extends Service {
    private static final int SERVICE_ID = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18) {   //Android 4.4一下
            startForeground(SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < 26) { //Android 4.4~7.x
            startForeground(SERVICE_ID, new Notification());
            //需要隐藏notification 需要重新开启一个service
            startService(new Intent(this, InnerService.class));
        } else {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (null != manager) {
                NotificationChannel channel = new NotificationChannel("channel", "none", NotificationManager.IMPORTANCE_NONE);
                manager.createNotificationChannel(channel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel");
                //Aandroid8.0 Android9.0不一致 9.0需要权限
                startForeground(SERVICE_ID, builder.build());

            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnerService extends Service {

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}

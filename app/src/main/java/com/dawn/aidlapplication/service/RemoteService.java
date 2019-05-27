package com.dawn.aidlapplication.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class RemoteService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(RemoteService.this,LocalService.class), serviceConnection, BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(RemoteService.this,LocalService.class));
            bindService(new Intent(RemoteService.this,LocalService.class), serviceConnection, BIND_IMPORTANT);
        }
    };
}

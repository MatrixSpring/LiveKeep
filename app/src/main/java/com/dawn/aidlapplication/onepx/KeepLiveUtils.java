package com.dawn.aidlapplication.onepx;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepLiveUtils {
    private static volatile KeepLiveUtils keepLiveUtils;
    private static WeakReference<Activity> weakReference;
    private ScreenStatusReceiver mScreenStatusReceiver;
    private KeepLiveUtils() {}

    public static synchronized KeepLiveUtils getInstance(){
        keepLiveUtils = new KeepLiveUtils();
        return keepLiveUtils;
    }


    public void setKeepLive(Activity activity){
        weakReference = new WeakReference<>(activity);
    }


    public void registerBroadcast(final Context context) {
        mScreenStatusReceiver = new ScreenStatusReceiver(new BroadcastCallback() {
            @Override
            public void broadcastResult(boolean isOpen) {
                if(isOpen){
                    ActivityKeepLive.startActivity(context);
                }else{
                    if(null!=weakReference.get()){
                        weakReference.get().finish();
                    }
                }
            }
        });
        IntentFilter screenStatusIF = new IntentFilter();
        screenStatusIF.addAction(Intent.ACTION_SCREEN_ON);
        screenStatusIF.addAction(Intent.ACTION_SCREEN_OFF);
        context.registerReceiver(mScreenStatusReceiver, screenStatusIF);
    }

    public void unRegisterBroadcast(Context context) {
        context.unregisterReceiver(mScreenStatusReceiver);
    }

    public interface BroadcastCallback{
        public void broadcastResult(boolean isOpen);
    }


    static class ScreenStatusReceiver extends BroadcastReceiver {
        String SCREEN_ON = "android.intent.action.SCREEN_ON";
        String SCREEN_OFF = "android.intent.action.SCREEN_OFF";
        BroadcastCallback broadcastCallback;

        public ScreenStatusReceiver(BroadcastCallback broadcastCallback){
            this.broadcastCallback = broadcastCallback;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (SCREEN_ON.equals(intent.getAction())) {
                if(null != this.broadcastCallback){
                    this.broadcastCallback.broadcastResult(false);
                }
            } else if (SCREEN_OFF.equals(intent.getAction())) {
                if (null != this.broadcastCallback) {
                    this.broadcastCallback.broadcastResult(true);
                }
            }
        }
    }
}

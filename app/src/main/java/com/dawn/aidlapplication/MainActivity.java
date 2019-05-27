package com.dawn.aidlapplication;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        final Intent intent = new Intent();
        intent.setAction("com.dawn.aidlapplication.FIRST_SERVICE");
        final Intent eintent = new Intent(this,AIDLService.class);
        bindService(eintent,new MyConnection(), Service.BIND_AUTO_CREATE);

        findViewById(R.id.sample_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (iMyAidlInterface != null) {
                        iMyAidlInterface.addBookIn(new Book("Hello", (float) 9.99));
                        iMyAidlInterface.addBookIn(new Book("Book", (float) 8.99));
                        iMyAidlInterface.addBookIn(new Book("C++", (float) 7.99));
                        iMyAidlInterface.addBookIn(new Book("Java", (float) 4.99));
                        Toast.makeText(MainActivity.this, iMyAidlInterface.getString(), Toast.LENGTH_LONG).show();


                        Log.d("iMyAidlInterface","iMyAidlInterface : "+ iMyAidlInterface.getBookCount());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    private class MyConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface =  IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


}

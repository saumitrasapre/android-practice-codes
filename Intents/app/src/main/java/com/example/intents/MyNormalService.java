package com.example.intents;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyNormalService extends Service {

    Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {

            Toast mytoast=Toast.makeText(getApplicationContext(),"Normal Service Started...",Toast.LENGTH_SHORT);
            mytoast.show();
            //super.handleMessage(msg);
        }
    };

    private static final String TAG2="com.example.intents";
    public MyNormalService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        Log.i(TAG2,"Normal service started...");

        Runnable r=new Runnable() {
            @Override
            public void run() {

                myHandler.sendEmptyMessage(0);

            }
        };

        Thread myThread=new Thread(r);
        myThread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        //super.onDestroy();

        Log.i(TAG2,"Normal Service Destroyed...");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;

    }
}

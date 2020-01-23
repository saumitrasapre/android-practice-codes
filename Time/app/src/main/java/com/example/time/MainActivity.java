package com.example.time;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import com.example.time.MyService.MyLocalBinder;

import android.os.Bundle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MyService myobject;
    boolean isBound=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(1);

        Intent myIntent=new Intent(getApplicationContext(),MyService.class);
        bindService(myIntent,myconn, Context.BIND_AUTO_CREATE);

        Button gobtn=findViewById(R.id.timeButton);
        final TextView disptxt=findViewById(R.id.currentTime);

        final Handler myHandler=new Handler()
        {
            @Override
            public void handleMessage(@NonNull Message msg) {
                String currtime=myobject.getCurrentTime();
                disptxt.setText(currtime);
            }
        };

        gobtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String currtime=myobject.getCurrentTime();
                        disptxt.setText(currtime);
                    }
                }

        );

        Runnable r=new Runnable() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0);
            }
        };

        final ScheduledFuture timechange=scheduler.scheduleAtFixedRate(r,1,1, TimeUnit.SECONDS);

        //CODE TO CANCEL TIME CHANGE AFTER 1 HOUR

        /*
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                timechange.cancel(true);
            }
        }, 60 * 60, TimeUnit.SECONDS);
*/
    }



    private ServiceConnection myconn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MyLocalBinder binder=(MyLocalBinder)iBinder;
           myobject= binder.getService();
           isBound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            isBound=false;

        }
    };
}

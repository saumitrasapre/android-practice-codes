package com.example.time;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MyService extends Service {

    public final IBinder myBinder =new MyLocalBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");

        return myBinder;
    }

    public String getCurrentTime()
    {
        SimpleDateFormat df=new SimpleDateFormat("HH:mm:ss",Locale.UK);
        return  (df.format(new Date()));
    }

    public class MyLocalBinder extends  Binder //This is a nested class
    {
        MyService getService()
        {
            return MyService.this;
        }
    }
}

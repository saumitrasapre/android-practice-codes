package com.example.intents;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    public final String TAG="com.example.intents";// Tag for the log
    public MyIntentService()
    {
        super("MyIntentService"); //Pass the name of the class as the parameter to the super class
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //This is what the service does
        Log.i(TAG,"The service is now running");

    }
}

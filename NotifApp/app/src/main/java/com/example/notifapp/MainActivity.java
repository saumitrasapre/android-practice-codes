package com.example.notifapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final int uniqueID=123456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().subscribeToTopic("Indigo");

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {

                if(!task.isSuccessful())
                {
                    Log.i("Token of Notif","Task Failed");
                    return;
                }
                Log.i("Token of Notif","The result: "+  task.getResult().getToken());

            }
        });
    }



    private void triggerNotification() {


        Intent intent=new Intent(this,AfterNotifActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder notification;
        notification=new NotificationCompat.Builder(this, "Default");
        notification.setSmallIcon(R.drawable.ic_launcher_foreground);
        notification.setContentTitle("Hey Man!");
        notification.setContentText("Wear your mom...");
        notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notification.setContentIntent(pendingIntent);
        notification.setChannelId("Default");
        notification.setOngoing(true);//Notification cannot be swiped unless you click on it
        notification.setAutoCancel(true); //Notification cancels when you click it or it redirects you to the app
        notification.setWhen(System.currentTimeMillis());


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(uniqueID,notification.build());
    }
}

package com.example.notiftest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        private static final int uniqueID=123456;
        Button notifbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifbutton=(Button)findViewById(R.id.notifButton);

        createNotificationChannel();

        notifbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                triggerNotification();



            }


        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel= new NotificationChannel("Default","MyNotificationChannel",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("MyDescription");
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    private void triggerNotification() {


        Intent intent=new Intent(this,AfterNotif.class);
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

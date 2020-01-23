package com.example.notifapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static int NOTIFICATION_ID=1;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        createNotificationChannel();

        generateNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
        Log.i("Message","Message Received");
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i("Token","New Token: "+s);
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel= new NotificationChannel("Default","MyNotificationChannel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("MyDescription");
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void generateNotification(String body, String title) {

        Intent intent=new Intent(this,AfterNotifActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,"Default")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId("Default")
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis());


        if(NOTIFICATION_ID>1073741824)
        {
            NOTIFICATION_ID=0;
        }

        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID++,notificationBuilder.build());
    }
}

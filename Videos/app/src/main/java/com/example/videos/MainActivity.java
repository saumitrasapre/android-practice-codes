package com.example.videos;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.widget.VideoView;
import android.widget.MediaController;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final VideoView myVideoView= (VideoView)findViewById(R.id.myvideoView);
        MediaController mycontroller=new MediaController(this);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.meme);
        myVideoView.setVideoURI(uri);
        mycontroller.setAnchorView(myVideoView);
        myVideoView.setMediaController(mycontroller);
        myVideoView.requestFocus();
        myVideoView.start();
        


    }
}

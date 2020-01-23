package com.example.sendbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton=findViewById(R.id.send_button);

        myButton.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent=new Intent();
                        myIntent.setAction("com.example.sendbroadcast");
                        myIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                         sendBroadcast(myIntent);
                    }
                }
        );
    }





}

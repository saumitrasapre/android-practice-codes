package com.example.threads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        TextView mytext = findViewById(R.id.myText);
        mytext.setText(R.string.main_txt);
        long futureTime = System.currentTimeMillis() + 10000;
        boolean test = false;
        while (test == false) {
            synchronized (this) {
                try {
                    wait(futureTime - System.currentTimeMillis());
                    test = true;
                } catch (Exception e) {

                }
            }

            mytext.setText("Thank you for wasting 10 seconds of your life!");
        }

    }

    Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {

            TextView mytext = findViewById(R.id.myText);
            mytext.setText("Thank you for wasting 10 seconds of your life!");
        }
    };

    public void onButton2Click(View view) {
        TextView mytext = findViewById(R.id.myText);
        mytext.setText(R.string.main_txt);
        Runnable myrun = new Runnable() {
            @Override
            public void run() {
                long futureTime = System.currentTimeMillis() + 10000;
                boolean test = false;
                while (test == false) {
                    synchronized (this) {
                        try {
                            wait(futureTime - System.currentTimeMillis());
                            test = true;
                        } catch (Exception e) {

                        }
                    }

                }
                myHandler.sendEmptyMessage(0);
            }

        };
        Thread myThread=new Thread(myrun);
        myThread.start();
    }
}
package com.example.chickennugget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;

import android.view.View;
import  android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    TextView txtgo;
    GestureDetectorCompat mygestdetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btngo=findViewById(R.id.appButton);
        txtgo=findViewById(R.id.chickenMessage);
       this.mygestdetector=new GestureDetectorCompat(this,this);
       mygestdetector.setOnDoubleTapListener(this);

       btngo.setOnClickListener(
               new Button.OnClickListener()
               {
                   public void onClick (View v)
                   {
                       txtgo.setText("Cha Cha Real Smooth!");
                   }
               }
       );

    }

//GESTURES BEGIN
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        txtgo.setText("Single Tap!");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        txtgo.setText("Double Tap!");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        txtgo.setText("Double Tap!");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        txtgo.setText("Down!");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        txtgo.setText("Up!");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        txtgo.setText("Scroll!");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        txtgo.setText("Long Press!");

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        txtgo.setText("Fling!");
        return true;
    }
    //GESTURES END


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        this.mygestdetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}

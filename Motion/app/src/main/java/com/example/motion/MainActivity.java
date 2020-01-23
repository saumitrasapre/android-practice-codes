package com.example.motion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import  android.view.View;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.transition.TransitionManager;

public class MainActivity extends AppCompatActivity {

    ViewGroup myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myview=findViewById(R.id.main_layout);

        myview.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        Button mybutton=findViewById(R.id.main_button);
                        TransitionManager.beginDelayedTransition(myview);

                        //CHANGING THE POSITION OF THE BUTTON
                        RelativeLayout.LayoutParams buttonPosition=new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
                        );
                        buttonPosition.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
                        buttonPosition.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);

                        mybutton.setLayoutParams(buttonPosition);

                        //CHANGING THE SIZE OF THE BUTTON
                       // ViewGroup.LayoutParams buttonSize=mybutton.getLayoutParams();
                        RelativeLayout.LayoutParams buttonSize= (RelativeLayout.LayoutParams) mybutton.getLayoutParams();
                        buttonSize.width=450;
                        buttonSize.height=300;
                        mybutton.setLayoutParams(buttonSize);


                        return true;
                    }
                }
        );

    }
}

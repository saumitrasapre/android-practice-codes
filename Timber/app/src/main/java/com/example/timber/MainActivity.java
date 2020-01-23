package com.example.timber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.EditText;
import  android.graphics.Color;
import  android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CREATING THE LAYOUT
        RelativeLayout myLayout=new RelativeLayout(this);
        myLayout.setBackgroundColor(Color.GREEN);

        //CREATING THE BUTTON AND SETTING ITS ATTRIBUTES
        Button yellowButton=new Button(this);
        yellowButton.setText("Click Me!");
        yellowButton.setBackgroundColor(Color.YELLOW);
        yellowButton.setId(1);

        //CREATING THE EDITTEXT AND SETTING ITS ATTRIBUTES
        EditText myText=new EditText(this);
        myText.setId(2);

        //CONVERTING FROM DIP TO PIXELS
        Resources r=getResources();

        int pixels=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        //POSTIONING THE BUTTON
        RelativeLayout.LayoutParams buttonDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        //POSTIONING THE EDITTEXT
        RelativeLayout.LayoutParams textDetails=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        textDetails.addRule(RelativeLayout.ABOVE,yellowButton.getId());
        textDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textDetails.setMargins(0,0,0,50);
        myText.setWidth(pixels);



        //ADDING THE ELEMENTS TO THEIR CONTAINERS
        myLayout.addView(yellowButton,buttonDetails);
        myLayout.addView(myText,textDetails);

        //DISPLAYING THE LAYOUT
        setContentView(myLayout);
        //setContentView(R.layout.activity_main);
    }
}

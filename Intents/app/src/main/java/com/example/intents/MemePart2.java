package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import android.os.Bundle;

public class MemePart2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_part2);
        Button prevbutton=findViewById(R.id.btn_prev);
        TextView outputText=findViewById(R.id.textOutput);

        Bundle meme1data=getIntent().getExtras();
        if(meme1data==null)
        {
            return;
        }
        else
        {
            String toDisplay=meme1data.getString("passedInput");
            outputText.setText(toDisplay);
        }


        prevbutton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent myIntent2=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(myIntent2);

                    }
                }
        );
    }
}

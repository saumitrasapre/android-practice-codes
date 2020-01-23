package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent meow=new Intent(getApplicationContext(),MyIntentService.class);
        startService(meow);

        Intent lmao=new Intent(getApplicationContext(),MyNormalService.class);
        startService(lmao);

        Button nextbutton=findViewById(R.id.btn_nxt);
        final EditText inputText=findViewById(R.id.textInput);

        nextbutton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent myIntent;
                        myIntent = new Intent(getApplicationContext(),MemePart2.class);
                        final String userInput=inputText.getText().toString();
                        myIntent.putExtra("passedInput",userInput);
                        startActivity(myIntent);


                    }
                }
        );
    }
}

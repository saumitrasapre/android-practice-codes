package com.example.rocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import  android.widget.Button;
import  android.widget.TextView;
import  android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btngo= findViewById(R.id.myButton);
        final TextView txtgo=findViewById(R.id.myText);
        btngo.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick (View v)
                    {
                        txtgo.setText("Oi You F*cking Cunt!");
                        Toast mytoast=Toast.makeText(getApplicationContext(),"You absolute twat!",Toast.LENGTH_SHORT);
                        mytoast.show();
                    }
                }


        );

        btngo.setOnLongClickListener(
                new Button.OnLongClickListener()

                {
                    public boolean onLongClick(View v)
                    {
                        txtgo.setText("AAAH! Leave me or I'll beat the shit out of you!");
                        Toast rottenegg=Toast.makeText(getApplicationContext(),"You mankey daft arse-licker!",Toast.LENGTH_SHORT);
                        rottenegg.show();
                        return  false; //Returning false will temporarily execute onLongClick method and on release will switch back to onClick

                    }
                }


        );

        Button  newbtn=findViewById(R.id.mybutton2);
        newbtn.setOnClickListener(
               new Button.OnClickListener()
               {
                   public void onClick(View v)
                   {
                       txtgo.setText("Ya Cheeky Wanker!");
                       Toast bread=Toast.makeText(getApplicationContext(),"Cheeky Breeki!",Toast.LENGTH_LONG);
                       bread.show();
                   }
               }
        );

        newbtn.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        txtgo.setText("You are a cum stain on the mattress of humanity!");
                        Toast meow=Toast.makeText(getApplicationContext(),"Nants ingonyama baghiti baba!",Toast.LENGTH_SHORT);
                        meow.show();
                        return true;//Returning true will execute the onLongClick method and will stop there even after releasing the finger.
                    }
                }
        );




    }
}

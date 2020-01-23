package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="myMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"inside onCreate");
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onCreate()",Toast.LENGTH_SHORT);
        mytoast.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"inside onStart");
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onStart()",Toast.LENGTH_SHORT);
        mytoast.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"inside onStop");
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onStop()",Toast.LENGTH_SHORT);
        mytoast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"inside onDestroy");
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onDestroy()",Toast.LENGTH_SHORT);
        mytoast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onPause()",Toast.LENGTH_SHORT);
        mytoast.show();
    }

    //@Override
    /*protected void onResume() {
        super.onResume();
        Log.i(TAG,"inside onResume");
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onResume()",Toast.LENGTH_SHORT);
        mytoast.show();
    }
    */

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"inside onRestart");
        Toast mytoast=Toast.makeText(getApplicationContext(),"I'm inside onRestart()",Toast.LENGTH_SHORT);
        mytoast.show();
    }
}

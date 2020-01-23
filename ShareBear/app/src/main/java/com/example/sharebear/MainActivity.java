package com.example.sharebear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameInput,passwordInput;
    TextView output;
    Button saveInfo,displayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput=findViewById(R.id.userNameInput);
        passwordInput=findViewById(R.id.passwordInput);
        output=findViewById(R.id.displayedInfo);
        saveInfo=findViewById(R.id.saveInfo);
        displayInfo=findViewById(R.id.displayButton);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(v);
            }
        });

        displayInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayData(v);
            }
        });

    }

    //Save the users login info
    public void saveInfo(View view)
    {

        SharedPreferences sharedPreferences= getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("userName",usernameInput.getText().toString());
        editor.putString("passWord",passwordInput.getText().toString());
        editor.apply();

        Toast.makeText(getApplicationContext(),"Login Info Saved",Toast.LENGTH_SHORT).show();
    }

    public void displayData(View view)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("userInfo",Context.MODE_PRIVATE);

        String name=sharedPreferences.getString("userName","");
        String password=sharedPreferences.getString("passWord","");

        output.setText("USERNAME: "+name+" Password: "+password);

    }
}

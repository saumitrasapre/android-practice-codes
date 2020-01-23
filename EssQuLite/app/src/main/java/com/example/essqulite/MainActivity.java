package com.example.essqulite;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText userInput;
    Button insertButton;
    Button deleteButton;
    TextView outputText;

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput=(EditText)findViewById(R.id.userInput);
        insertButton=(Button)findViewById(R.id.insertButton);
        deleteButton=(Button) findViewById(R.id.deleteButton);
        outputText=(TextView)findViewById(R.id.outputText);

        dbHandler=new MyDBHandler(getApplicationContext(),null,null,1);
        printDatabase();

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items item=new Items(userInput.getText().toString());
                dbHandler.addNewItems(item);
                printDatabase();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToDelete=userInput.getText().toString();
                dbHandler.deleteItems(itemToDelete);
                printDatabase();
            }
        });

    }

    void printDatabase()
    {
        String dbString;
        dbString=dbHandler.dbToString();
        outputText.setText(dbString);
        userInput.setText("");
    }
}

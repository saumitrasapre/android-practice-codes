package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dogs[]={"Most aggressive dog breeds ranked:","14. You","13. cannot", "12. rank","11. them","10. because","9. aggression","8. isn't","7. breed","6. specific","5. it's","4. a","3. learned","2. behaviour","1. Chihuahuas"};
        ListAdapter dogadapter= new Custom_Adapter(getApplicationContext(),dogs);

        ListView myListView= (ListView)findViewById(R.id.myListView);
        myListView.setAdapter(dogadapter);
        final Intent mycatIntent=new Intent(getApplicationContext(),CatActivity.class);
        final Intent secondcatIntent= new Intent(getApplicationContext(),CatActivity2.class);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String choice;
                choice=String.valueOf(adapterView.getItemAtPosition(i));
                if(choice=="1. Chihuahuas")
                {
                    startActivity(secondcatIntent);
                    Toast.makeText(getApplicationContext(),"And fuck your mom " + ("\ud83d\ude3c"),Toast.LENGTH_SHORT).show();


                }
                else
                {
                    startActivity(mycatIntent);
                    Toast.makeText(getApplicationContext(),"Fuck you cunt " + ("\ud83d\ude3e"),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

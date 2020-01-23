package com.example.calendarviewwithevents;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.github.sundeepk.compactcalendarview.*;
import com.github.sundeepk.compactcalendarview.domain.Event;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatMonth= new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);

        compactCalendarView=(CompactCalendarView)findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        Event packageDelivery =new Event(Color.RED,1571628600000L,"Flipkart Delivery For C193");
        compactCalendarView.addEvent(packageDelivery);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                Context context=getApplicationContext();
               // Toast.makeText(context,dateClicked.toString(),Toast.LENGTH_LONG).show();

                if(dateClicked.toString().compareTo("Mon Oct 21 00:00:00 GMT+05:30 2019")==0)
                {
                    Toast.makeText(context,"Flipkart Delivery For C193",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context,"No Events Planned for that day",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
}

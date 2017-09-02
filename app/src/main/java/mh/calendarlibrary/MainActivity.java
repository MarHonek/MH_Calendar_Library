package mh.calendarlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    CalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FE s = new FE(this);

        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setAdapter(s);
    }
}

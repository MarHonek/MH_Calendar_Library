package mh.calendarlibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

import mh.calendarlibrary.adapters.CalendarAdapter;

/**
 * @author Martin Honek
 */
public class FE extends CalendarAdapter {

    Context context;

    public FE(Context context) {
        this.context = context;
    }


    @Override
    public View getView(MonthDay day, int index) {
        LayoutInflater vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.test, null);

        TextView textView = (TextView)v.findViewById(R.id.textView);
        textView.setText(String.valueOf(day.getDateTime().getDayOfMonth()));


        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(1);
        if(day.getDateTime().withTimeAtStartOfDay().isEqual(dateTime.withTimeAtStartOfDay())) {
            day.setBackgroundColor(Color.BLUE);
            if(!day.isCurrentMonth()) {
                day.setBackgroundColor(Color.GREEN);
            }
        }
        return v;
    }
}

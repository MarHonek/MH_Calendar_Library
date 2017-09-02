package mh.calendarlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import mh.calendarlibrary.adapters.CalendarAdapter;

/**
 * @author Martin Honek
 */
public class MonthView extends TableLayout {

    public MonthView(Context context, Month month, CalendarAdapter adapter) {
        this(context);
        init(month, adapter);
    }

    public MonthView(Context context) {
        super(context);
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Month month, CalendarAdapter adapter) {
        MonthDay firstDayByIndex = month.getMonthDay(0);
        adapter.onFirstIndexedDay(firstDayByIndex.getDateTime());

        for (int i = 0; i < month.getWeeksInMonth() ; i++) {
            TableRow tableRows = new TableRow(getContext());
            for (int j = 0; j < month.getDaysInWeek(); j++) {
                MonthDay monthDay = month.getMonthDay(i, j);

                View view = adapter.getView(monthDay, i * month.getDaysInWeek() + j);

                DayView dayView = new DayView(getContext(), monthDay , view);
                tableRows.addView(dayView);
            }
            addView(tableRows);
        }
    }
}

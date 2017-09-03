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

    public MonthView(Context context, Month month, CalendarAdapter adapter, CalendarView calendarView) {
        this(context);
        init(month, adapter, calendarView);
    }

    public MonthView(Context context) {
        super(context);
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Month month, CalendarAdapter adapter, final CalendarView calendarView) {
        MonthDay firstDayByIndex = month.getMonthDay(0);
        adapter.onFirstIndexedDay(firstDayByIndex.getDateTime());

        for (int i = 0; i < month.getWeeksInMonth() ; i++) {
            TableRow tableRows = new TableRow(getContext());
            for (int j = 0; j < month.getDaysInWeek(); j++) {
                final MonthDay monthDay = month.getMonthDay(i, j);

                View view = adapter.getView(monthDay, i * month.getDaysInWeek() + j);

                DayView dayView = new DayView(getContext(), monthDay , view);
                tableRows.addView(dayView);


                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(monthDay.isCurrentMonth()) {
                            calendarView.onDayClickListener.onDayClick(monthDay.getDateTime());
                        } else {
                            if(monthDay.getDayFlag() == MonthDay.PREV_MONTH_DAY) {
                                calendarView.showPreviousMonth();
                            }
                            else if(monthDay.getDayFlag() == MonthDay.NEXT_MONTH_DAY) {
                                calendarView.showNextMonth();
                            }
                        }
                    }
                });

                view.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if(monthDay.isCurrentMonth()) {
                            calendarView.onDayLongClickListener.onDayLongClick(monthDay.getDateTime());
                        }
                        return true;
                    }
                });
            }
            addView(tableRows);
        }
    }
}

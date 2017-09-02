package mh.calendarlibrary.adapters;

import android.view.View;

import org.joda.time.DateTime;

import mh.calendarlibrary.MonthDay;

/**
 * @author Martin Honek
 */
public abstract class CalendarAdapter {

    public abstract View getView(MonthDay day, int index);
    public void onFirstIndexedDay(DateTime dateTime) {}
}

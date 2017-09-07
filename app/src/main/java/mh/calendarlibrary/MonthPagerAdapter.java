package mh.calendarlibrary;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;
import org.joda.time.Months;

import mh.calendarlibrary.adapters.CalendarAdapter;

/**
 * @author Martin Honek
 */
public class MonthPagerAdapter extends PagerAdapter {

    private SparseArrayCompat<Month> monthCache = new SparseArrayCompat<>();
    private SparseArrayCompat<MonthView> monthViewCache = new SparseArrayCompat<>();

    private DateTime minDate, maxDate;
    private CalendarAdapter adapter;

    private Context context;

    private CalendarViewProperties properties;
    private CalendarView calendarView;

    public MonthPagerAdapter(Context context, CalendarViewProperties properties, CalendarAdapter adapter, CalendarView calendarView) {
        this.context = context;
        this.adapter = adapter;
        this.properties = properties;
        this.calendarView = calendarView;

        minDate = new DateTime(1900 , 1, 1, 0, 0);
        maxDate = new DateTime(2200, 12, 31, 0, 0);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MonthView monthView = new MonthView(context, getItem(position), adapter, calendarView);
        container.addView(monthView);
        monthViewCache.put(position, monthView);
        return monthView;
    }


    private Month getItem(int position) {
        Month monthItem = monthCache.get(position);
        if (monthItem != null) {
            return monthItem;
        }

        int numYear = position / 12;
        int numMonth = position % 12;

        int year = minDate.getYear() + numYear;
        int month = minDate.getMonthOfYear() + numMonth;

        monthItem = new Month(year, month, properties);
        monthCache.put(position, monthItem);

        return monthItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        MonthView monthView = (MonthView)object;
        container.removeView(monthView);
        monthViewCache.remove(position);
    }

    public void setCalendarAdapter(CalendarAdapter adapter) {
        this.adapter = adapter;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return Months.monthsBetween(minDate.withTimeAtStartOfDay(), maxDate.withTimeAtStartOfDay()).getMonths();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    protected int getIndexOfCurrentMonth() {
        DateTime dateTime = new DateTime();
        return getIndexOfMonth(dateTime.getYear(), dateTime.getMonthOfYear());
    }

    protected int getIndexOfMonth(int year, int month) {
        return (year - minDate.getYear()) * 12 + month - 1;
    }
    protected DateTime getDateOfIndex(int index) {
        int year =  index / 12 + minDate.getYear();
        int month = index % 12 + 1;
        return new DateTime(year, month, 1, 0, 0);
    }

    protected void deleteMonthCache() {
        monthCache = new SparseArrayCompat<>();
    }

}

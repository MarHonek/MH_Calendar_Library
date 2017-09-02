package mh.calendarlibrary;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import mh.calendarlibrary.adapters.CalendarAdapter;

/**
 * @author Martin Honek
 */
public class MonthPagerAdapter extends PagerAdapter {

    private SparseArrayCompat<Month> monthCache = new SparseArrayCompat<>();
    private SparseArrayCompat<MonthView> monthViewCache = new SparseArrayCompat<>();

    private DateTime minDate;
    private CalendarAdapter adapter;

    private Context context;

    private CalendarViewProperties properties;

    public MonthPagerAdapter(Context context, CalendarViewProperties properties, CalendarAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        this.properties = properties;
        minDate = new DateTime(1900 , 1, 1, 0, 0);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MonthView monthView = new MonthView(context, getItem(position), adapter);
        container.addView(monthView);
        monthViewCache.put(position, monthView);
        return monthView;

       /* int selectedDay = mSelectedDayCache.get(position, -1);
        if (selectedDay != -1) {
            monthView.setSelectedDay(selectedDay);
            mSelectedDayCache.removeAt(mSelectedDayCache.indexOfKey(position));
        }*/
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

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    protected int getIndexOfCurrentMonth() {
        DateTime dateTime = new DateTime();
        return getIndexOfMonth(dateTime.getYear(), dateTime.getMonthOfYear()-1);
    }

    protected int getIndexOfMonth(int year, int month) {
        return (year - minDate.getYear()) * 12 + month;
    }
}

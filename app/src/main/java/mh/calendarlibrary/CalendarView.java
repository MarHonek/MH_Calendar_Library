package mh.calendarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import mh.calendarlibrary.adapters.CalendarAdapter;

/**
 * @author Martin Honek
 */
public class CalendarView extends LinearLayout {

    CalendarViewProperties properties;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setAdapter(CalendarAdapter adapter) {
        createCalendar(adapter);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);
            try {

                CalendarViewProperties.CalendarViewPropertiesBuilder builder = new CalendarViewProperties.CalendarViewPropertiesBuilder();
                builder.currentMonthBackgroundColor(a.getColor(R.styleable.CalendarView_defaultCurrentMonthBackground, ContextCompat.getColor(getContext(), R.color.currentMonthBackgroundColor)));
                builder.previousMonthBackgroundColor(a.getColor(R.styleable.CalendarView_defaultPreviousAndNextMonthBackground, ContextCompat.getColor(getContext(), R.color.previousAndNextMonthBackgroundColor)));
                builder.nextMonthBackgroundColor(a.getColor(R.styleable.CalendarView_defaultPreviousAndNextMonthBackground, ContextCompat.getColor(getContext(), R.color.previousAndNextMonthBackgroundColor)));
                builder.todayIndicationColor(a.getColor(R.styleable.CalendarView_TodayIndicatorColor, ContextCompat.getColor(getContext(), R.color.colorPrimary)));

                properties = builder.build();
            } finally {
                a.recycle();
            }
        }
        setOrientation(VERTICAL);
    }

    private void createCalendar(CalendarAdapter calendarAdapter) {
        WeekLabelView weekLabelView = new WeekLabelView(getContext());
        addView(weekLabelView);

        ViewPager pager = new ViewPager(getContext());
        pager.setOffscreenPageLimit(1);
        addView(pager);

        MonthPagerAdapter adapter = new MonthPagerAdapter(getContext(), properties, calendarAdapter);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(mPageListener);
        pager.setCurrentItem(adapter.getIndexOfCurrentMonth());
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setAlpha(1 - Math.abs(position));
            }
        });
    }

    private ViewPager.OnPageChangeListener mPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            /*if (mIsChangedByUser) {
                mIsChangedByUser = false;
                return;
            }

            mAdapter.resetSelectedDay(position);*/
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}

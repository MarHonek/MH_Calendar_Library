package mh.calendarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import org.joda.time.DateTime;

import mh.calendarlibrary.adapters.CalendarAdapter;

/**
 * @author Martin Honek
 */
public class CalendarView extends LinearLayout {

    private CalendarViewProperties properties;
    private MonthPagerAdapter adapter;
    private ViewPager pager;

    OnDatePickListener onDatePickListener;
    OnDayClickListener onDayClickListener;
    OnDayLongClickListener onDayLongClickListener;

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

    public void setAdapter(CalendarAdapter calendarAdapter) {
        adapter = new MonthPagerAdapter(getContext(), properties, calendarAdapter, this);
        adapter.deleteMonthCache();
        pager.setAdapter(adapter);
        pager.setCurrentItem(adapter.getIndexOfCurrentMonth());
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

        WeekLabelView weekLabelView = new WeekLabelView(getContext());
        addView(weekLabelView);

        pager = new ViewPager(getContext());
        pager.setOffscreenPageLimit(1);
        addView(pager);

        pager.addOnPageChangeListener(mPageListener);
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setAlpha(1 - Math.abs(position));
            }
        });
    }

    
    private void showMonth(int position) {
        pager.setCurrentItem(position, true);
    }

    public void backToToday() {
        showMonth(adapter.getIndexOfCurrentMonth());
    }

    public void showNextMonth() {
        showMonth(pager.getCurrentItem() + 1);
    }

    public void showPreviousMonth() {
        showMonth(pager.getCurrentItem() - 1);
    }

    private ViewPager.OnPageChangeListener mPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
       
            if(onDatePickListener != null) {
                onDatePickListener.onDatePick(adapter.getDateOfIndex(position));
            }
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

    public interface OnDatePickListener {
        void onDatePick(DateTime dateTime);

    }

    public interface OnDayClickListener {
        void onDayClick(DateTime dateTime);

    }

    public interface OnDayLongClickListener {
        void onDayLongClick(DateTime dateTime);

    }

    public void setOnDatePickListener(OnDatePickListener listener) {
        onDatePickListener = listener;
    }

    public void setOnDayClickListener(OnDayClickListener listener) {
        onDayClickListener = listener;
    }

    public void setOnDayLongClickListener(OnDayLongClickListener listener) {
        onDayLongClickListener = listener;
    }
}

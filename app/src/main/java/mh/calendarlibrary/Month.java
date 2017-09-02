package mh.calendarlibrary;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Honek
 */
public class Month {
    private static final int DAYS_IN_WEEK = 7;
    private static final int TOTAL_WEEKS = 6;

    protected static final int PREV_MONTH_DAY = 1;
    protected static final int NEXT_MONTH_DAY = 2;

    private final int year;
    private final int month;

    int delta;

    private CalendarViewProperties properties;

    private int totalDays;
    private List<MonthDay> monthDayList = new ArrayList<>();

    protected Month(int year, int month, CalendarViewProperties properties) {
        this.year = year;
        this.month = month;
        this.properties = properties;
        addMonthDay(year, month);
    }

    private DateTime generateWorkingCalendar(int year, int month) {
        DateTime dateTime = new DateTime(year, month, 1, 0, 0);
        totalDays = dateTime.dayOfMonth().getMaximumValue();
        int dayOfWeek = dateTime.getDayOfWeek();
        delta = dayOfWeek - 1;
        dateTime = dateTime.minusDays(delta);
        return dateTime;
    }

    private void addMonthDay(int year, int month) {
        DateTime dateTime = generateWorkingCalendar(year, month);

        for (int i = 0; i < TOTAL_WEEKS; i++) {
            for (int j = 0; j < DAYS_IN_WEEK; j++) {
                MonthDay monthDay = new MonthDay(dateTime);

                int currentDays = i * DAYS_IN_WEEK + j;
                monthDay.setCurrentMonth(!(currentDays < delta ||
                        currentDays >= totalDays + delta));

                monthDay.setBackgroundColor(properties.getCurrentMonthBackgroundColor());

                if (currentDays < delta) {
                    monthDay.setDayFlag(MonthDay.PREV_MONTH_DAY);
                    monthDay.setBackgroundColor(properties.getPreviousMonthBackgroundColor());
                } else if (currentDays >= totalDays + delta) {
                    monthDay.setDayFlag(MonthDay.NEXT_MONTH_DAY);
                    monthDay.setBackgroundColor(properties.getNextMonthBackgroundColor());
                }


                DateTime today = new DateTime();
                if(dateTime.withTimeAtStartOfDay().isEqual(today.withTimeAtStartOfDay())) {
                    monthDay.setToday(true);
                    monthDay.setTodayIndicatorColor(properties.getTodayIndicationColor());
                }

                monthDayList.add(monthDay);
                dateTime = dateTime.plusDays(1);
            }
        }
    }

    protected int getWeeksInMonth() {
        return TOTAL_WEEKS;
    }

    protected int getDaysInWeek() {
        return DAYS_IN_WEEK;
    }

    protected MonthDay getMonthDay(int index) {
        return monthDayList.size() <= index ? null : monthDayList.get(index);
    }

    protected MonthDay getMonthDay(int xIndex, int yIndex) {
        return getMonthDay(xIndex * DAYS_IN_WEEK + yIndex);
    }
}

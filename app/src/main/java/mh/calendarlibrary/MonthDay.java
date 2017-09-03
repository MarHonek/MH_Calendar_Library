package mh.calendarlibrary;

import org.joda.time.DateTime;

/**
 * @author Martin Honek
 */
public class MonthDay {

    private boolean isCurrentMonth;
    private boolean isToday;
    private int todayIndicatorColor;
    private DateTime dateTime;
    private int backgroundColor;

    protected static final int PREV_MONTH_DAY = 1;

    public void setToday(boolean today) {
        isToday = today;
    }

    public int getTodayIndicatorColor() {
        return todayIndicatorColor;
    }

    public void setTodayIndicatorColor(int todayIndicatorColor) {
        this.todayIndicatorColor = todayIndicatorColor;
    }

    protected static final int NEXT_MONTH_DAY = 2;
    int dayFlag;

    public MonthDay(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isToday() {
        DateTime today = new DateTime();
        return today.withTimeAtStartOfDay().isEqual(dateTime.withTimeAtStartOfDay());
    }

    public void setCurrentMonth(boolean isCurrentMonth) {
        this.isCurrentMonth = isCurrentMonth;
    }

    public boolean isCurrentMonth() {
        return isCurrentMonth;
    }

    public void setDayFlag(int flag) {
        this.dayFlag = flag;
    }

    public int getDayFlag() {
        return dayFlag;
    }
}

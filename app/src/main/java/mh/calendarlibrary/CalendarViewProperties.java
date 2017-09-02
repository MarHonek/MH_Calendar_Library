package mh.calendarlibrary;

/**
 * @author Martin Honek
 */
public class CalendarViewProperties {

    private int currentMonthBackgroundColor;
    private int previousMonthBackgroundColor;
    private int nextMonthBackgroundColor;
    private int todayIndicationColor;

    public CalendarViewProperties(CalendarViewPropertiesBuilder calendarViewPropertiesBuilder) {
        this.currentMonthBackgroundColor = calendarViewPropertiesBuilder.currentMonthBackgroundColor;
        this.previousMonthBackgroundColor = calendarViewPropertiesBuilder.previousMonthBackgroundColor;
        this.nextMonthBackgroundColor = calendarViewPropertiesBuilder.nextMonthBackgroundColor;
        this.todayIndicationColor = calendarViewPropertiesBuilder.todayIndicationColor;
    }


    public int getCurrentMonthBackgroundColor() {
        return currentMonthBackgroundColor;
    }

    public int getNextMonthBackgroundColor() {
        return nextMonthBackgroundColor;
    }

    public int getPreviousMonthBackgroundColor() {
        return previousMonthBackgroundColor;
    }

    public int getTodayIndicationColor() {
        return todayIndicationColor;
    }

    public static class CalendarViewPropertiesBuilder {
        private int currentMonthBackgroundColor;
        private int previousMonthBackgroundColor;
        private int nextMonthBackgroundColor;
        private int todayIndicationColor;

        public CalendarViewPropertiesBuilder currentMonthBackgroundColor(int currentMonthBackgroundColor) {
            this.currentMonthBackgroundColor = currentMonthBackgroundColor;
            return this;
        }

        public CalendarViewPropertiesBuilder previousMonthBackgroundColor(int previousMonthBackgroundColor) {
            this.previousMonthBackgroundColor = previousMonthBackgroundColor;
            return this;
        }

        public CalendarViewPropertiesBuilder nextMonthBackgroundColor(int nextMonthBackgroundColor) {
            this.nextMonthBackgroundColor = nextMonthBackgroundColor;
            return this;
        }

        public CalendarViewPropertiesBuilder todayIndicationColor(int todayIndicationColor) {
            this.todayIndicationColor = todayIndicationColor;
            return this;
        }

        public CalendarViewProperties build() {
            return new CalendarViewProperties(this);
        }
    }
}

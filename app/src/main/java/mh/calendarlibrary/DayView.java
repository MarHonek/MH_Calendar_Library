package mh.calendarlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;

/**
 * @author Martin Honek
 */
public class DayView extends LinearLayout {

    private MonthDay monthDay;
    private View view;


    private static final int backgroundOpacity = 120;
    private static final float viewOpacity = 0.3f;


    public DayView(Context context) {
        super(context);
    }

    public DayView(Context context, MonthDay monthDay, View view) {
        this(context);
        this.monthDay = monthDay;
        this.view = view;
        setWillNotDraw(false);
        init();
    }


    public DayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
    }

    private void init() {
        view.setBackgroundColor(Color.TRANSPARENT);

        monthDay.getBackgroundColor();

        if(!monthDay.isCurrentMonth()) {
            view.setAlpha(0.5f);
        }

        addView(view, new TableRow.LayoutParams());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        paint.setColor(monthDay.getBackgroundColor());
        canvas.drawRect(new Rect(0,0,view.getWidth(), view.getHeight()), paint);

        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1f);
        canvas.drawRect(new Rect(0,0,view.getWidth()-1, view.getHeight()-1), paint);

        if(monthDay.isToday()) {
            if(monthDay.isCurrentMonth()) {
                paint.setColor(monthDay.getTodayIndicatorColor());
            } else {
                int currentMonthColor = monthDay.getTodayIndicatorColor();
                int notCurrentMonthColor = Color.argb(backgroundOpacity, Color.red(currentMonthColor), Color.green(currentMonthColor), Color.blue(currentMonthColor));
                paint.setColor(notCurrentMonthColor);
            }

            paint.setStrokeWidth(20f);
            canvas.drawRect(new Rect(1, 1, view.getWidth()-1, view.getHeight()-1), paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getContext().getResources().getDisplayMetrics().widthPixels / 7;
        setMeasuredDimension(width, getMeasuredHeight());
    }
}

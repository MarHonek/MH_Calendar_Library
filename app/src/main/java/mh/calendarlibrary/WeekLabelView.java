package mh.calendarlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.View;

import mh.calendarlibrary.exceptions.InvalidDaysCountException;

/**
 * @author Martin Honek
 */
final class WeekLabelView extends View {
    private static final int DAYS_IN_WEEK = 7;
    private final Region[] weekRegion = new Region[DAYS_IN_WEEK];
    private static String[] week = {"Po", "Út", "St", "Čt", "Pá", "So", "Ne"};

    private Paint paint;


    public WeekLabelView(Context context) {
        super(context);
        init();
    }

    public WeekLabelView(Context context, String[] week) {
        this(context);
        if(week.length != DAYS_IN_WEEK) {
            throw new InvalidDaysCountException();
        }
        this.week = week;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        int itemWidth = (int) (width / 7f);
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            weekRegion[i].set(i * itemWidth, 0, (i + 1) * itemWidth, height);
        }

        paint.setTextSize(width / 25f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        Paint.FontMetrics fm = paint.getFontMetrics();
        int heightSize = (int) Math.ceil(fm.descent - fm.ascent) +
                getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint.FontMetrics fm = paint.getFontMetrics();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            Rect rect = weekRegion[i].getBounds();
            float centerY = rect.height() / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
            canvas.drawText(week[i], rect.centerX(), centerY, paint);
        }
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.GRAY);
        setPadding(0, 10, 0, 10);

        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            Region region = new Region();
            weekRegion[i] = region;
        }
    }
}

package de.htw.toto.moco.app.gui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class CompassView extends View {
    private float direction;

    public CompassView(Context context) {
        super(context);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int r;
        if (w > h) {
            r = h / 2;
        } else {
            r = w / 2;
        }

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.WHITE);

        canvas.drawCircle(w / 2, h / 2, r, paint);

        paint.setColor(Color.RED);
        canvas.drawLine(w / 2, h / 2, (float) (w / 2 + r * Math.sin(-direction)),
                        (float) (h / 2 - r * Math.cos(-direction)), paint);

    }

    public void update(float dir) {
        direction = dir;
        invalidate();
    }
}

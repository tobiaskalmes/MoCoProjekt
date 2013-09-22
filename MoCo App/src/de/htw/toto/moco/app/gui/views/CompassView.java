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
    private Float bearing;

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
            r = (h / 2) - 15;
        } else {
            r = (w / 2) - 15;
        }

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setColor(Color.rgb(167, 167, 167));
        canvas.drawCircle(w / 2, h / 2, r, paint);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        canvas.drawCircle(w / 2, h / 2, r + 6, paint);
        canvas.drawCircle(w / 2, h / 2, r - 6, paint);

        paint.setStrokeWidth(7);
        paint.setColor(Color.WHITE);
        canvas.drawLine(w / 2, h / 2, (float) (w / 2 + (r + 2) * Math.sin(-direction)),
                        (float) (h / 2 - (r + 2) * Math.cos(-direction)), paint);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        canvas.drawLine(w / 2, h / 2, (float) (w / 2 + r * Math.sin(-direction)),
                        (float) (h / 2 - r * Math.cos(-direction)), paint);
        if (bearing != null) {
            paint.setStrokeWidth(7);
            paint.setColor(Color.WHITE);
            canvas.drawLine(w / 2, h / 2,
                            (float) (w / 2 + (r + 2) * Math.sin(-(direction - (bearing * Math.PI / 180)))),
                            (float) (h / 2 - (r + 2) * Math.cos(-(direction - (bearing * Math.PI / 180)))), paint);
            paint.setStrokeWidth(5);
            paint.setColor(Color.GREEN);
            canvas.drawLine(w / 2, h / 2, (float) (w / 2 + r * Math.sin(-(direction - (bearing * Math.PI / 180)))),
                            (float) (h / 2 - r * Math.cos(-(direction - (bearing * Math.PI / 180)))), paint);
        }

        paint.setStrokeWidth(25);
        paint.setColor(Color.RED);
        canvas.drawCircle(w / 2, h / 2, 12, paint);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(w / 2, h / 2, 24, paint);
    }

    public void update(float dir) {
        direction = dir;
        bearing = null;
        invalidate();
    }

    public void update(float direction, float bearing) {
        this.direction = direction;
        this.bearing = bearing;
        invalidate();
    }
}

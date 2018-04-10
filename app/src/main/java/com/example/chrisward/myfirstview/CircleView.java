package com.example.chrisward.myfirstview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CircleView extends View {
    private int circleColour;
    private Paint paint = new Paint();
    private float circleRadius;
    private boolean active = false;
    private int activeColour;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CircleView,
                defStyleAttr,
                0
        );
        circleColour = a.getColor(R.styleable.CircleView_inactive_background, Color.GRAY);
        activeColour = a.getColor(R.styleable.CircleView_active_background, Color.GRAY);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(active ? Color.RED : circleColour);
        setBackgroundColor(Color.TRANSPARENT);
        canvas.drawCircle(circleRadius, circleRadius, circleRadius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width == 0 && height == 0) {
            circleRadius = 0;
        } else {
            circleRadius = (width > height ? height : width) / 2;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            performClick();
        }
        return true;
    }

    @Override
    public boolean performClick() {
        active = !active;
        invalidate();
        return super.performClick();
    }
}

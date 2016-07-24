package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * @author chenupt create on 2015/10/16
 */
public class CircleProgressView extends View {

    private Paint paint;
    private RectF rectF;
    private float sweepAngle;
    private float startAngle;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
//        init(typedArray);
    }

    public void init(TypedArray typedArray, PullToRefreshBase.Mode mode){
        startAngle = typedArray.getFloat(R.styleable.PullToRefresh_cpvStartAngle, -90f);
        float strokeWidth = typedArray.getFloat(R.styleable.PullToRefresh_cpvStrokeWidth, 6.0f);
        int color;
        switch (mode) {
            case PULL_FROM_START:
            default:
                color = typedArray.getColor(R.styleable.PullToRefresh_cpvStartColor, Color.BLACK);
                break;

            case PULL_FROM_END:
                color = typedArray.getColor(R.styleable.PullToRefresh_cpvEndColor, Color.BLACK);
                break;
        }
//      typedArray.recycle();

        rectF = new RectF();
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        int padding = (int) (strokeWidth / 2);
        setPadding(padding, padding, padding, padding);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        rectF.left = getPaddingLeft();
        rectF.top = getPaddingTop();
        rectF.right = getWidth() - getPaddingRight();
        rectF.bottom = getHeight() - getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public void updateSweepAngle(float sweepAngle) {
        setSweepAngle(sweepAngle);
        postInvalidate();
    }

    public void startSweep(long millisInFuture, long countDownInterval){
        CountDownTimer countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                sweepAngle = sweepAngle + 1;
                invalidate();
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }


}

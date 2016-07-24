package com.csy.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.csy.weather.domin.Constant;

/**
 * Created by csy on 2016/7/24.
 */
public class CircleView extends View {
    private Paint paint;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max;
    private int progress;
    private boolean textIsDisplayable;
    private int style;
    public static final int STROKE = 0;
    public static final int FILL = 1;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
        max = 300;
        roundColor = Color.WHITE;
        roundProgressColor = Color.BLUE;
        textColor = Color.BLUE;
        textSize = 80;
        roundWidth = 20;
        textIsDisplayable = true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外层的大圆环
        int center_w = getWidth()/2;
        int center_h = getWidth()/3;
        //int radius = (int)(center - roundWidth/2);
        int radius = Constant.displayWidth / 4;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setAntiAlias(true); //消除锯齿
        RectF ovalMax = new RectF(center_w - radius, center_h - radius, center_w + radius, center_h + radius);
        canvas.drawArc(ovalMax, 120, 300, false, paint);
        //画进百分比
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT);//设置字体
        float textWidth = paint.measureText(progress + "");
        if(textIsDisplayable && progress != 0 && style == STROKE){
            canvas.drawText(progress + "", center_w - textWidth/2, center_h - textWidth/2, paint);
        }
        //
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        String grade = getGrade(progress);
        textWidth = paint.measureText(grade);
        canvas.drawText(grade, center_w - textWidth /2, center_h + textWidth/2, paint);

        //
        paint.setColor(Color.GRAY);
        paint.setTextSize(30);
        canvas.drawText("健康", center_w - radius /2, center_h + radius * 3/4, paint);
        canvas.drawText("污染", center_w + radius /2 - paint.measureText("污染"), center_h + radius * 3/4, paint);
        //画圆弧， 画圆环的进度
        paint.setStrokeWidth(roundWidth);//圆环的宽度
        paint.setColor(roundProgressColor);//设置进度的颜色
        RectF ovalProgress = new RectF(center_w - radius, center_h - radius, center_w + radius, center_h + radius);
        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(ovalProgress, 120, 300 * progress / max, false, paint);
                break;
            case FILL:
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress != 0)
                    canvas.drawArc(ovalProgress, 120, 300 * progress / max, true, paint);
                break;
            default:
                break;
        }
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public synchronized int getMax() {
        return max;
    }

    public void setMax(int max) {
        if(max < 0)
            max = 100;
        this.max = max;
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if(progress < 0)
            progress = 0;
        if(progress > max)
            progress = max;
        this.progress = progress;
        postInvalidate();
    }

    public boolean isTextIsDisplayable() {
        return textIsDisplayable;
    }

    public void setTextIsDisplayable(boolean textIsDisplayable) {
        this.textIsDisplayable = textIsDisplayable;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    private String getGrade(int api){
        String grade = "优";
        if(api > 50 && api <= 100){
            grade = "良";
        }else if(api > 100 && api <= 150){
            grade = "轻度污染";
        }else if(api > 150 && api <= 200){
            grade = "中度污染";
        }else if(api > 200){
            grade = "重度污染";
            if(api > 300)
                this.setProgress(300);
        }
        return grade;
    }
}


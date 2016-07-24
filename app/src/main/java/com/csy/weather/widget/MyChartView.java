package com.csy.weather.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/**
 * Created by csy on 2016/7/24.
 */
public class MyChartView extends View {

    private Context context;
    private Paint paint;
    private HashMap<Integer, Double> dataMap;
    String[] strX;
    String[] strY;
    public MyChartView(Context context) {
        this(context, null);
    }
    public MyChartView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }
    public MyChartView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.context = context;
        paint = new Paint();


    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //画y轴分割线
        int zeroX = getWidth()/16;
        int zeroY = getHeight() * 4/5;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        canvas.drawLine(zeroX, zeroY, getWidth() * 13/16, zeroY, paint);
        for(int i = 0; i < strY.length; i++){
            paint.setStrokeWidth(1);
            paint.setColor(Color.BLACK);
            canvas.drawLine(zeroX, zeroY - getHeight() * i/5, getWidth() * 13/16, zeroY - getHeight()* i/5, paint);
            paint.setColor(Color.GRAY);
            paint.setTextSize(30);
            canvas.drawText(strY[i], getWidth() * 13/16, zeroY - getHeight()* i/5, paint);
        }
        //画x轴
        for (int i = 0; i < strX.length; i++) {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawLine(zeroX + getWidth() * (2*i)/16, zeroY, zeroX + getWidth() * (2*i)/16, zeroY + 20, paint);
            paint.setColor(Color.GRAY);
            paint.setTextSize(30);
            canvas.drawText(strX[i], zeroX + getWidth() * (2*i)/16 - paint.measureText(strX[i])/2, zeroY + 50, paint);
        }
        //画折线图
        if(dataMap != null){
            for(int i = 0; i < dataMap.size() - 1; i++){
                float x_1 = zeroX + getWidth() * 3/4 * i/60;
                float y_1 = (float)(zeroY - dataMap.get(i) * getHeight() * 3/5);
                float x_2 = zeroX + getWidth() * 3/4 * (i + 1)/60;
                float y_2 = (float)(zeroY - dataMap.get(i+1) * getHeight() * 3/5);
                paint.setColor(Color.BLUE);
                paint.setStrokeWidth(5);
                canvas.drawLine(x_1, y_1, x_2, y_2, paint);
            }
        }
    }

    public HashMap<Integer, Double> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<Integer, Double> dataMap) {
        this.dataMap = dataMap;
        postInvalidate();
    }

    public String[] getStrX() {
        return strX;
    }

    public void setStrX(String[] strX) {
        this.strX = strX;
    }

    public String[] getStrY() {
        return strY;
    }

    public void setStrY(String[] strY) {
        this.strY = strY;
    }
}

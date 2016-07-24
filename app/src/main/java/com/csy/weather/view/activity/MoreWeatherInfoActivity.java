package com.csy.weather.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.csy.weather.R;
import com.csy.weather.common.BaseActivity;
import com.csy.weather.domin.NextHourWeatherInfo;
import com.csy.weather.presenter.MoreWeatherInfoActivityPresenter;
import com.csy.weather.util.LocationUtil;
import com.csy.weather.view.iview.IMoreWeatherInfoActivity;
import com.csy.weather.widget.CircleView;
import com.csy.weather.widget.MyChartView;

import java.util.HashMap;


public class MoreWeatherInfoActivity extends BaseActivity implements BDLocationListener,IMoreWeatherInfoActivity {

    private EditText etAddress;
    private LinearLayout llAirInfo;
    private LinearLayout llNextHourInfo;
    private TextView tvNextHourInfo;

    private MyChartView myChartView;
    private CircleView circleView;

    private LocationUtil locationUtil;
    public double myLatitude, myLongitude;
    public String myDistrict, myCity, myAddress;
    public MoreWeatherInfoActivityPresenter presenter;
    private HashMap<Integer, Double> dataMap  = new HashMap<Integer, Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_weather_info);
        locationUtil = new LocationUtil(this);
        presenter = new MoreWeatherInfoActivityPresenter(this);
        initView();
        locationUtil.start();
    }

    private void initView(){
        etAddress = findView(R.id.etAddress);
        //chartNextHour = findView(R.id.chartNextHour);
        llAirInfo = findView(R.id.llAirInfo);
        llNextHourInfo = findView(R.id.llNextHourInfo);
        tvNextHourInfo = findView(R.id.tvNextHourInfo);

        llAirInfo.setAlpha(0.7f);
        circleView = new CircleView(this);
        llAirInfo.addView(circleView);

        myChartView = new MyChartView(this);
        String[] strY = {"无雨","小雨","中雨","大雨"};
        String[] strX = {"0", "10", "20", "30", "40", "50", "60"};

        myChartView.setStrX(strX);
        myChartView.setStrY(strY);
        llNextHourInfo.addView(myChartView);
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        // TODO Auto-generated method stub
        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();
        myAddress = location.getAddrStr();
        myCity = location.getCity();
        myDistrict = location.getDistrict();
        if(!(myDistrict == null || myDistrict.length() <= 0)){
            locationUtil.stop();
            etAddress.setText(myAddress);
            presenter.getNextHourWeatherInfo(String.valueOf(myLongitude), String.valueOf(myLatitude));
            presenter.getAirInfo(myCity);
        } else{
            Toast.makeText(this, "定位失败， 请检查网络后再试", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setNextHourWeatherInfoData(NextHourWeatherInfo nextHourWeatherInfo) {
        tvNextHourInfo.setText(nextHourWeatherInfo.getDescription());
        for(int i = 0; i < nextHourWeatherInfo.getDataList().size() ; i++ ){
            dataMap.put(i, nextHourWeatherInfo.getDataList().get(i));
        }
        myChartView.setDataMap(dataMap);

    }

    @Override
    public void updateAirInfo(final int aqi) {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <= aqi; i++) {
                    try {
                        circleView.setProgress(i);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        if(aqi <= 100){
            llAirInfo.setBackgroundColor(Color.parseColor("#00EE00"));
        }else if(aqi > 100 && aqi <= 150){
            llAirInfo.setBackgroundColor(Color.parseColor("#FFF68F"));
        }else if(aqi > 150){
            llAirInfo.setBackgroundColor(Color.parseColor("#FF7F24"));
        }
    }
}

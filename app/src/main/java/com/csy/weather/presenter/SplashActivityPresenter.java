package com.csy.weather.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.csy.weather.domin.Constant;
import com.csy.weather.domin.WeatherInfo;
import com.csy.weather.model.db.DBOperation;
import com.csy.weather.model.db.IDBOperation;
import com.csy.weather.model.net.GetWeatherInfo;
import com.csy.weather.model.net.GetWeatherInfoListener;
import com.csy.weather.model.net.IGetWeatherInfo;
import com.csy.weather.util.AppLog;
import com.csy.weather.util.JumpUtil;
import com.csy.weather.util.LocationUtil;
import com.csy.weather.util.WeatherInfoParser;
import com.csy.weather.view.activity.SplashActivity;
import com.csy.weather.view.iview.ISplashActivity;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/17.
 */


public class SplashActivityPresenter {
    private Context context;
    private ISplashActivity activity;
    private IDBOperation dbOperation;
    private IGetWeatherInfo getWeatherInfo;
    private ArrayList<String> cityList = new ArrayList<String>();
    private WeatherInfoParser parser;
    private LocationUtil locationUtil;
    private String mainCity;
    private SharedPreferences sharedPreferences;

    public SplashActivityPresenter(Context context){
        activity = (SplashActivity)context;
        this.context = context;
        dbOperation = new DBOperation(context);
        getWeatherInfo = new GetWeatherInfo();
        sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
    }

    public void getAllCityWeather(){
        cityList = dbOperation.getAllCity();
        if(cityList.size() == 0){
            locationUtil = new LocationUtil(context);
            locationUtil.start();
        }else {
            for (int i = 0; i < cityList.size(); i++) {
                String cityName = cityList.get(i);
                String weatherInfoStr = dbOperation.getWeather(cityName);
                parser = new WeatherInfoParser(weatherInfoStr);
                Constant.weatherInfoHashMap.put(cityName, parser.getWeatherInfo());
            }
            mainCity = sharedPreferences.getString("mainCity", "");
            if(TextUtils.isEmpty(mainCity)){
                mainCity = cityList.get(0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("mainCity", mainCity);
                editor.commit();
            }
            getMainCityWeather(mainCity);
        }
    }

    public void getMainCityWeather(final String cityName){
        AppLog.e("mainCity", cityName);
        getWeatherInfo.getTodayWeatherInfo(cityName, context, new GetWeatherInfoListener() {
            @Override
            public void getSuccess(final WeatherInfo weatherInfo) {
                JumpUtil.jumpToMainActivity((SplashActivity)activity);
            }

            @Override
            public void getFailed() {

            }
        });
    }

}

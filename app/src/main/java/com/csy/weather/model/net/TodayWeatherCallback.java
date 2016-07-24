package com.csy.weather.model.net;

import android.content.Context;

import com.csy.weather.domin.Constant;
import com.csy.weather.model.db.DBOperation;
import com.csy.weather.model.db.IDBOperation;
import com.csy.weather.util.WeatherInfoParser;

/**
 * Created by csy on 2016/7/2.
 */
public class TodayWeatherCallback extends HttpCallBack {
    private IDBOperation dbOperation;
    private String cityName;
    private GetWeatherInfoListener listener;
    private WeatherInfoParser parser;

    public TodayWeatherCallback(Context context, String cityName, GetWeatherInfoListener listener){
        dbOperation = new DBOperation(context);
        this.cityName = cityName;
        this.listener = listener;
    }

    @Override
    public void operate(String result) {
        parser = new WeatherInfoParser(result);
        listener.getSuccess(parser.getWeatherInfo());
        Constant.weatherInfoHashMap.put(cityName, parser.getWeatherInfo());
        if(dbOperation.checkCity(cityName)){
            dbOperation.updateWeather(cityName, result);
        }else {
            dbOperation.insertCity(cityName, result);
        }
    }

    @Override
    public void fail() {
        listener.getFailed();
    }
}

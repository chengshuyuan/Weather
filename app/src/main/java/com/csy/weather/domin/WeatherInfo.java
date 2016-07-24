package com.csy.weather.domin;

/**
 * Created by csy on 2016/7/2.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherInfo implements Serializable{

    private TodayWeatherInfo todayWeatherInfo;
    private ArrayList<ForecastWeatherInfo> forecastWeatherInfos;
    private ArrayList<ZhishuInfo> zhishuInfos;

    public TodayWeatherInfo getTodayWeatherInfo() {
        return todayWeatherInfo;
    }
    public void setTodayWeatherInfo(TodayWeatherInfo todayWeatherInfo) {
        this.todayWeatherInfo = todayWeatherInfo;
    }
    public ArrayList<ForecastWeatherInfo> getForecastWeatherInfos() {
        return forecastWeatherInfos;
    }
    public void setForecastWeatherInfos(
            ArrayList<ForecastWeatherInfo> forecastWeatherInfos) {
        this.forecastWeatherInfos = forecastWeatherInfos;
    }
    public ArrayList<ZhishuInfo> getZhishuInfos() {
        return zhishuInfos;
    }
    public void setZhishuInfos(ArrayList<ZhishuInfo> zhishuInfos) {
        this.zhishuInfos = zhishuInfos;
    }

}

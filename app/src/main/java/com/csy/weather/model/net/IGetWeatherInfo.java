package com.csy.weather.model.net;

import android.content.Context;

/**
 * Created by csy on 2016/7/2.
 */
public interface IGetWeatherInfo {
    void getTodayWeatherInfo(String cityName,  Context context, GetWeatherInfoListener listener);

    void getAirInfo(String cityName, GetAirInfoListener listener);

    void getNextHourWeatherInfo(String longitude, String latitude, GetNextHourWeatherInfoListener listener);
}

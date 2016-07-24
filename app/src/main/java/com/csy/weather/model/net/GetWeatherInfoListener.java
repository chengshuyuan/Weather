package com.csy.weather.model.net;

import com.csy.weather.domin.WeatherInfo;

/**
 * Created by csy on 2016/7/2.
 */
public interface GetWeatherInfoListener {
    void getSuccess(WeatherInfo weatherInfo);

    void getFailed();
}

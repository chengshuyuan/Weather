package com.csy.weather.model.net;

import com.csy.weather.domin.NextHourWeatherInfo;

/**
 * Created by csy on 2016/7/24.
 */
public interface GetNextHourWeatherInfoListener {
    void getSuccess(NextHourWeatherInfo nextHourWeatherInfo);

    void getFailed();
}

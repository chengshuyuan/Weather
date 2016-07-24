package com.csy.weather.view.iview;

import com.csy.weather.domin.WeatherInfo;

/**
 * Created by csy on 2016/7/2.
 */
public interface IWeatherInfoFragment {
    void updateView(WeatherInfo weatherInfo);

    void getFail();
}

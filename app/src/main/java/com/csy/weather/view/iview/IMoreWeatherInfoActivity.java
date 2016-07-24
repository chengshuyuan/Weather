package com.csy.weather.view.iview;

import com.csy.weather.domin.NextHourWeatherInfo;

/**
 * Created by csy on 2016/7/24.
 */
public interface IMoreWeatherInfoActivity {

    public void setNextHourWeatherInfoData(NextHourWeatherInfo nextHourWeatherInfo);

    public void updateAirInfo(int aqi);
}

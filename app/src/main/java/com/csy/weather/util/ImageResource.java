package com.csy.weather.util;

import com.csy.weather.R;

/**
 * Created by csy on 2016/7/2.
 */
public class ImageResource {
    public static int getWeatherBitMapResource(String weather) {
        if (weather.equals("晴")) {
            return R.drawable.weathericon_condition_01;
        } else if (weather.equals("多云")) {
            return R.drawable.weathericon_condition_02;
        } else if (weather.equals("阴")) {
            return R.drawable.weathericon_condition_04;
        } else if (weather.equals("雾")) {
            return R.drawable.weathericon_condition_05;
        } else if (weather.equals("沙尘暴")) {
            return R.drawable.weathericon_condition_06;
        } else if (weather.equals("阵雨")) {
            return R.drawable.weathericon_condition_07;
        } else if (weather.equals("小雨") || weather.equals("中雨")) {
            return R.drawable.weathericon_condition_08;
        } else if (weather.equals("大雨")) {
            return R.drawable.weathericon_condition_09;
        } else if (weather.equals("雷阵雨")) {
            return R.drawable.weathericon_condition_10;
        } else if (weather.equals("小雪")) {
            return R.drawable.weathericon_condition_11;
        } else if (weather.equals("大雪")) {
            return R.drawable.weathericon_condition_12;
        } else if (weather.equals("雨夹雪")) {
            return R.drawable.weathericon_condition_13;
        } else {
            return R.drawable.weathericon_condition_17;
        }
    }


    public static int getBackgroundResource(String weather) {
        if (weather.equals("晴")) {
            return R.drawable.bg_sun;
        } else if (weather.equals("多云")) {
            return R.drawable.bg_cloudy_day;
        } else if (weather.equals("阴")) {
            return R.drawable.bg_overcast;
        } else if (weather.equals("雾")) {
            return R.drawable.bg_fog;
        } else if (weather.equals("沙尘暴")) {
            return R.drawable.bg_sand_storm;
        } else if (weather.equals("小雨") || weather.equals("小到中雨")||weather.equals("阵雨")) {
            return R.drawable.bg_rain2;
        } else if (weather.equals("大雨")) {
            return R.drawable.bg_rain1;
        } else if (weather.equals("雷阵雨")) {
            return R.drawable.bg_thundershower;
        } else if (weather.equals("小雪")) {
            return R.drawable.bg_snow;
        } else if (weather.equals("大雪")) {
            return R.drawable.bg_snow2;
        } else if (weather.equals("雨夹雪")) {
            return R.drawable.bg_snow;
        } else {
            return R.drawable.bg_normal;
        }
    }
}

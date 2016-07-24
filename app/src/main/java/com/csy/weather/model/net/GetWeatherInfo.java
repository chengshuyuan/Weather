package com.csy.weather.model.net;


import android.content.Context;

import com.csy.weather.model.db.ImportDB;
import com.csy.weather.util.AppLog;

/**
 * Created by csy on 2016/7/2.
 */
public class GetWeatherInfo implements IGetWeatherInfo {

    private ImportDB importDB;

    @Override
    public void getTodayWeatherInfo(String cityName, Context context, GetWeatherInfoListener listener){
        importDB = new ImportDB(context);
        String cityCode = importDB.getCityCode(cityName);
        AppLog.e("cityCode", cityCode);
        String url = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + cityCode;
        HttpUtil.sendRequest(url, new TodayWeatherCallback(context, cityName, listener));
    }

    @Override
    public void getAirInfo(String cityName, GetAirInfoListener listener){
        String url = "http://apistore.baidu.com/microservice/aqi?city=" + cityName;
        HttpUtil.sendRequest(url, new AirInfoCallback(listener));
    }

    @Override
    public void getNextHourWeatherInfo(String longitude, String latitude, GetNextHourWeatherInfoListener listener){
        String url = "http://caiyunapp.com/fcgi-bin/v1/api.py?lonlat=" +
                longitude + "," + latitude +
                "&format=json&product=minutes_prec_only&token=hAz61sD7Rc02uCLF";
        HttpUtil.sendRequest(url, new NextHourWeatherCallback(listener));
    }
}

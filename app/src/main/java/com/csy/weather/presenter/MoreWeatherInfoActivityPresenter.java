package com.csy.weather.presenter;

import com.csy.weather.domin.NextHourWeatherInfo;
import com.csy.weather.model.net.GetAirInfoListener;
import com.csy.weather.model.net.GetNextHourWeatherInfoListener;
import com.csy.weather.model.net.GetWeatherInfo;
import com.csy.weather.view.iview.IMoreWeatherInfoActivity;

/**
 * Created by csy on 2016/7/24.
 */
public class MoreWeatherInfoActivityPresenter {

    private IMoreWeatherInfoActivity activity;
    private GetWeatherInfo getWeatherInfo;
    public MoreWeatherInfoActivityPresenter(IMoreWeatherInfoActivity activity){
        this.activity = activity;
        getWeatherInfo = new GetWeatherInfo();
    }

    public void getNextHourWeatherInfo(String myLongitude, String myLatitude){

        getWeatherInfo.getNextHourWeatherInfo(String.valueOf(myLongitude), String.valueOf(myLatitude), new GetNextHourWeatherInfoListener() {
            @Override
            public void getSuccess(NextHourWeatherInfo nextHourWeatherInfo) {
                activity.setNextHourWeatherInfoData(nextHourWeatherInfo);

            }

            @Override
            public void getFailed() {

            }
        });
    }

    public void getAirInfo(String cityName){
        getWeatherInfo.getAirInfo(cityName, new GetAirInfoListener() {
            @Override
            public void onSuccess(String aqi) {
                activity.updateAirInfo(Integer.valueOf(aqi));
            }

            @Override
            public void onFail() {

            }
        });
    }
}

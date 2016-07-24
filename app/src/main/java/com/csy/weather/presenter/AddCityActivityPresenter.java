package com.csy.weather.presenter;

import android.content.Context;

import com.csy.weather.domin.WeatherInfo;
import com.csy.weather.model.net.GetWeatherInfo;
import com.csy.weather.model.net.GetWeatherInfoListener;
import com.csy.weather.view.activity.AddCityActivity;
import com.csy.weather.view.iview.IAddCityActivity;

/**
 * Created by csy on 2016/7/23.
 */
public class AddCityActivityPresenter {

    private final Context context;
    private GetWeatherInfo getWeatherInfo;
    private IAddCityActivity activity;
    public AddCityActivityPresenter(Context context){
        this.activity = (AddCityActivity)context;
        this.context = context;
        getWeatherInfo = new GetWeatherInfo();
    }

    public void getWeatherInfo(String cityName){
        getWeatherInfo.getTodayWeatherInfo(cityName, context, new GetWeatherInfoListener(){
            @Override
            public void getSuccess(WeatherInfo weatherInfo) {
                activity.jump();
            }

            @Override
            public void getFailed() {

            }
        });
    }
}

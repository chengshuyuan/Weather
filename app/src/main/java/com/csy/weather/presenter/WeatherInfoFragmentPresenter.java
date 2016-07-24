package com.csy.weather.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.csy.weather.domin.Constant;
import com.csy.weather.domin.WeatherInfo;
import com.csy.weather.model.db.DBOperation;
import com.csy.weather.model.db.IDBOperation;
import com.csy.weather.model.net.GetWeatherInfo;
import com.csy.weather.model.net.GetWeatherInfoListener;
import com.csy.weather.model.net.IGetWeatherInfo;
import com.csy.weather.util.AppLog;
import com.csy.weather.view.iview.IWeatherInfoFragment;

/**
 * Created by csy on 2016/7/2.
 */
public class WeatherInfoFragmentPresenter {

    private IWeatherInfoFragment fragment;
    private IGetWeatherInfo getWeatherInfo;
    private Context context;
    private Handler handler = new Handler(Looper.myLooper());
    private IDBOperation idbOperation;

    public WeatherInfoFragmentPresenter(IWeatherInfoFragment fragment, Context context){
        this.fragment = fragment;
        this.context = context;
        getWeatherInfo = new GetWeatherInfo();
        idbOperation = new DBOperation(context);
    }

    public void getWeatherInfo(String cityName, Boolean fromNet){
        if(fromNet) {
            getWeatherInfo.getTodayWeatherInfo(cityName, context, new GetWeatherInfoListener() {
                @Override
                public void getSuccess(final WeatherInfo weatherInfo) {
                    fragment.updateView(weatherInfo);
                }

                @Override
                public void getFailed() {
                    fragment.getFail();
                }
            });
        }else {
            if (Constant.weatherInfoHashMap.containsKey(cityName)) {
                fragment.updateView(Constant.weatherInfoHashMap.get(cityName));
            } else {
                AppLog.e("checkCity", "false");
                getWeatherInfo(cityName, true);
            }
        }
    }
}


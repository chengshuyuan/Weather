package com.csy.weather.util;

import android.content.Context;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by csy on 2016/7/23.
 */
public class LocationUtil  {
    public LocationClient locationClient;

    public LocationUtil(Context context){
        locationClient = new LocationClient(context);
        initLocation(locationClient);
        locationClient.registerLocationListener((BDLocationListener)context);

    }

    public void start(){
        locationClient.start();
        locationClient.requestLocation();
    }

    public void stop(){
        locationClient.stop();
    }

    private void initLocation(LocationClient locationClient){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(false);
        option.setIgnoreKillProcess(true);
        locationClient.setLocOption(option);
    }
}

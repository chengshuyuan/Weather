package com.csy.weather.model.net;

/**
 * Created by csy on 2016/7/24.
 */
public interface GetAirInfoListener {

    public void onSuccess(String aqi);

    public void onFail();
}

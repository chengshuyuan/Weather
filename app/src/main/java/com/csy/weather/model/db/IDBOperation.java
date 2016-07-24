package com.csy.weather.model.db;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/2.
 */
public interface IDBOperation {
    void insertCity(String cityName, String weatherInfo);

    void deleteCity(String cityName);

    String getWeather(String cityName);

    void updateWeather(String cityName, String weatherInfo);

    boolean checkCity(String cityName);

    ArrayList<String> getAllCity();
}

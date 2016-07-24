package com.csy.weather.view.iview;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/2.
 */
public interface IMainActivity {

    public void addFragment(ArrayList<String> allCity);

    public void setBackground(String weatherType);

    public void jumpToMoreWeatherInfoActivity();
}

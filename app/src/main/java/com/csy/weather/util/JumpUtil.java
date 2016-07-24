package com.csy.weather.util;

import android.content.Intent;

import com.csy.weather.common.BaseActivity;
import com.csy.weather.view.activity.AddCityActivity;
import com.csy.weather.view.activity.CityAdminActivity;
import com.csy.weather.view.activity.MainActivity;
import com.csy.weather.view.activity.MoreWeatherInfoActivity;

/**
 * Created by csy on 2016/7/17.
 */
public class JumpUtil {
    public static void jumpToCityAdmin(BaseActivity activity){
        Intent intent = new Intent(activity, CityAdminActivity.class);
        activity.startActivity(intent);
    }

    public static void jumpToMainActivity(BaseActivity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void jumpToAddCityActivity(BaseActivity activity){
        Intent intent = new Intent(activity, AddCityActivity.class);
        activity.startActivity(intent);
    }

    public static void jumpToMoreWeatherInfoActivity(BaseActivity activity){
        Intent intent = new Intent(activity, MoreWeatherInfoActivity.class);
        activity.startActivity(intent);
    }
}

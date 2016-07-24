package com.csy.weather.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.csy.weather.R;
import com.csy.weather.common.BaseActivity;
import com.csy.weather.presenter.SplashActivityPresenter;
import com.csy.weather.util.AppLog;
import com.csy.weather.util.JumpUtil;
import com.csy.weather.util.LocationUtil;
import com.csy.weather.view.iview.ISplashActivity;

public class SplashActivity extends BaseActivity implements ISplashActivity, BDLocationListener {

    private SplashActivityPresenter presenter;
    private SharedPreferences sharedPreferences;
    private String mainCity;
    private LocationUtil locationUtil;

    public double myLatitude, myLongitude;
    public String  myDistrict, myCity, myAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashActivityPresenter(this);
        presenter.getAllCityWeather();
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        locationUtil = new LocationUtil(this);
    }

    @Override
    public void jump(){
        JumpUtil.jumpToMainActivity(SplashActivity.this);
        finish();
    }


    @Override
    public void onReceiveLocation(BDLocation location) {
        // TODO Auto-generated method stub
        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();
        myAddress = location.getAddrStr();
        myCity = location.getCity();
        myDistrict = location.getDistrict();
        if(!(myDistrict == null || myDistrict.length() <= 0)){
            locationUtil.stop();
            Toast.makeText(this, myAddress, Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            mainCity = myCity.substring(0, myCity.length() - 1);
            editor.putString("mainCity", mainCity);
            AppLog.e("mainCity0", mainCity);
            editor.commit();

            presenter.getMainCityWeather(mainCity);
        } else{
            Toast.makeText(this, "定位失败， 请检查网络后再试", Toast.LENGTH_LONG).show();
        }
    }
}

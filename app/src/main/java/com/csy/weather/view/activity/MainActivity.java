package com.csy.weather.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csy.weather.R;
import com.csy.weather.adapter.CityWeatherViewPagerAdater;
import com.csy.weather.common.BaseActivity;
import com.csy.weather.domin.Constant;
import com.csy.weather.domin.WeatherInfo;
import com.csy.weather.presenter.MainActivityPresenter;
import com.csy.weather.util.AppLog;
import com.csy.weather.util.ImageResource;
import com.csy.weather.util.JumpUtil;
import com.csy.weather.view.fragment.WeatherInfoFragment;
import com.csy.weather.view.iview.IMainActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements IMainActivity {

    private LinearLayout mainLayout;
    private ViewPager viewPager;
    private LinearLayout indicator;
    private ImageButton settingImageButton, cityAdminImageButton;
    private TextView cityNameTextView;

    private ArrayList<String> citiesList;
    private ArrayList<Fragment> fragmentsList;
    private CityWeatherViewPagerAdater viewPagerAdater;
    private MainActivityPresenter presenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentsList = new ArrayList<Fragment>();
        presenter = new MainActivityPresenter(this);
        sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;
        initView();
        initAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAllCity();
    }

    public void initView() {
        mainLayout = (LinearLayout)findView(R.id.layout_main);
        settingImageButton = (ImageButton)findView(R.id.imagebtn_settting);
        cityAdminImageButton = (ImageButton)findView(R.id.imagebtn_cityAdmin);
        cityNameTextView = (TextView)findView(R.id.textview_cityame);

        viewPager = findView(R.id.viewpager_city);
        indicator = findView(R.id.ll_indicator);
        viewPagerAdater = new CityWeatherViewPagerAdater(getSupportFragmentManager(), fragmentsList);
        viewPager.setAdapter(viewPagerAdater);
    }

    public void initAction() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i < fragmentsList.size(); i++){
                    indicator.getChildAt(i).setEnabled(false);
                }
                indicator.getChildAt(position).setEnabled(true);
                cityNameTextView.setText(citiesList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        settingImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpToSettingActivity(MainActivity.this);
            }
        });

        cityAdminImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpToCityAdmin(MainActivity.this);
            }
        });
    }

    @Override
    public void addFragment (ArrayList<String> allCity){
        citiesList = allCity;
        fragmentsList.clear();
        indicator.removeAllViews();
        View dot = null;
        if(allCity.size() > 0){
            indicator.setVisibility(View.VISIBLE);
            cityNameTextView.setText(citiesList.get(0));
            for(int i = 0; i < allCity.size(); i++) {
                WeatherInfoFragment fragment = new WeatherInfoFragment(allCity.get(i));
                fragment.setCity(allCity.get(i));
                fragmentsList.add(fragment);
                dot = new View(this);
                dot.setBackgroundResource(R.drawable.dot_bg_selector);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
                params.leftMargin = 10;
                dot.setEnabled(false);
                dot.setLayoutParams(params);
                indicator.addView(dot);
            }
            indicator.getChildAt(0).setEnabled(true);
        }
        viewPagerAdater.notifyDataSetChanged();
    }

    @Override
    public void setBackground(String weatherType) {
        AppLog.e("setBackground", weatherType);
        String background = sharedPreferences.getString("background", "天气");
        if(background.equals("天气")){
            mainLayout.setBackgroundResource(ImageResource.getBackgroundResource(weatherType));
        }else if(background.equals("默认")){
            mainLayout.setBackgroundResource(R.drawable.bg_normal);
        }else if(background.equals("米色")){
            mainLayout.setBackgroundColor(Color.parseColor("#CDC9A5"));
        }
    }

    @Override
    public void jumpToMoreWeatherInfoActivity(String aqi) {
        JumpUtil.jumpToMoreWeatherInfoActivity(this, aqi);
    }
}

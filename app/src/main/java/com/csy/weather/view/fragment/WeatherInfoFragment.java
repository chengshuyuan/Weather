package com.csy.weather.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.csy.weather.R;
import com.csy.weather.adapter.ForecastGridAdapter;
import com.csy.weather.adapter.ZhishuGridAdapter;
import com.csy.weather.common.BaseFragment;
import com.csy.weather.domin.Constant;
import com.csy.weather.domin.ForecastWeatherInfo;
import com.csy.weather.domin.TodayWeatherInfo;
import com.csy.weather.domin.WeatherInfo;
import com.csy.weather.domin.ZhishuInfo;
import com.csy.weather.presenter.WeatherInfoFragmentPresenter;
import com.csy.weather.util.AppLog;
import com.csy.weather.util.ImageResource;
import com.csy.weather.view.iview.IMainActivity;
import com.csy.weather.view.iview.IWeatherInfoFragment;
import com.csy.weather.widget.MyGridView;
import com.csy.weather.widget.ZhishuInfoDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

public class WeatherInfoFragment  extends BaseFragment implements IWeatherInfoFragment {


    private PullToRefreshScrollView pullRefreshScrollView;

    private Button moreButton;
    private TextView temperatureText, weatherText;
    private ImageView weatherImage;
    private TextView updateTimeText, dateText, humidityText, windText, temperatureRangeText, suntimeText;

    private GridView forecastGridView;
    private MyGridView zhishuGridView;
    private TextView umbrellaValueText, umbrellaDetailText;

    private View view;

    private TodayWeatherInfo todayWeatherInfo;
    private ForecastWeatherInfo forecastWeatherInfo;
    private ZhishuInfo zhishuInfo;
    private ArrayList<ForecastWeatherInfo> forecasetInfos;
    private ArrayList<ZhishuInfo> zhishuInfos;
    private WeatherInfoFragmentPresenter presenter;

    private String cityName;
    private IMainActivity activity;
    private boolean isCreated = false;

    public WeatherInfoFragment(String cityName){
        this.cityName = cityName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather_info, null);
        presenter = new WeatherInfoFragmentPresenter(this, getActivity());
        initView();
        initAction();
        presenter.getWeatherInfo(cityName, false);
        isCreated = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        if(!isVisible || !isCreated){
            return;
        }
        activity.setBackground(Constant.weatherInfoHashMap.get(cityName).getForecastWeatherInfos().get(0).getWeatherType());
    }

    @Override
    public void onResume() {
        super.onResume();
        AppLog.e("onResume", "onResume");

    }

    private void initView(){
        pullRefreshScrollView = (PullToRefreshScrollView)findView(R.id.scrollview_fragment_weather);
        pullRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        moreButton = (Button)findView(R.id.button_more);
        temperatureText = (TextView)findView(R.id.textview_temperature);
        weatherText = (TextView)findView(R.id.textview_weather);
        weatherImage = (ImageView)findView(R.id.imageview_weather);

        updateTimeText = (TextView)findView(R.id.textview_updatetime);
        dateText = (TextView)findView(R.id.textview_date);
        humidityText = (TextView)findView(R.id.textview_humidity);
        windText = (TextView)findView(R.id.textview_wind);
        temperatureRangeText = (TextView)findView(R.id.textview_temperatureRange);
        suntimeText = (TextView)findView(R.id.textview_suntime);
        forecastGridView = (GridView)findView(R.id.gridview_forecast);
        umbrellaValueText = (TextView)findView(R.id.textview_umbrella_value);
        umbrellaDetailText = (TextView)findView(R.id.textview_umbrell_detail);
        zhishuGridView = (MyGridView)findView(R.id.gridview_zhishu);
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams((int) (Constant.displayWidth * 2.0f + 0.5f), LinearLayout.LayoutParams.WRAP_CONTENT);
        forecastGridView.setLayoutParams(param1);
        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams((int) (Constant.displayWidth * 1.0f + 0.5f), LinearLayout.LayoutParams.WRAP_CONTENT);
        zhishuGridView.setLayoutParams(param2);
    }

    private void initAction(){
        pullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                presenter.getWeatherInfo(cityName, true);
            }
        });

        zhishuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        zhishuInfo = zhishuInfos.get(1);
                        showZhishuInfoDialog(zhishuInfo, 0);
                        break;
                    case 1:
                        zhishuInfo = zhishuInfos.get(2);
                        showZhishuInfoDialog(zhishuInfo, 1);
                        break;
                    case 2:
                        zhishuInfo = zhishuInfos.get(3);
                        showZhishuInfoDialog(zhishuInfo, 2);
                        break;
                    case 3:
                        zhishuInfo = zhishuInfos.get(4);
                        showZhishuInfoDialog(zhishuInfo, 3);
                        break;
                    case 4:
                        zhishuInfo = zhishuInfos.get(5);
                        showZhishuInfoDialog(zhishuInfo, 4);
                        break;
                    case 5:
                        zhishuInfo = zhishuInfos.get(6);
                        showZhishuInfoDialog(zhishuInfo, 5);
                        break;
                    case 6:
                        zhishuInfo = zhishuInfos.get(7);
                        showZhishuInfoDialog(zhishuInfo, 6);
                        break;
                    case 7:
                        zhishuInfo = zhishuInfos.get(8);
                        showZhishuInfoDialog(zhishuInfo, 7);
                        break;
                    case 8:
                        zhishuInfo = zhishuInfos.get(9);
                        showZhishuInfoDialog(zhishuInfo, 8);
                        break;

                }
            }
        });

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.jumpToMoreWeatherInfoActivity();
            }
        });
    }

    public <T extends View> T findView(int id){
        return (T)view.findViewById(id);
    }

    public void setCity(String cityName){
        this.cityName = cityName;
    }

    @Override
    public void updateView(WeatherInfo weatherInfo){

        pullRefreshScrollView.onRefreshComplete();
        todayWeatherInfo = weatherInfo.getTodayWeatherInfo();
        forecasetInfos = weatherInfo.getForecastWeatherInfos();
        zhishuInfos = weatherInfo.getZhishuInfos();
        forecastWeatherInfo = forecasetInfos.get(0);
        zhishuInfo = zhishuInfos.get(10);
        temperatureText.setText(todayWeatherInfo.getTemperature());
        weatherImage.setBackgroundResource(ImageResource.getWeatherBitMapResource(forecastWeatherInfo.getWeatherType()));
        weatherText.setText(forecastWeatherInfo.getWeatherType());
        updateTimeText.setText("" + todayWeatherInfo.getUpdateTime());
        dateText.setText( forecastWeatherInfo.getDate());
        humidityText.setText( todayWeatherInfo.getHumidity());
        windText.setText("风：" + todayWeatherInfo.getWindDirection() + "," + todayWeatherInfo.getWindVelocity());
        temperatureRangeText.setText(forecastWeatherInfo.getLowTem() + "~" + forecastWeatherInfo.getHighTem());
        suntimeText.setText(todayWeatherInfo.getSunRise() + "~" + todayWeatherInfo.getSunSet());
        forecastGridView.setAdapter(new ForecastGridAdapter(getActivity(), forecasetInfos));
        umbrellaValueText.setText(zhishuInfo.getValue());
        umbrellaDetailText.setText(zhishuInfo.getDescription());
        zhishuGridView.setAdapter(new ZhishuGridAdapter(getActivity(), zhishuInfos));
    }

    @Override
    public void getFail() {
        Toast.makeText(getActivity(), "刷新失败", Toast.LENGTH_LONG).show();
        pullRefreshScrollView.onRefreshComplete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IMainActivity){
            activity = (IMainActivity)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showZhishuInfoDialog(ZhishuInfo zhishuInfo, int position){
        ZhishuInfoDialog zhishuInfoDialog = new ZhishuInfoDialog(getActivity(), zhishuInfo, position);
        zhishuInfoDialog.show();
    }
}

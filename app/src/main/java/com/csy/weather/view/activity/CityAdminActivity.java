package com.csy.weather.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.csy.weather.R;
import com.csy.weather.adapter.CityAdminGridViewAdapter;
import com.csy.weather.common.BaseActivity;
import com.csy.weather.presenter.CityAdminActivityPresenter;
import com.csy.weather.util.JumpUtil;
import com.csy.weather.view.iview.ICityAdminActivity;

import java.util.ArrayList;

public class CityAdminActivity extends BaseActivity implements ICityAdminActivity {

    private GridView cityAdminGridView;
    private ImageButton ibtnAddCity;
    private ArrayList<String> cityList;
    private CityAdminActivityPresenter presenter;
    private CityAdminGridViewAdapter adapter;
    private SharedPreferences sharedPreferences;
    private ArrayList<Boolean> deleteList  = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_admin);
        presenter = new CityAdminActivityPresenter(this);
        cityList = presenter.getAllCity();
        getDeleteStatus();
        adapter = new CityAdminGridViewAdapter(this, cityList, deleteList);
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        initView();
        initAction();
    }

    private void initView() {
        cityAdminGridView = findView(R.id.gridview_cityAdmin);
        ibtnAddCity = findView(R.id.ibtnAddCity);
        cityAdminGridView.setAdapter(adapter);
    }

    private void initAction() {
        cityAdminGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteList.set(position, true);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        ibtnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jumpToAddCityActivity(CityAdminActivity.this);
            }
        });

    }

    private void getDeleteStatus(){
        for(int i = 0; i < cityList.size(); i++){
            deleteList.add(false);
        }
    }
}

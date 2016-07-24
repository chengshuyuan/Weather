package com.csy.weather.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.csy.weather.R;
import com.csy.weather.adapter.HotCityGridViewAdapter;
import com.csy.weather.common.BaseActivity;
import com.csy.weather.domin.Constant;
import com.csy.weather.model.db.ImportDB;
import com.csy.weather.presenter.AddCityActivityPresenter;
import com.csy.weather.util.JumpUtil;
import com.csy.weather.view.iview.IAddCityActivity;

public class AddCityActivity extends BaseActivity implements IAddCityActivity {

    private EditText etCityName;
    private ImageButton ibtnSearch;
    private GridView gvHotCity;

    private String cityName, cityCode;
    private ImportDB importDB;
    private AddCityActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        importDB = new ImportDB(this);
        presenter = new AddCityActivityPresenter(this);
        initView();
        initAction();
    }

    private void initView(){
        etCityName = findView(R.id.etCityName);
        ibtnSearch = findView(R.id.ibtnSearch);
        gvHotCity = findView(R.id.gvHotCity);
    }

    private void initAction(){
        ibtnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cityName = etCityName.getText().toString().trim();
                if(cityName == null || cityName.length() <= 0){
                    Toast.makeText(AddCityActivity.this, "请输入要添加的城市名", Toast.LENGTH_LONG).show();
                }else{
                    cityCode = importDB.getCityCode(cityName);
                    if(cityCode == null || cityCode.length() <= 0){
                        Toast.makeText(AddCityActivity.this, "该城市不存在， 请重新输入", Toast.LENGTH_LONG).show();
                    }else{
                        presenter.getWeatherInfo(cityName);
                    }
                }
            }
        });

        HotCityGridViewAdapter adapter = new HotCityGridViewAdapter(this);
        gvHotCity.setAdapter(adapter);
        gvHotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                cityName = Constant.hotCitys[position];
                cityCode = importDB.getCityCode(cityName);
                presenter.getWeatherInfo(cityName);
            }
        });
    }

    @Override
    public void jump() {
        Toast.makeText(AddCityActivity.this, "添加成功", Toast.LENGTH_LONG).show();
        JumpUtil.jumpToMainActivity(AddCityActivity.this);
    }
}

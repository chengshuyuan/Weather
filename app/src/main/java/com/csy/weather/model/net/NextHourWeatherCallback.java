package com.csy.weather.model.net;

import com.csy.weather.domin.NextHourWeatherInfo;
import com.csy.weather.util.AppLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/2.
 */
public class NextHourWeatherCallback extends  HttpCallBack{

    private GetNextHourWeatherInfoListener listener;
    public NextHourWeatherCallback(GetNextHourWeatherInfoListener listener){
        this.listener = listener;
    }

    @Override
    public void operate(String result)  {
        AppLog.e("result",result);
        NextHourWeatherInfo nextHourWeatherInfo = new NextHourWeatherInfo();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            String description = jsonObject.getString("summary");
            JSONArray jsonArray = jsonObject.getJSONArray("dataseries");
            ArrayList<Double> nextWeaherList = new ArrayList<Double>();
            for(int i = 0; i < jsonArray.length(); i++){
                nextWeaherList.add(Double.valueOf(jsonArray.getInt(i)));
            }
            nextHourWeatherInfo.setDataList(nextWeaherList);
            nextHourWeatherInfo.setDescription(description);
            listener.getSuccess(nextHourWeatherInfo);
        } catch (JSONException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Override
    public void fail() {

    }
}

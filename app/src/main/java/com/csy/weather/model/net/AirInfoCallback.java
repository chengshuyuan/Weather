package com.csy.weather.model.net;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by csy on 2016/7/2.
 */
public class AirInfoCallback extends HttpCallBack {

    private GetAirInfoListener listener;

    public AirInfoCallback(GetAirInfoListener listener){
        this.listener = listener;
    }

    @Override
    public void operate(String result) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result).getJSONObject("retData");
            String aqi = jsonObject.getString("aqi");
            listener.onSuccess(aqi);
        } catch (JSONException e) {
            listener.onFail();
            e.printStackTrace();
        }

    }

    @Override
    public void fail() {
        listener.onFail();
    }
}

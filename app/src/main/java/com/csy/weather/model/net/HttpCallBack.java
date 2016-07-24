package com.csy.weather.model.net;

import android.os.Handler;
import android.os.Looper;

import com.csy.weather.util.AppLog;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by csy on 2016/7/2.
 */
public abstract class HttpCallBack implements Callback {

    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public void onFailure(Call call, IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                fail();
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        final String result = response.body().string();
        AppLog.e("result", result);
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    operate(result);
                } catch (JSONException e) {
                    fail();
                    e.printStackTrace();
                }
            }
        });
    }

    public abstract void operate(String result) throws JSONException;

    public abstract void fail();

}

package com.csy.weather.model.net;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by csy on 2016/7/2.
 */
public class HttpUtil {
    public static void sendRequest(String url, HttpCallBack callBack){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        if(request == null)
            return;

        final Call call = okHttpClient.newCall(request);
        call.enqueue(callBack);
    }
}

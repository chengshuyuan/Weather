package com.csy.weather.common;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.ref.WeakReference;

public abstract class BaseActivity extends AppCompatActivity {

    public BaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static class BaseHandler extends Handler{
        private final WeakReference<BaseActivity> mActivity;
        public BaseHandler(BaseActivity baseActivity){
            mActivity = new WeakReference<BaseActivity>(baseActivity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseActivity activity = mActivity.get();
            activity.handler(msg);
        }
    }

    public void handler(Message msg){

    }

    public <T extends View> T findView(int id){
        return (T)this.findViewById(id);
   }
}
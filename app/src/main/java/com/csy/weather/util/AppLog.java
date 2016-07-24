package com.csy.weather.util;

import android.util.Log;

import com.csy.weather.common.BuildConfig;

/**
 * Created by csy on 2016/7/2.
 */
public class AppLog {

    public static void e(String tag, String msg){
        if(BuildConfig.isDebug){
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable throwable){
        if(BuildConfig.isDebug){
            Log.e(tag, msg, throwable);
        }
    }

    public static void v(String tag, String msg){
        if(BuildConfig.isDebug){
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable throwable){
        if(BuildConfig.isDebug){
            Log.v(tag, msg, throwable);
        }
    }

    public static void w(String tag, String msg){
        if(BuildConfig.isDebug){
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable throwable){
        if(BuildConfig.isDebug){
            Log.w(tag, msg, throwable);
        }
    }

    public static void i(String tag, String msg){
        if(BuildConfig.isDebug){
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable throwable){
        if(BuildConfig.isDebug){
            Log.i(tag, msg, throwable);
        }
    }

    public static void d(String tag, String msg){
        if(BuildConfig.isDebug){
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable throwable){
        if(BuildConfig.isDebug){
            Log.d(tag, msg, throwable);
        }
    }
}

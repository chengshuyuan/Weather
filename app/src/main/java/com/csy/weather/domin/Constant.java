package com.csy.weather.domin;

import com.csy.weather.R;

import java.util.HashMap;

/**
 * Created by csy on 2016/7/2.
 */
public class Constant {
    public static final int GET_LOCATION_SUCCESS = 12;
    public static final int DOWNLOAD_APK_SUCCESS = 13;
    public static final int DOWNLOAD_APK_ERROR = 14;
    public static final int GET_AIRINFO_SUCCESS = 15;
    public static final int GET_AIRINFO_ERROR = 16;
    public static final int GET_NEXTHOUR_WEATHERINFO_SUCCESS = 17;
    public static final int GET_NEXTHOUR_WEATHERINFO_ERROR = 18;
    public static int displayWidth;  //屏幕宽度
    public static int displayHeight; //屏幕高度

    public static HashMap<String, WeatherInfo> weatherInfoHashMap = new HashMap<String, WeatherInfo>();

    public static final int zhishuIcons[] = {R.drawable.zhishu_comfort, R.drawable.zhishu_coat, R.drawable.zhishu_ill,
            R.drawable.zhishu_drying, R.drawable.zhishu_travel, R.drawable.zhishu_uv,
            R.drawable.zhishu_carwash, R.drawable.zhishu_sports, R.drawable.zhishu_appointment};
    public static final String hotCitys[] ={"北京", "上海", "广州", "深圳",
            "武汉", "南京", "西安", "成都",
            "杭州", "合肥", "重庆", "沈阳",
            "长沙", "苏州", "大连", "厦门",
            "三亚", "天津", "宁波", "无锡",
            "香港", "澳门", "台北", "珠海",
            "绵阳", "郫县", "潜山", "苍溪"};
    public static final String settingItem[] = {"城市管理", "天气自动更新", "背景图片"};
    public static final String appInfoItem[] = {"意见反馈", "检测更新", "关于我们"};
    public static final String autoUpdateItem[] = {"天气自动更新", "更新开始时间", "更新结束时间", "更新间隔时间"};
}

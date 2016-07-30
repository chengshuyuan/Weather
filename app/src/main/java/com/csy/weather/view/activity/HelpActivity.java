package com.csy.weather.view.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.csy.weather.R;
import com.csy.weather.adapter.ExpandableListViewAdapter;
import com.csy.weather.common.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpActivity extends BaseActivity {


    private ExpandableListView listviewHelp;
    private ExpandableListViewAdapter adapter;
    private ArrayList<String> parentList = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> childMap = new HashMap<String , ArrayList<String>>();
    private ArrayList<String> chilidList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initView();
        initData();
    }

    private void initView(){
        adapter = new ExpandableListViewAdapter(this, parentList, childMap);
        listviewHelp = findView(R.id.listviewHelp);
        listviewHelp.setAdapter(adapter);
    }

    private void initData(){
        parentList.add("元元天气提供哪些天气信息?");
        chilidList = new ArrayList<String>();
        chilidList.add("元元天气提供了天气、温度、适度、空气质量、一周天气预测的同时，还提供了未来一小时的天气情况预测");
        childMap.put("元元天气提供哪些天气信息?", chilidList);
        parentList.add("如何管理城市信息?");
        chilidList = new ArrayList<String>();
        chilidList.add("点击右上角城市列表按钮，进入城市管理页面，可以进行添加、删除、修改主要城市等操作");
        childMap.put("如何管理城市信息?", chilidList);
        parentList.add("如何设置主要城市?");
        chilidList = new ArrayList<String>();
        chilidList.add("城市管理页面红色表示主要城市，点击城市下边按钮即可设置为主要城市");
        childMap.put("如何设置主要城市?", chilidList);
        parentList.add("如何添加城市?");
        chilidList = new ArrayList<String>();
        chilidList.add("进入添加城市管理页面，用户可以输入城市名添加，也可以点击提供的国内主要城市进行添加");
        childMap.put("如何添加城市?", chilidList);
        parentList.add("如何删除城市?");
        chilidList = new ArrayList<String>();
        chilidList.add("城市管理页面，长按某个城市出现删除按钮，点击即可删除");
        childMap.put("如何删除城市?", chilidList);
        parentList.add("如何查看未来一小时天气情况?");
        chilidList = new ArrayList<String>();
        chilidList.add("点击空气质量显示按钮，即可进入更多天气情况的页面");
        childMap.put("如何查看未来一小时天气情况?", chilidList);
        adapter.notifyDataSetChanged();

    }

}

package com.csy.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.csy.weather.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by csy on 2016/7/30.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    //得到子item需要关联的数据
    private Context context;
    private ArrayList<String> parent;
    private HashMap<String, ArrayList<String>> map;
    public ExpandableListViewAdapter(Context context, ArrayList<String> parent, HashMap<String, ArrayList<String>>map){
        this.context = context;
        this.parent = parent;
        this.map = map;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parent.get(groupPosition);
        return (map.get(key).get(childPosition));
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parentView) {
        String key = parent.get(groupPosition);
        String info = map.get(key).get(childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
        tv.setText(info);
        return tv;
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        String key = parent.get(groupPosition);
        int size=map.get(key).size();
        return size;
    }
    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parentView) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_parent, null);
        }
        TextView tv = (TextView) convertView
                .findViewById(R.id.tvParent);
        tv.setText(parent.get(groupPosition));
        return tv;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

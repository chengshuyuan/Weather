package com.csy.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csy.weather.R;
import com.csy.weather.domin.Constant;
import com.csy.weather.domin.ZhishuInfo;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/2.
 */
public class ZhishuGridAdapter extends BaseAdapter{

    private ArrayList<ZhishuInfo> zhishuInfos;
    private LayoutInflater inflater;
    private int icons[] = Constant.zhishuIcons;

    public ZhishuGridAdapter(Context context, ArrayList<ZhishuInfo> zhishuInfos) {
        this.zhishuInfos = zhishuInfos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return zhishuInfos.get(position);
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_gridview_zhishu, null);
            viewHolder = new ViewHolder();
            viewHolder.nameText = (TextView)convertView.findViewById(R.id.textview_name);
            viewHolder.valueText = (TextView)convertView.findViewById(R.id.textview_value);
            viewHolder.zhishuImage = (ImageView)convertView.findViewById(R.id.imageview_zhishu);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ZhishuInfo zhishuInfo = zhishuInfos.get(position + 1);
        viewHolder.nameText.setText(zhishuInfo.getName());
        viewHolder.zhishuImage.setImageResource(icons[position]);
        viewHolder.valueText.setText(zhishuInfo.getValue());
        return convertView;
    }

    private static class ViewHolder{
        TextView nameText;
        TextView valueText;
        ImageView zhishuImage;
    }
}

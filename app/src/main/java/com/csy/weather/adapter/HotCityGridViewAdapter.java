package com.csy.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csy.weather.R;
import com.csy.weather.domin.Constant;

/**
 * Created by csy on 2016/7/23.
 */
public class HotCityGridViewAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public HotCityGridViewAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return Constant.hotCitys.length;
    }

    @Override
    public Object getItem(int position) {
        return Constant.hotCitys[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_addcity, null);
            viewHolder.tvCityName = (TextView) convertView.findViewById(R.id.tvCityName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tvCityName.setText(Constant.hotCitys[position]);
        return convertView;
    }

    private static class ViewHolder{
        TextView tvCityName;
    }
}

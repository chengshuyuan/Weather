package com.csy.weather.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csy.weather.R;
import com.csy.weather.domin.Constant;
import com.csy.weather.model.db.DBOperation;
import com.csy.weather.model.db.IDBOperation;
import com.csy.weather.util.ImageResource;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/17.
 */
public class CityAdminGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<String>cityList;
    private SharedPreferences sharedPreferences;
    private String mainCity;
    private ArrayList<Boolean> deleteList;
    private IDBOperation dbOperation;
    private Context context;

    public CityAdminGridViewAdapter(Context constext, ArrayList<String> cityList, ArrayList<Boolean> deleteList){
        inflater = LayoutInflater.from(constext);
        this.context = constext;
        this.cityList = cityList;
        this.deleteList = deleteList;
        sharedPreferences = constext.getSharedPreferences("config", Context.MODE_PRIVATE);

    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return Constant.weatherInfoHashMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mainCity = sharedPreferences.getString("mainCity", "");
        if(TextUtils.isEmpty(mainCity)){
            if(cityList.size() > 0) {
                mainCity = cityList.get(0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("mainCity", mainCity);
                editor.commit();
            }
        }
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_cityadmin, null);
            viewHolder.cityAdminLayout = (RelativeLayout)convertView.findViewById(R.id.layout_cityAdmin);
            viewHolder.cityNameTextView = (TextView)convertView.findViewById(R.id.textview_item_cityName);
            viewHolder.weaterTextView = (TextView)convertView.findViewById(R.id.textview_item_weather);
            viewHolder.weatherImageView = (ImageView)convertView.findViewById(R.id.imageview_item_weather);
            viewHolder.mainCityLayout = (LinearLayout)convertView.findViewById(R.id.layout_item_mainCity);
            viewHolder.deleteImageView = (ImageView)convertView.findViewById(R.id.imageview_item_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final String cityName = cityList.get(position);
        viewHolder.cityAdminLayout.setBackgroundResource(ImageResource.getBackgroundResource(Constant.weatherInfoHashMap.get(cityName).getForecastWeatherInfos().get(0).getWeatherType()));
        viewHolder.cityNameTextView.setText(cityList.get(position));
        viewHolder.weaterTextView.setText(Constant.weatherInfoHashMap.get(cityName).getForecastWeatherInfos().get(0).getWeatherType());
        viewHolder.weatherImageView.setBackgroundResource(ImageResource.getWeatherBitMapResource(Constant.weatherInfoHashMap.get(cityName).getForecastWeatherInfos().get(0).getWeatherType()));
        if(cityList.get(position).equals(mainCity)){
            viewHolder.mainCityLayout.setBackgroundColor(Color.parseColor("#f8513f"));
        }else{
            viewHolder.mainCityLayout.setBackgroundColor(Color.parseColor("#7fffffff"));
        }

        if(deleteList.get(position)){
            viewHolder.deleteImageView.setVisibility(View.VISIBLE);
        }else{
            viewHolder.deleteImageView.setVisibility(View.GONE);
        }

        viewHolder.mainCityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("mainCity", cityList.get(position));
                editor.commit();
                notifyDataSetChanged();
            }
        });

        viewHolder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityList.get(position);
                if(cityName.equals(mainCity)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("mainCity", "");
                    editor.commit();
                }
                dbOperation = new DBOperation(context);
                dbOperation.deleteCity(cityName);
                Constant.weatherInfoHashMap.remove(cityName);
                cityList.remove(position);
                deleteList.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private static class  ViewHolder{
        RelativeLayout cityAdminLayout;
        ImageView weatherImageView;
        TextView cityNameTextView;
        TextView weaterTextView;
        LinearLayout mainCityLayout;
        ImageView deleteImageView;
    }
}

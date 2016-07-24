package com.csy.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csy.weather.R;
import com.csy.weather.domin.ForecastWeatherInfo;
import com.csy.weather.util.ImageResource;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/2.
 */
public class ForecastGridAdapter extends BaseAdapter{

    private ArrayList<ForecastWeatherInfo> forecastWeatherInfos;
    private LayoutInflater inflater;

    public ForecastGridAdapter(Context context, ArrayList<ForecastWeatherInfo> forecastWeatherInfos) {
        this.forecastWeatherInfos = forecastWeatherInfos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return forecastWeatherInfos.get(position);
    }

    @Override
    public int getCount() {
        return forecastWeatherInfos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_gridview_forecast, null);
            viewHolder = new ViewHolder();
            viewHolder.dateText = (TextView)convertView.findViewById(R.id.textview_date_forecast);
            viewHolder.weatherIconImage = (ImageView)convertView.findViewById(R.id.imageview_forecast);
            viewHolder.lowTemText = (TextView)convertView.findViewById(R.id.textview_lowtemp_forecast);
            viewHolder.highTemText = (TextView)convertView.findViewById(R.id.textview_hightemp_forecast);
            viewHolder.weatherTypeText = (TextView)convertView.findViewById(R.id.textview_weathertype_forecast);
            viewHolder.windText = (TextView)convertView.findViewById(R.id.textview_wind_forecast);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ForecastWeatherInfo forecastWeatherInfo = forecastWeatherInfos.get(position + 1);
        viewHolder.dateText.setText(forecastWeatherInfo.getDate());
        viewHolder.weatherIconImage.setBackgroundResource(ImageResource.getWeatherBitMapResource(forecastWeatherInfo.getWeatherType()));
        viewHolder.lowTemText.setText(forecastWeatherInfo.getLowTem());
        viewHolder.highTemText.setText(forecastWeatherInfo.getHighTem());
        viewHolder.weatherTypeText.setText(forecastWeatherInfo.getWeatherType());
        viewHolder.windText.setText(forecastWeatherInfo.getWindDirection() + "," + forecastWeatherInfo.getWindVelocity());

        return convertView;
    }

    private static class ViewHolder{
        TextView dateText;
        ImageView weatherIconImage;
        TextView lowTemText;
        TextView highTemText;
        TextView weatherTypeText;
        TextView windText;
    }
}

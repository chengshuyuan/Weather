package com.csy.weather.util;


import com.csy.weather.domin.ForecastWeatherInfo;
import com.csy.weather.domin.TodayWeatherInfo;
import com.csy.weather.domin.WeatherInfo;
import com.csy.weather.domin.ZhishuInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class WeatherInfoParser {
	private TodayWeatherInfo todayWeatherInfo;
	private ForecastWeatherInfo forecastWeatherInfo;
	private ZhishuInfo zhishuInfo;
	ArrayList<ForecastWeatherInfo> forecastList;
	ArrayList<ZhishuInfo> zhishuList;
	public WeatherInfoParser(String data){
		todayWeatherInfo = new TodayWeatherInfo();
		forecastList = new ArrayList<ForecastWeatherInfo>();
		zhishuList = new ArrayList<ZhishuInfo>();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(data));
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				if(eventType == XmlPullParser.START_TAG){
					if("updatetime".equals(parser.getName())){
						todayWeatherInfo.setUpdateTime(parser.nextText());
					}else if("wendu".equals(parser.getName())){
						todayWeatherInfo.setTemperature(parser.nextText());
					}else if("fengli".equals(parser.getName())){
						todayWeatherInfo.setWindVelocity(parser.nextText());
					}else if("fengxiang".equals(parser.getName())){
						todayWeatherInfo.setWindDirection(parser.nextText());
					}else if("shidu".equals(parser.getName())){
						todayWeatherInfo.setHumidity(parser.nextText());
					}else if("sunrise_1".equals(parser.getName())){
						todayWeatherInfo.setSunRise(parser.nextText());
					}else if("sunset_1".equals(parser.getName())){
						todayWeatherInfo.setSunSet(parser.nextText());
					}else if("weather".equals(parser.getName())){
						forecastWeatherInfo = new ForecastWeatherInfo();
						eventType = parser.next();
						while(!("weather".equals(parser.getName()))){
							if("date".equals(parser.getName())){
								forecastWeatherInfo.setDate(parser.nextText());
							}else if("high".equals(parser.getName())){
								forecastWeatherInfo.setHighTem(parser.nextText());
							}else if("low".equals(parser.getName())){
								forecastWeatherInfo.setLowTem(parser.nextText());
							}else if("type".equals(parser.getName())){
								forecastWeatherInfo.setWeatherType(parser.nextText());
							}else if("fengxiang".equals(parser.getName())){
								forecastWeatherInfo.setWindDirection(parser.nextText());
							}else if("fengli".equals(parser.getName())){
								forecastWeatherInfo.setWindVelocity(parser.nextText());
							}
							eventType = parser.next();
						}
						forecastList.add(forecastWeatherInfo);
					}else if("zhishu".equals(parser.getName())){
						zhishuInfo = new ZhishuInfo();
					}else if("name".equals(parser.getName())){
						zhishuInfo.setName(parser.nextText());
					}else if("value".equals(parser.getName())){
						zhishuInfo.setValue(parser.nextText());
					}else if("detail".equals(parser.getName())){
						zhishuInfo.setDescription(parser.nextText());
					}else{
					}
				}else if(eventType == XmlPullParser.END_TAG){
					if("zhishu".equals(parser.getName())){
						zhishuList.add(zhishuInfo);
					}
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WeatherInfo getWeatherInfo(){
		WeatherInfo weatherInfo = new WeatherInfo();
		weatherInfo.setTodayWeatherInfo(todayWeatherInfo);
		weatherInfo.setForecastWeatherInfos(forecastList);
		weatherInfo.setZhishuInfos(zhishuList);
		return weatherInfo;
	}
}

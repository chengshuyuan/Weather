package com.csy.weather.domin;

import java.util.ArrayList;

public class NextHourWeatherInfo {
	private String description;
	private ArrayList<Double>dataList;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Double> getDataList() {
		return dataList;
	}
	public void setDataList(ArrayList<Double> dataList) {
		this.dataList = dataList;
	}
	
}

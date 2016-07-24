package com.csy.weather.domin;

import java.io.Serializable;

public class ForecastWeatherInfo implements Serializable{
	String date;
	String weatherType;
	String highTem;
	String lowTem;
	String windDirection;
	String windVelocity;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeatherType() {
		return weatherType;
	}
	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}
	public String getHighTem() {
		return highTem;
	}
	public void setHighTem(String highTem) {
		this.highTem = highTem;
	}
	public String getLowTem() {
		return lowTem;
	}
	public void setLowTem(String lowTem) {
		this.lowTem = lowTem;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getWindVelocity() {
		return windVelocity;
	}
	public void setWindVelocity(String windVelocity) {
		this.windVelocity = windVelocity;
	}	
}

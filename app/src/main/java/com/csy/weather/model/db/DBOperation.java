package com.csy.weather.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csy.weather.util.AppLog;

import java.util.ArrayList;

public class DBOperation implements IDBOperation {
	DBOpenHelper openHelper;
	SQLiteDatabase db;
	public DBOperation(Context context){
		openHelper = new DBOpenHelper(context);
	}
	@Override
	public void insertCity(String cityName, String weatherInfo){
		AppLog.e("insertCity", weatherInfo);
		db = openHelper.getWritableDatabase();
		String sql = "insert into city(cityName, weather) values(?,?)";
		db.execSQL(sql, new Object[]{cityName, weatherInfo});
		db.close();
	}
	
	@Override
	public void deleteCity(String cityName){
		db = openHelper.getWritableDatabase();
		String sql = "delete from city where cityName=?";
		db.execSQL(sql, new Object[]{cityName});
		db.close();
	}
	
	@Override
	public String getWeather(String cityName){
		db = openHelper.getWritableDatabase();
		String weatherInfo = null;
		String sql = "select weather from city where cityName=?";
		Cursor cursor = db.rawQuery(sql, new String[]{cityName});
		while(cursor.moveToNext()){
			weatherInfo = cursor.getString(0);
		}
		db.close();
		AppLog.e("getWeather", weatherInfo);
		return weatherInfo;
	}
	
	@Override
	public void updateWeather(String cityName, String weatherInfo){
		AppLog.e("updateWeather", weatherInfo);
		db = openHelper.getWritableDatabase();
		String sql = "update city set weather =? where cityName=?";
		db.execSQL(sql, new Object[]{weatherInfo, cityName});
		db.close();
	}
	
	@Override
	public boolean checkCity(String cityName){
		db = openHelper.getReadableDatabase();
		String city = null;
		String sql = "select * from city where cityName=?";
		Cursor cursor = db.rawQuery(sql, new String[]{cityName});
		while(cursor.moveToNext()){
			city = cursor.getString(0);
		}
		db.close();
		if(city == null)
			return false;
		return true;
	}
	
	@Override
	public ArrayList<String> getAllCity(){
		ArrayList<String> citysList = new ArrayList<String>();
		db = openHelper.getReadableDatabase();
		String city = null;
		String sql = "select * from city";
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			city = cursor.getString(0);
			citysList.add(city);
		}
		db.close();
		return citysList;
	}
}

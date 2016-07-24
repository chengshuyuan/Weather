package com.csy.weather.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by csy on 2016/7/16.
 */
public class ImportDB {
    private SQLiteDatabase database;
    private Context context;
    private final int BUFFER_SIZE = 10000;
    private static final String DB_NAME = "citycode";
    private static final String PACKAGE_NAME = "com.csy.weather";
    private static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;//在手机里存放数据库的位置
    private static final String DB_FILE = DB_PATH + "/" + DB_NAME;
    public ImportDB(Context context){
        this.context = context;
    }
    public SQLiteDatabase openDataBase(){
        if(!(new File(DB_FILE).exists())){
            try {
                InputStream inputStream = context.getResources().getAssets().open("citysCode.db");
                FileOutputStream fileOutputStream  = new FileOutputStream(DB_FILE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while((count = inputStream.read(buffer)) > 0){
                    fileOutputStream.write(buffer, 0, count);
                }
                fileOutputStream.close();
                inputStream.close();
                database = SQLiteDatabase.openOrCreateDatabase(DB_FILE, null);
                return database;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(DB_FILE, null);
    }
    public String getCityCode(String cityName){
        String cityCode = "";
        SQLiteDatabase db = openDataBase();
        String sql = "select * from city where city = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{cityName});
        if(cursor.moveToFirst()){
            cityCode = cursor.getString(2);
            return cityCode;
        }
        cursor.close();
        db.close();
        return cityCode;
    }
    public void closeDataBase(){
        this.database.close();
    }
}

package com.csy.weather.presenter;

import com.csy.weather.model.db.DBOperation;
import com.csy.weather.model.db.IDBOperation;
import com.csy.weather.view.activity.CityAdminActivity;
import com.csy.weather.view.iview.ICityAdminActivity;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/17.
 */
public class CityAdminActivityPresenter {

    private ICityAdminActivity activity;
    private IDBOperation dbOperation;

    public CityAdminActivityPresenter(CityAdminActivity activity){
        this.activity = activity;
        dbOperation = new DBOperation(activity);
    }

    public ArrayList<String> getAllCity(){
        return dbOperation.getAllCity();
    }
}

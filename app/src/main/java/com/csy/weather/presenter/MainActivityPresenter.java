package com.csy.weather.presenter;

import com.csy.weather.model.db.DBOperation;
import com.csy.weather.model.db.IDBOperation;
import com.csy.weather.view.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by csy on 2016/7/2.
 */
public class MainActivityPresenter {
    private MainActivity activity;
    private IDBOperation dbOperation;

    public MainActivityPresenter(MainActivity activity){
        this.activity = activity;
        dbOperation = new DBOperation(activity);
    }

    public void getAllCity(){
        ArrayList<String > allCity = new ArrayList<String>();
        allCity = dbOperation.getAllCity();
        activity.addFragment(allCity);
    }
}

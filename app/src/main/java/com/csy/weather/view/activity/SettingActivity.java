package com.csy.weather.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.csy.weather.R;
import com.csy.weather.common.BaseActivity;
import com.csy.weather.util.JumpUtil;

public class SettingActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout layoutCityAdmin, layoutHelp, layoutVersion, layoutAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView(){
        layoutCityAdmin = findView(R.id.layoutCityAdmin);
        layoutHelp = findView(R.id.layoutHelp);
        layoutVersion = findView(R.id.layoutVersion);
        layoutAuthor = findView(R.id.layoutAuthor);
        layoutCityAdmin.setOnClickListener(this);
        layoutHelp.setOnClickListener(this);
        layoutVersion.setOnClickListener(this);
        layoutAuthor.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutCityAdmin:
                JumpUtil.jumpToCityAdmin(this);
                finish();
                break;
            case R.id.layoutHelp:
                JumpUtil.jumpToHelpActivity(this);
                break;
            case R.id.layoutVersion:
                JumpUtil.jumpToVersionActivity(this);
                break;
            case R.id.layoutAuthor:
                JumpUtil.jumpToAuthorActivity(this);
                break;
            default:
                break;
        }
    }
}

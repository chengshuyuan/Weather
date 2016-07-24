package com.csy.weather.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.csy.weather.R;
import com.csy.weather.domin.Constant;
import com.csy.weather.domin.ZhishuInfo;

/**
 * Created by csy on 2016/7/17.
 */
public class ZhishuInfoDialog extends AlertDialog {
    private Context context;
    private ZhishuInfo zhishuInfo;
    private ImageView zhishuImage;
    private TextView nameText, valueText, detailText;
    private int position;
    public ZhishuInfoDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public ZhishuInfoDialog(Context context, ZhishuInfo zhishuInfo, int position){
        super(context, R.style.MyDialog);
        this.context = context;
        this.zhishuInfo = zhishuInfo;
        this.position = position;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_zhishu);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        zhishuImage = (ImageView)this.findViewById(R.id.imageview_dialog_zhishu);
        zhishuImage.setBackgroundResource(Constant.zhishuIcons[position]);
        nameText = (TextView)this.findViewById(R.id.textview_dialog_name);
        valueText = (TextView)this.findViewById(R.id.textview_dialog_value);
        detailText = (TextView)this.findViewById(R.id.textview_dialog_detail);
        nameText.setText(zhishuInfo.getName());
        valueText.setText(zhishuInfo.getValue());
        detailText.setText("   " + zhishuInfo.getDescription());
    }
}

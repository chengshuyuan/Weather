package com.hb.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hzchenxuexing
 * @date 2016年03月01日
 * <p/>
 * Copyright 2016 NetEase. All rights reserved.
 */
public class SuperListView extends ListView {

    public interface Show {
        boolean isShow(boolean init);
    }

    public class SuperFixedViewInfo extends FixedViewInfo {
        /**
         * 要想实现footer的显示与隐藏，view必须是ViewGroup的派生类
         */
        public Show show;
    }

    private List<SuperFixedViewInfo> footers = new ArrayList<>();

    public SuperListView(Context context) {
        super(context);
    }

    public SuperListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public SuperListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void changeFooterVisibility() {
        if (footers.size() > 0) {
            Iterator<SuperFixedViewInfo> ite = footers.iterator();
            while (ite.hasNext()) {
                SuperFixedViewInfo info = ite.next();
                if ((info.view instanceof ViewGroup) && info.show != null) {
                    int visi;
                    if (info.show.isShow(false)) {
                        // 其实这里不应该直接用VISIBLE,而是需要在之前维护一个列表
                        //　记录之前的显示状态，在这里还原，不过目前需求比较简单
                        //　以后由于求的话再加
                        visi = VISIBLE;
                    } else {
                        visi = GONE;
                    }
                    setViewGroupVisibility(info.view, visi);
                }
            }
        }
    }

    private void setViewGroupVisibility(View view, int visi) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                ((ViewGroup) view)
                        .getChildAt(i).setVisibility(visi);
            }
        }
    }

    @Override
    public boolean removeFooterView(View v) {
        boolean res = super.removeFooterView(v);
        if (res && footers.size() > 0) {
            Iterator<SuperFixedViewInfo> ite = footers.iterator();
            while (ite.hasNext()) {
                SuperFixedViewInfo fvi = ite.next();
                if (fvi.view.equals(v)) {
                    footers.remove(fvi);
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public void addFooterView(View v) {
        addFooterView(v, null);
    }

    /**
     * @param v
     * @param show 注意，该方法会被{@link PullToRefreshBase#onRefreshComplete()}所调用
     */
    public void addFooterView(View v, Show show) {
        addFooterView(v, null, false, show);
    }

    @Override
    public void addFooterView(View v, Object data, boolean isSelectable) {
        addFooterView(v, data, isSelectable, null);
    }

    /**
     * @param v
     * @param data
     * @param isSelectable
     * @param show         注意，该方法会被{@link PullToRefreshBase#onRefreshComplete()}所调用
     */
    public void addFooterView(View v, Object data, boolean isSelectable, Show show) {
        if (v == null)
            return;
        super.addFooterView(v, data, isSelectable);
        SuperFixedViewInfo fvi = new SuperFixedViewInfo();
        fvi.view = v;
        fvi.isSelectable = isSelectable;
        fvi.data = data;
        fvi.show = show;
        footers.add(fvi);
        if (show != null) {
            int vis;
            if (show.isShow(true)) {
                vis = VISIBLE;
            } else {
                vis = GONE;
            }
            setViewGroupVisibility(fvi.view, vis);
        }
    }

}

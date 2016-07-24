package com.csy.weather.common;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    public View view;
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    public View onCreateView(int layoutId, LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(layoutId, null);
        return view;
    }

    public <T extends View> T findView(int id){
        return (T)view.findViewById(id);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

}

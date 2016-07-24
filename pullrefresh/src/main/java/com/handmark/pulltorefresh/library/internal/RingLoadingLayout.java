package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * @author chenupt create on 2015/10/19
 */
public class RingLoadingLayout extends LoadingLayout {

    static final int ROTATION_ANIMATION_DURATION = 1200;

    private final Animation mRotateAnimation;
    private CircleProgressView circleProgressView;

    public RingLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);

        circleProgressView = (CircleProgressView) findViewById(R.id.cpv);
        circleProgressView.init(attrs, mode);

        mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
        mRotateAnimation.setDuration(ROTATION_ANIMATION_DURATION);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.pull_to_refresh_header_vertical_ring;
    }

    public void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    protected void onPullImpl(float scaleOfLayout) {
        float angle = scaleOfLayout * 360f - 90f;
        if (angle < 340 && angle >= 0) {
            circleProgressView.updateSweepAngle(angle);
        }
    }

    @Override
    protected void refreshingImpl() {
        circleProgressView.updateSweepAngle(340);
        circleProgressView.startAnimation(mRotateAnimation);
    }

    @Override
    protected void resetImpl() {
        if (circleProgressView != null) {
            circleProgressView.clearAnimation();
        }
    }

    @Override
    protected void pullToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }

    @Override
    public void hideAllViews() {
        super.hideAllViews();
        circleProgressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showInvisibleViews() {
        super.showInvisibleViews();
        circleProgressView.setVisibility(View.VISIBLE);
    }
}

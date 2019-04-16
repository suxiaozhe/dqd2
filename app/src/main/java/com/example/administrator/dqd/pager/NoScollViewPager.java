package com.example.administrator.dqd.pager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScollViewPager extends ViewPager {
    //是否能滑动，默认不能
    private boolean scrollable =false;

    public NoScollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public NoScollViewPager(@NonNull Context context) {
        super(context);
    }
    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollable;
    }
}

package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;


public class ParallelScrollView extends HorizontalScrollView {

    public ParallelScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    //拦截当前事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return false;
    }

}

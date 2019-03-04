package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/1 17:41
 * TODO:
 */
public class TouchScrollView extends ScrollView {
    public TouchScrollView(Context context) {
        super(context);
    }

    public TouchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        ListView展开需要屏蔽， true表示请求不进行事件拦截
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}

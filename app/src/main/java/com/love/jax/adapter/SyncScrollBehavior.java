package com.love.jax.adapter;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.love.jax.adapter
 * Created by jax on 2019/1/22 11:23
 * TODO:  两个列表联动效果
 */
public class SyncScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public SyncScrollBehavior(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       View child, View directTargetChild, View target,
                                       int nestedScrollAxes,int type) {

        //判断滑动方向，表示竖直方向
        return (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL)||super.onStartNestedScroll
                (coordinatorLayout, child, directTargetChild, target, nestedScrollAxes,type);
    }

    /**
     *
     * @param coordinatorLayout
     * @param child 观察者View
     * @param target 被观察者View
     * @param dx x方向移动
     * @param dy y方向移动
     * @param consumed
     * @param type
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout,
                                  View child, View target, int dx, int dy, int[] consumed,int type) {

        int scrollY = target.getScrollY();//获取被观察者在Y方向移动距离
        child.setScrollY(scrollY);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed,type);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout,
                                 View child, View target, float velocityX, float velocityY,
                                 boolean consumed) {
        // 快速滑动的惯性移动（松开手指后还会有滑动效果）保持监听者与被监听者一致性
        //velocityY  在Y轴方向上的滑动速度
        ((NestedScrollView)child).fling((int) velocityY);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX,
                velocityY, consumed);
    }
}

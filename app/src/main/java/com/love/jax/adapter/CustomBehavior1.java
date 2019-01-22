package com.love.jax.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.love.jax.R;

/**
 * com.love.jax.adapter
 * Created by jax on 2019/1/22 09:44
 * TODO:一个View监听一个View的状态改变
 * Behavior(CoordinatorLayout.Behavior/FloatingActionButton.Behavior)
 */
public class CustomBehavior1 extends CoordinatorLayout.Behavior<View> {

    public CustomBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * todo: 用来决定需要监听哪些控件或者容器的状态（1.知道监听谁；2.什么状态改变）
     * @param parent 父容器
     * @param child 子控件---需要监听dependency这个view的视图们---观察者
     * @param dependency 你要监听的那个View
     * @return 是否是需要的监听者
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child,
                                   @NonNull View dependency) {
         //还可以根据ID或者TAG来判断,控制被观察者的ID
        return dependency instanceof TextView && dependency.getId()==R.id.tv1 ||super.layoutDependsOn(parent, child, dependency);
    }

    /*
        当被监听的view发生改变的时候回调
        可以在此方法里面做一些响应的联动动画等效果。
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child,
                                          @NonNull View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
        //获取被监听的view的状态---垂直方向位置
        int offset = dependency.getTop() - child.getTop();
        //让child进行平移
        ViewCompat.offsetTopAndBottom(child, offset);
        //进行翻转
        child.animate().rotation(child.getTop()*20);
        return true;
    }
}

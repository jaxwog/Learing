package com.love.jax.activity.animation;



import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

/**
 *  动画框架
 *  1、最外层的滑动用于监听滑动距离，计算百分比
 *  2、LinearLayout用于拿到子View的属性进行拦截处理
 *  3、子View处理时候悄悄加上一个FrameLayout，用于处理动画效果，把FrameLayout作为子View添加到ViewGroup中
 */
public class AnimaFramework1Activity extends BaseActivity {



    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_anima_framework1;
    }
}

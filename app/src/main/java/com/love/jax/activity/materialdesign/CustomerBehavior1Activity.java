package com.love.jax.activity.materialdesign;


import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * behavior 联动效果
 *桥梁，监听者，包裹在里面的所有子控件或者容器产生联动效果
 * public abstract static class Behavior<V extends View>
 *     Behavior可以做到以下两种情况：
 *     1、某个View需要监听另外一个View的状态（比如：位置、大小、显示状态）
 *          （需要重写方法：layoutDependsOn和 onDependentViewChanged）
 *     2、某个View需要监听coordinateLayout里面所有控件的滑动状态
 *          需要重写方法：onStartNestedScroll/ onNestedPreScroll 和onNestedScroll）
 *     滑动控件指的是：RecyclerView/NestedScrollView/ViewPager
 */
public class CustomerBehavior1Activity extends BaseActivity {


    @BindView(R.id.tv1)
    TextView tv1;

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
        return R.layout.activity_customer_behavior1;
    }


    @OnClick(R.id.tv1)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv1:
                ViewCompat.offsetTopAndBottom(view,9);
                break;
        }
    }
}

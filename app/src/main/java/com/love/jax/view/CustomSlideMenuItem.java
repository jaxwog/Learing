package com.love.jax.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/5 10:07
 * TODO:实现仿QQ条目侧滑效果
 */
public class CustomSlideMenuItem extends LinearLayout {

    private Scroller mScroller;
    private View leftChild;
    private View rightChild;
    private float startX ;
    private float startY ;
    private float dx;
    private float dy;

    public CustomSlideMenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //设置布局为水平布局
        setOrientation(LinearLayout.HORIZONTAL);
        //创建Scroller，类似于属性动画效果，多了一个回弹效果
        mScroller = new Scroller(getContext(),null,true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取两个子View用于判断滑动的最大距离、滑动的距离与width百分比进行判断左还是右方向的回弹效果
        leftChild = getChildAt(0);
        rightChild = getChildAt(1);
        //    ----------需要处理滑动时候不进行触摸调用
        leftChild.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"查看消息，哈哈",Toast.LENGTH_SHORT).show();
            }
        });
        rightChild.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"信息删除",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取按下位置的坐标
                startX = ev.getX();
                startY =ev.getY();
                //调用此方法用于查看父类是否需要此事件
                super.dispatchTouchEvent(ev);
                return true;//直接拦截事件

            case MotionEvent.ACTION_MOVE:
                //以屏幕的左上角为起始点，水平方向表示X轴，X为正数不断向右增加
                //移动位置的坐标-按下位置坐标，向右移动X越来越大，表示为正数；向左移动X越来越小，表示为负数
                dx = ev.getX() - startX;
                dy = ev.getY() - startY;

                //dx > 0 向右滑动 dx<0 向左滑动
                //水平方向-竖直方向距离大于系统定义的滑动的最小距离，我们认为是进行了水平滑动
                if (Math.abs(dx) - Math.abs(dy) > ViewConfiguration.getTouchSlop()){
                    //滑动距离判断，防止滑动距离超出条目显示范围
                    //向左滑动getScrollX为正值
                    if(getScrollX() + (-dx)>rightChild.getWidth()||getScrollX() + (-dx) < 0){
                        return true;
                    }
                    //
                    this.scrollBy((int) -dx,0);
                    startX = ev.getX();
                    startY = ev.getY();
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:

                //判断当前松开手是往左滑还是往右滑
                int offset = (getScrollX()/(float)rightChild.getWidth()) > 0.5f ? rightChild.getWidth() - getScrollX() : - getScrollX();
                //仅仅只是把滑动的情况和参数描述和记录。
                mScroller.startScroll(getScrollX(), getScrollY(), offset, 0);
                invalidate();//不断调用View的绘制方法，然后调用computeScroll方法
                startX = 0;
                startY = 0;
                dx = 0;
                dy = 0;
                break;

            default:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }


    //在开启滑动的情况下（mScroller.startScroll），滑动的过程当中此方法会被不断调用
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}

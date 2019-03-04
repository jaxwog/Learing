package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Scroller;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/4 11:15
 * TODO: 仿照ViewPager进行简化版的事件拦截处理
 * 未解决问题： up事件处理，子控件没有触摸事件无法滑动问题
 */
public class CustomViewPager  extends ViewGroup {

    private int leftBound;
    private int rightBound;
    private int mTouchSlop;
    private float downX;
    private float moveX;
    private float lastMoveX;



    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewPager(context);
    }

    private void initViewPager( Context context) {
//        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        //系统允许的滑动的最小距离
        this.mTouchSlop = configuration.getScaledPagingTouchSlop();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int size = getChildCount();
            for (int i = 0; i < size; ++i) {
                final View child = getChildAt(i);
                child.layout(i*child.getMeasuredWidth(), 0, (i+1)*child.getMeasuredWidth(), child.getMeasuredHeight());
                child.setClickable(true);
            }
        }
        //左边界
        leftBound = getChildAt(0).getLeft();
        //右边界
        rightBound = getChildAt(getChildCount()-1).getRight();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = getChildCount();
        for (int i = 0; i < size; ++i) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
           switch (ev.getAction()){
               case MotionEvent.ACTION_DOWN:
                   downX = ev.getRawX();
                   lastMoveX = downX;
                   this.requestParentDisallowInterceptTouchEvent(true);
                   break;
               case MotionEvent.ACTION_MOVE:
                   moveX = ev.getRawX();
                   float xDiff = Math.abs(moveX - downX);
                   lastMoveX = moveX;
                   if (xDiff > mTouchSlop){
                       return true;
                   }
                   break;

                   default:
                       break;
           }

        return super.onInterceptTouchEvent(ev);
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent parent = this.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 处理滑动的逻辑
         * View.scrollTo(x,y);     让View相对于它初始的位置滚动一段距离。
         * View.scrollBy(x,y);      让View相对于它现在的位置滚动一段距离。
         *
         *      上面两种方法都是滑动View里面的内容，即里面的所有子控件。
        */

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                //滑动到一半需要处理后续滚动事件

                break;
            case MotionEvent.ACTION_MOVE:
             this.moveX = event.getRawX();
             int scrollDx = (int) (this.lastMoveX - this.moveX);
                if (getScrollX()+scrollDx < leftBound) {
                    scrollTo(leftBound, 0);
                    return true;
                }else if (getScrollX() + scrollDx + getWidth() > rightBound){
                    scrollTo(rightBound - getWidth(), 0);
                    return true;
                }

                scrollBy(scrollDx, 0);
                lastMoveX = moveX;


                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}

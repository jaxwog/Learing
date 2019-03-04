package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/4 16:23
 * TODO: 自定义实现qq侧滑效果
 * menu 的left表示完全显示出来
 */
public class CustomSlideMenu extends HorizontalScrollView {
    private int mScreenWidth;
    private ViewGroup mMenu;
    private ViewGroup mMain;
    private int mMenuRightPadding = 100;
    private int mMenuWidth;
    private boolean isOnce;
    private float downX;

    public CustomSlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics );
        mScreenWidth = outMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!isOnce){
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) wrapper.getChildAt(0);
            mMain = (ViewGroup) wrapper.getChildAt(1);

            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mMenu.getLayoutParams().width = mMenuWidth;
            mMain.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            this.scrollTo(mMenuWidth, 0);
            isOnce = true;
        }
        super.onLayout(changed, l, t, r, b);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                float dx = ev.getX() - downX;
                if(dx<mScreenWidth/3){
                    this.smoothScrollTo(mMenuWidth, 0);
                }else{
                    this.smoothScrollTo(0, 0);
                }
                return true;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //处理动画
        //滑动百分比因子 ，l表示menu的左侧到屏幕左边缘位置
        float factor = (float)l / mMenuWidth;
        //1.平移效果(兼容2.x，nineOldeAndroid.jar)
        //滑动的速度应该比主界面慢一点
//        ViewHelper.setTranslationX(mMenu, mMenuWidth*factor*0.6f);
        mMenu.setTranslationX(mMenuWidth*factor*0.6f);
        //2.缩放效果
        float leftScale = 1-0.4f*factor;
        mMenu.setScaleX(leftScale);
        mMenu.setScaleY(leftScale);

        float rightScale = 0.8f+0.2f*factor;
        mMain.setScaleX(rightScale);
        mMain.setScaleY(rightScale);
        //3.透明度效果
        mMenu.setAlpha(1-factor);
    }
}

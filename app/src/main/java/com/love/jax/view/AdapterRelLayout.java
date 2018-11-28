package com.love.jax.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.love.jax.JaxApplication;
import com.love.jax.utils.Logger;
import com.love.jax.utils.UIUitls;

/**
 * com.love.jax.view
 * Created by jax on 2018/11/15 22:34
 * TODO:
 */
public class AdapterRelLayout extends RelativeLayout {
    public AdapterRelLayout(Context context) {
        super(context);
    }

    public AdapterRelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterRelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint({"Ne wApi", "NewApi"})
    public AdapterRelLayout(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    boolean isFlag = true;
    static int num = 1;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isFlag){
            int count = getChildCount();
            //单例引用Activity，导致内存泄漏
//            float scaleX = UIUitls.getInstance(this.getContext()).getHorizontalScalValue();
//            float scaleY = UIUitls.getInstance(this.getContext()).getVertucakScalValue();
            float scaleX = UIUitls.getInstance(JaxApplication.getInstance()).getHorizontalScalValue();
            float scaleY = UIUitls.getInstance(JaxApplication.getInstance()).getVertucakScalValue();
            for (int i = 0; i < count; i++) {
                View view = getChildAt(i);
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                layoutParams.height = (int) (layoutParams.height*scaleY);
                layoutParams.width = (int) (layoutParams.width*scaleX);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin*scaleX);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin*scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin*scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin*scaleY);

            }
            Logger.i("wog","我刷新的是第"+num);
            isFlag = false;

        }



        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

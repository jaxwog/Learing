package com.love.jax.view.search;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * com.love.jax.view.search
 * Created by jax on 2019/3/14 16:47
 * TODO: 动画控制的共性
 */
public abstract class BaseController {

    public static final int STATE_ANIM_NONE = 0;
    public static final int STATE_ANIM_START = 1;
    public static final int STATE_ANIM_STOP = 2;
    public static final int DEFAULT_ANIM_TIME = 5000;
    public static final float DEFAULT_ANIM_STARTF = 0;
    public static final float DEFAULT_ANIM_ENDF = 1;
    public int mState = STATE_ANIM_NONE;
    public float mPro = -1;
    private View mView;

    protected abstract void draw(Canvas canvas, Paint paint);

    public void startAnim(){
    }

    public void resetAnim(){
    }

    public int getWidth(){
        return mView.getWidth();
    }
    public int getHeight(){
        return  mView.getHeight();
    }

    public void setBaseView(View view){
        mView = view;
    }

    public ValueAnimator startViewAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000l);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
             mPro = (float) animation.getAnimatedValue();
             mView.invalidate();

            }
        });

        valueAnimator.start();
        mPro = 0;

        return valueAnimator;
    }

}

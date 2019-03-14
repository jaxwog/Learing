package com.love.jax.view;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/13 19:17
 * TODO:处理画布剪贴功能
 */
public class RevealDrawable extends Drawable {
    private final Rect mTmpRect = new Rect();
    private Drawable mUnselectedDrawable;
    private Drawable mSelectedDrawable;
    private int mOrientation;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;

    public RevealDrawable(Drawable unselected,Drawable selected,int orientation) {
        mUnselectedDrawable = unselected;
        mSelectedDrawable = selected;
        mOrientation = orientation;
    }

    @Override
    public void draw(Canvas canvas) {
        // 绘制
        int level = getLevel();//from 0 (minimum) to 10000
        //三个区间
        //右边区间和左边区间--设置成灰色
        if(level == 10000|| level == 0){
            mUnselectedDrawable.draw(canvas);
        }
        else if(level==5000){//全部选中--设置成彩色
            mSelectedDrawable.draw(canvas);
        }else{
            //混合效果的Drawable
            /**
             * 将画板切割成两块-左边和右边
             */
            final Rect r = mTmpRect;
            //得到当前自身Drawable的矩形区域
            Rect bounds = getBounds();//获取图片的矩形区域
            {
                //1.先绘制灰色部分
                //level 0~5000~10000
                //比例，用来确定灰色部分所占比例（小于0表示左边灰色，大于0表示右边灰色）
                float ratio = (level/5000f) - 1f;
                int w = bounds.width();//获取图片宽度
                if(mOrientation==HORIZONTAL){
                    w = (int) (w*Math.abs(ratio));
                }
                int h = bounds.height();
                if(mOrientation==VERTICAL){
                    h = (int) (h*Math.abs(ratio));
                }

                //从左边还是右边裁剪
                int gravity = ratio < 0 ? Gravity.LEFT : Gravity.RIGHT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                        gravity,//从左边还是右边开始抠
                        w,//目标矩形的宽
                        h, //目标矩形的高
                        bounds, //被抠出来的rect
                        r);//目标rect

                canvas.save();//保存画布
                canvas.clipRect(r);//切割
                mUnselectedDrawable.draw(canvas);//画
                canvas.restore();//恢复之前保存的画布
            }
            {
                //2.再绘制彩色部分
                //level 0~5000~10000
                //比例
                float ratio = (level/5000f) - 1f;
                int w = bounds.width();
                //获取图片的宽度，减去灰色部分
                if(mOrientation==HORIZONTAL){
                    w -= (int) (w*Math.abs(ratio));
                }
                int h = bounds.height();
                if(mOrientation==VERTICAL){
                    h -= (int) (h*Math.abs(ratio));
                }
                 //小于0表示彩色图片从右边裁剪，大于0表示从左边裁剪
                int gravity = ratio < 0 ? Gravity.RIGHT : Gravity.LEFT;
                //从一个已有的bounds矩形边界范围中抠出一个矩形r
                Gravity.apply(
                        gravity,//从左边还是右边开始抠
                        w,//目标矩形的宽
                        h, //目标矩形的高
                        bounds, //被抠出来的rect
                        r);//目标rect

                canvas.save();//保存画布
                canvas.clipRect(r);//切割
                mSelectedDrawable.draw(canvas);//画
                canvas.restore();//恢复之前保存的画布
            }

        }

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        // 定好两个Drawable图片的宽高---边界bounds
        mUnselectedDrawable.setBounds(bounds);
        mSelectedDrawable.setBounds(bounds);
    }

    @Override
    public int getIntrinsicWidth() {
        //得到Drawable的实际宽度
        return Math.max(mSelectedDrawable.getIntrinsicWidth(),
                mUnselectedDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
        //得到Drawable的实际高度
        return Math.max(mSelectedDrawable.getIntrinsicHeight(),
                mUnselectedDrawable.getIntrinsicHeight());
    }

    @Override
    protected boolean onLevelChange(int level) {
        // 当设置level的时候回调---提醒自己重新绘制
        invalidateSelf();
        return true;
    }

    @Override
    public void setAlpha(int alpha) {


    }

    @Override
    public void setColorFilter(ColorFilter cf) {


    }

    @SuppressLint("WrongConstant")
    @Override
    public int getOpacity() {
        return 0;
    }
}

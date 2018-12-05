package com.love.jax.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.love.jax.utils.Logger;

/**
 * com.love.jax.adapter 参考 v7包中的 DividerItemDecoration
 * Created by jax on 2018/12/4 10:57
 * TODO: 绘制水平或者竖直方向的间隔线
 */
public class MyDivItemDecoration extends RecyclerView.ItemDecoration {
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 0;
    private int mOrientation;
    private Drawable mDivider;
    private int [] attrs = new  int[]{android.R.attr.listDivider};


    public MyDivItemDecoration(Context context,int orientation){
        TypedArray a = context.obtainStyledAttributes(attrs);
         this.mDivider = a.getDrawable(0);
         if (mDivider==null){
             Logger.w("jax","获取系统间隔线失败");
         }
         //?????
         a.recycle();
         this.setOrientation(orientation);

    }

    public void setOrientation(int orientation){
        if (orientation !=0 && orientation !=1){
            throw new IllegalArgumentException("布局必须为竖直方向或者水平方向");
        }
        this.mOrientation = orientation;
    }



    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView
            .State state) {
        //自己绘制item间隔线
        if (parent.getLayoutParams()!=null && this.mDivider !=null){
            if (this.mOrientation == VERTICAL){
                drawVertical(c,parent);
            }else {
                drawHorizontal(c,parent);
            }

            super.onDraw(c,parent,state);
        }


    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount ; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin + Math.round(child.getTranslationX());
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top , right, bottom);
            if (i != (childCount-1)){
                this.mDivider.draw(canvas);
            }
        }
    }

    //画水平线
    private void drawVertical(Canvas canvas, RecyclerView parent) {

        int left = parent.getPaddingLeft();
        int right = parent.getWidth()-parent.getPaddingRight();
         int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() +params.bottomMargin+Math.round(child.getTranslationY());
            int bottom = top + this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(left,top,right,bottom);
            if (i != (childCount-1)){
                this.mDivider.draw(canvas);
            }


        }




    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        /**
         * 1、获取条目之间的间隙宽度，Rect矩形区域
         * 获得条目的偏移量，所有的item都会回调该方法一次
         */
            if (this.mDivider == null){
                outRect.set(0,0,0,0);
            }else {
                if (this.mOrientation == VERTICAL){
                    outRect.set(0,0,0,mDivider.getIntrinsicHeight());
                }else {
                    outRect.set(0,0,this.mDivider.getIntrinsicWidth(),0);
                }
            }


    }


}

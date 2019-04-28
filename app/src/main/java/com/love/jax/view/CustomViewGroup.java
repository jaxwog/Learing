package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * com.love.jax.view
 * Created by jax on 2019-04-28 19:46
 * TODO:自定义布局文件，设置自己摆放
 */
public class CustomViewGroup extends ViewGroup {
    private static int OFFSET = 80;

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 摆放
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            left = i*OFFSET;
            right = left + child.getMeasuredWidth();
            bottom = top  + child.getMeasuredHeight();

            child.layout(left,top,right,bottom);

            top += child.getMeasuredHeight();

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize =  MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int maxWidth = 0;
        int maxHeight = 0;

        //1.测量自己的尺寸
    //  ViewGroup.onMeasure();
        //1.1 为每一个child计算测量规格信息(MeasureSpec)
    //  ViewGroup.getChildMeasureSpec();
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec,0,lp.width);
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec,0,lp.height);
        //1.2 将上面测量后的结果，传给每一个子View，子view测量自己的尺寸
            child.measure(childWidthSpec,childHeightSpec);
        }

        //1.3 子View测量完，ViewGroup就可以拿到这个子View的测量后的尺寸了
        //  child.getChildMeasuredSize();//child.getMeasuredWidth()和child.getMeasuredHeight()
        //  //1.4ViewGroup自己就可以根据自身的情况(Padding等等),来计算自己的尺寸(mode,value)
        //   ViewGroup.calculateSelfSize();

        switch (widthMode){
            case MeasureSpec.EXACTLY:
                maxWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    int widthAndOffset = i*OFFSET + child.getMeasuredWidth();
                    maxWidth = Math.max(widthSize,widthAndOffset);
                }
              break;
              default:
                  break;

        }

        switch (heightMode){
            case MeasureSpec.EXACTLY:
                maxHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    maxHeight += child.getMeasuredHeight();
                }
                break;
            default:
                break;

        }

        //2.保存自己的尺寸
        //ViewGroup.setMeasuredDimension(size);
        setMeasuredDimension(maxWidth,maxHeight);

    }
}

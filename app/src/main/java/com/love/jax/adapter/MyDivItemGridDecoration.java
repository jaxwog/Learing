package com.love.jax.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * com.love.jax.adapter
 * Created by jax on 2018/12/5 16:29
 * TODO:绘制网格线
 */
public class MyDivItemGridDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int[] attrs = new int[]{android.R.attr.listDivider};

    public MyDivItemGridDecoration(Context context) {
        TypedArray a = context.obtainStyledAttributes(attrs);
        this.mDivider = a.getDrawable(0);
        a.recycle();
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView
            .State state) {
        drawVertical(c,parent);
        drawHorizontal(c,parent);
    }

    //画水平线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight()+params.rightMargin;
            int top = child.getBottom() +params.bottomMargin;
            int bottom = top + this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(left,top,right,bottom);
            this.mDivider.draw(canvas);

        }

    }

    //画竖直线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left+this.mDivider.getIntrinsicWidth();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin +this.mDivider.getIntrinsicWidth();
            this.mDivider.setBounds(left,top,right,bottom);
            this.mDivider.draw(canvas);

        }
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int right = this.mDivider.getIntrinsicWidth();
        int bottom = this.mDivider.getIntrinsicHeight();
        int position = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        if (isLastColum(position,parent)){
//            outRect.set(0,0,0,bottom);
            right = 0;

        }else {
            right = this.mDivider.getIntrinsicWidth();
        }
        if (isLastRow(position,parent)){
//            outRect.set(0,0,right,0);
            bottom = 0;
        }else {
            bottom = this.mDivider.getIntrinsicHeight();
        }

        outRect.set(0,0,right,bottom);
    }

    //判断是不是最后一行
    private boolean isLastRow(int position,RecyclerView parent){
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        int lastRowCount = childCount % spanCount;
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            if (lastRowCount==0 || lastRowCount< spanCount){
                return true;
            }

        }
        return false;
    }


    //判断是不是最后一列
    private boolean isLastColum(int position,RecyclerView parent){
        int spanCount = getSpanCount(parent);
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            if ((position+1)%spanCount==0){
                return true;
            }
        }

        return false;
    }


    //获取表格布局有每行有多少item
    private int getSpanCount(RecyclerView parent){
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            GridLayoutManager lm = (GridLayoutManager) manager;
            int spanCount = lm.getSpanCount();
            return spanCount;
        }

        return 0;
    }
}

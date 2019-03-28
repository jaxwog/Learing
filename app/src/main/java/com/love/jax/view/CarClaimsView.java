package com.love.jax.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.love.jax.utils.ScreenUtil;


/**
 * com.love.jax.view
 * Created by jax on 2019/3/26 15:46
 * TODO:绘制流程记录点
 */
public class CarClaimsView extends View {

    private Paint mPaint;
    private Context mContext;
    int mPx ;
    int mBlueR;
    int mBlueRBig;
    int mDuckR;
    int mLeng;
    int mSize;

    public void setInt(int anInt) {
        mInt = anInt;
    }

    int mInt = -1;








    public CarClaimsView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPx = ScreenUtil.dip2px(mContext, 27.5f);
        mBlueR = ScreenUtil.dip2px(mContext, 7.5f);
        mBlueRBig = ScreenUtil.dip2px(mContext, 10.5f);
        mDuckR = ScreenUtil.dip2px(mContext, 4.5f);
        mLeng = ScreenUtil.dip2px(mContext, 96f);
        mSize = 5;

    }

    public CarClaimsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }







    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.parseColor("#CCCCCC"));
//        mPaint.setStrokeWidth(2);
        //绘制直线
//        for (int i = 0; i < mSize-1; i++) {
//
//            canvas.drawLine(mPx,mPx - 3+mLeng*i,mPx,mPx - 3+mLeng*(i+1),mPaint);
//
//            canvas.drawCircle(mPx,mPx - 3 + mLeng*(i+1),mDuckR,mPaint);
//        }


//        mPaint.setStrokeWidth(2);
//        canvas.drawLine(mPx,mPx-3,mPx,mPx-3+mLeng,mPaint);
//
//        //绘制灰色的圆环
//        mPaint.reset();
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.parseColor("#CCCCCC"));
//        canvas.drawCircle(mPx,mPx-3+mLeng,mDuckR,mPaint);

//        canvas.drawLine(mPx,mPx - 3,mPx,mPx - 3+mLeng,mPaint);

        //1表示第一个点，2表示最后一个点
        if (mInt ==1) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor("#CCCCCC"));
            mPaint.setStrokeWidth(2);
            canvas.drawLine(mPx,mPx - 3,mPx,mPx - 3+mLeng,mPaint);
            //第一个点，表示正在执行
            mPaint.reset();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor("#FF517DF7"));
            canvas.drawCircle(mPx, mPx - 3, mBlueR, mPaint);
            mPaint.reset();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            mPaint.setColor(Color.parseColor("#20517DF7"));
            canvas.drawCircle(mPx, mPx - 3, mBlueRBig, mPaint);
        }else if (mInt==2){
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor("#CCCCCC"));
            mPaint.setStrokeWidth(2);
            canvas.drawLine(mPx,0,mPx,mPx - 3,mPaint);
            canvas.drawCircle(mPx,mPx - 3,mDuckR,mPaint);
        }else {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.parseColor("#CCCCCC"));
            mPaint.setStrokeWidth(2);
            canvas.drawLine(mPx,0,mPx,mLeng,mPaint);
            canvas.drawCircle(mPx,mPx - 3,mDuckR,mPaint);
        }

    }

}

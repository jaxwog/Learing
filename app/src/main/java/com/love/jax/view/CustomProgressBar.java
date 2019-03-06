package com.love.jax.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.love.jax.R;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/6 10:14
 * TODO:自定义进度条，属性控制显示颜色大小等内容
 */
public class CustomProgressBar extends View {

    private int max;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private boolean textShow;
    private int style;
    private int progress;
    private Paint paint;
    public static final int STROKE = 0;
    public static final int FILL = 1;

    public CustomProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
      TypedArray typedArray =  context.obtainStyledAttributes(attrs,R.styleable.CustomProgressBar);
        max = typedArray.getInteger(R.styleable.CustomProgressBar_max, 100);
        roundColor = typedArray.getColor(R.styleable.CustomProgressBar_roundColor, Color.RED);
        roundProgressColor = typedArray.getColor(R.styleable.CustomProgressBar_roundProgressColor, Color.BLUE);
        textColor = typedArray.getColor(R.styleable.CustomProgressBar_textColor, Color.GREEN);
        textSize = typedArray.getDimension(R.styleable.CustomProgressBar_textSize, 55);
        roundWidth = typedArray.getDimension(R.styleable.CustomProgressBar_roundWidth, 10);
        textShow = typedArray.getBoolean(R.styleable.CustomProgressBar_textShow, true);
        style = typedArray.getInt(R.styleable.CustomProgressBar_style, 0);

      typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      //画默认的大圆环
        int center = getWidth()/2;//中心坐标点

        float radius =  center-roundWidth/2;//半径

        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);//设置空心(描边)
        paint.setStrokeWidth(roundWidth);//圆环的宽度
        paint.setAntiAlias(true);
        canvas.drawCircle(center, center, radius , paint);

        //画进度百分比
        paint.reset();
        paint.setColor(textColor);
        paint.setStrokeWidth(0);//圆环的宽度
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        int percent = (int) (progress/(float)max * 100);
        //y公式： float baselineY = centerY + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom
        if(textShow && percent!=0 && style == STROKE){
            canvas.drawText(percent+"%", (getWidth()-paint.measureText(percent+"%"))/2f,
                    getWidth()/2f-(paint.descent()+paint.ascent())/2f, paint);
        }

        //画圆弧
        //矩形区域，定义圆弧的形状大小
        RectF oval = new RectF(center-radius, center-radius, center+radius, center+radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);//圆形
        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval , 0, 360*progress/max, false, paint);
                break;
            case FILL:
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if(progress!=0)
                    canvas.drawArc(oval , 0, 360*progress/max, true, paint);
                break;
        }
    }

    //防止多线程进行修改数据
    public synchronized int getMax(){
        return max;
    }

    public synchronized void setMax(){
        if(max<0){
            throw new IllegalArgumentException("max不能小于0");
        }
        this.max = max;
    }

    public synchronized int getProgress(){
        return progress;
    }

    public synchronized void setProgress(int progress){
        if(progress<0){
            throw new IllegalArgumentException("progress不能小于0");
        }
        if(progress>max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();//子线程中进行重新绘制
        }
    }


    public int getRoundColor() {
        return roundColor;
    }


    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }


    public int getRoundProgressColor() {
        return roundProgressColor;
    }


    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }


    public int getTextColor() {
        return textColor;
    }


    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    public float getTextSize() {
        return textSize;
    }


    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }


    public float getRoundWidth() {
        return roundWidth;
    }


    public void setRoundWidth(int roundWidth) {
        this.roundWidth = roundWidth;
    }


    public boolean isTextShow() {
        return textShow;
    }


    public void setTextShow(boolean textShow) {
        this.textShow = textShow;
    }
}

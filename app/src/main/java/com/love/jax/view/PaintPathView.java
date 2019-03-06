package com.love.jax.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.love.jax.utils.Logger;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/5 14:46
 * TODO:
 */
public class PaintPathView extends View {

    private Paint mPaint;
    private String str = "商abcdef";


    public PaintPathView(Context context) {
        super(context);
        mPaint = new Paint();

    }

    public PaintPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //重置
        mPaint.reset();
        //设置颜色
        mPaint.setColor(Color.RED);
        //设置透明度
        mPaint.setAlpha(255);

        //设置画笔样式
//        mPaint.setStyle(Paint.Style.FILL);//填充内容
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStyle(Paint.Style.STROKE);//描边

        //画笔描边宽度
        mPaint.setStrokeWidth(50f);

        //防锯齿，会损失一定的性能
        mPaint.setAntiAlias(true);
        //设置是否使用图像防抖动处理，会使图像颜色更加的清新饱满（会损失性能）
        mPaint.setDither(true);

        //线帽（直线多出来的部分，长度不算在绘制范围内）
//        mPaint.setStrokeCap(Paint.Cap.BUTT);//没有
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形


        //线条交汇处
//        mPaint.setStrokeJoin(Paint.Join.MITER);//锐角
//        mPaint.setStrokeJoin(Paint.Join.BEVEL);//直线
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧

        //测试
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(300, 100);
        path.lineTo(100, 300);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, mPaint);

        path.moveTo(100, 400);
        path.lineTo(300, 500);
        path.lineTo(100, 700);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, mPaint);

        path.moveTo(100, 800);
        path.lineTo(300, 800);
        path.lineTo(100, 1100);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, mPaint);

        textMetri(canvas);

    }

//     ----------------基线的问题--------------------
    private void textMetri(Canvas canvas){
        mPaint.reset();
        int top = 1300;
        int baselineX = 0;
        mPaint.setTextSize(200);
        mPaint.setTextAlign(Paint.Align.LEFT);

        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, top, 2000, top, mPaint);

        mPaint.setColor(Color.RED);
        //文本Metrics
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//		FontMetrics fontMetrics = mPaint.getFontMetricsInt();
//		fontMetrics.top;
//		fontMetrics.ascent;
//		fontMetrics.descent;
//		fontMetrics.bottom;

//以top为顶部位置，求出基准线位置，绘制的文字最高到top位置
        float baselineY = top - fontMetrics.top;
        canvas.drawText(str, baselineX, baselineY, mPaint);
        mPaint.setColor(Color.GREEN);
        //以top为基准线位置，绘制的文字在top上面
        canvas.drawText(str, baselineX, top, mPaint);
        mPaint.setColor(Color.YELLOW);
        //以top为文字中间位置，fontMetrics.bottom-fontMetrics.top表示文本的高度，
        //a = (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom; a表示文字的中间位置到基准线的距离
        baselineY = top + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom;
        canvas.drawText(str, baselineX, baselineY, mPaint);
    }

    //----------------文字绘制--------------------
    @SuppressLint("NewApi")
    private void textPaintAttrs() {
        //获得字符行间距
        mPaint.getFontSpacing();

        //获得字符之间的间距
        mPaint.getLetterSpacing();
//        mPaint.setLetterSpacing(letterSpacing)//设置

        //设置文本删除线
        mPaint.setStrikeThruText(true);
        //是否设置下划线
        mPaint.setUnderlineText(true);

        //设置文本大小
//        mPaint.setTextSize(textSize);
        mPaint.getTextSize();
        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//设置字体类型
//      Typeface.ITALIC
//        Typeface.create(familyName, style)//加载自定义字体
        //文字倾斜 默认0，官方推荐的-0.25f是斜体
        mPaint.setTextSkewX(-0.25f);

        //文本对齐方式
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        //计算制定长度的字符串（字符长度、字符个数、显示的时候真实的长度）
//      int breadText = mPaint.breakText(text, measureForwards, maxWidth, measuredWidth)

        mPaint.setTextSize(50);
        float[] measuredWidth = new float[1];
        int breakText = mPaint.breakText(str, true, 200, measuredWidth);
        Logger.i("jax", "breakText=" + breakText + ", str.length()=" + str.length() + ", " +
                "measredWidth:" + measuredWidth[0]);

        // Rect bounds获取文本的矩形区域（宽高）
//      mPaint.getTextBounds(text, index, count, bounds)
//      mPaint.getTextBounds(text, start, end, bounds)

        //获取文本的宽度，和上面类似，但是是一个比较粗略的结果
//        float measureText = mPaint.measureText(str);
        //获取文本的宽度，和上面类似，但是是比较精准的。
//        float[] measuredWidth = new float[10];

        //measuredWidth得到每一个字符的宽度；textWidths字符数
//        int textWidths = mPaint.getTextWidths(str, measuredWidth);
//        mPaint.getTextWidths(text, start, end, widths)
//        Logger.i("jax", "measureText:" + measureText + ", textWidths:" + textWidths);
    }

}

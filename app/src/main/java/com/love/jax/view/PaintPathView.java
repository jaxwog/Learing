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
 * ****************负责设置获取图形绘制、路径相关：***********
 *    1.setStyle(Paint.Style style)
 *    设置画笔样式，取值有
 * Paint.Style.FILL :填充内部
 * Paint.Style.FILL_AND_STROKE ：填充内部和描边
 * Paint.Style.STROKE ：仅描边、
 * 注意STROKE、FILL_OR_STROKE与FILL模式下外轮廓的位置会扩大。
 * 2.setStrokeWidth(float width)
 * 设置画笔宽度
 * 3.setAntiAlias(boolean aa)
 * 设置画笔是否抗锯齿
 * 4.setStrokeCap(Paint.Cap cap) ------demo演示
 * 设置线冒样式，取值有Cap.ROUND(圆形线冒)、Cap.SQUARE(方形线冒)、Paint.Cap.BUTT(无线冒)
 * 注意：冒多出来的那块区域就是线帽！就相当于给原来的直线加上一个帽子一样，所以叫线帽
 * 5.setStrokeJoin(Paint.Join join) ------ demo演示
 * 设置线段连接处样式，取值有：Join.MITER（结合处为锐角）、Join.Round(结合处为圆弧)、Join.BEVEL(结合处为直线)
 * 6.setStrokeMiter(float miter)
 * 设置笔画的倾斜度，90度拿画笔与30拿画笔，画出来的线条样式肯定是不一样的吧。（事实证明，根本看不出来什么区别好吗……囧……）
 * void reset()
 * 清空画笔复位。
 * void set(Paint src)
 *             设置一个外来Paint画笔。
 * 7.void setARGB(int a, int r, int g, int b)
 * int getAlpha()
 * void setAlpha(int a)
 * int getColor()
 * void setColor(int color)
 * 获取与设置alpha值、颜色、ARGB等。
 * final boolean isAntiAlias()
 * 8.void setAntiAlias(boolean aa)
 * 获取与设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢，一般会开启。设置后会平滑一些；
 * final boolean isDither()
 * 9.void setDither(boolean dither)
 * 获取与设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满、图像更加清晰。
 * 10.setPathEffect(PathEffect effect);
 * * 设置绘制路径的效果，如点画线等
 * （1）、CornerPathEffect——圆形拐角效果
 *     paint.setPathEffect(new CornerPathEffect(100));
 *     利用半径R=50的圆来代替原来两条直线间的夹角
 * （2）、DashPathEffect——虚线效果
 *     //画同一条线段,偏移值为15
 *     paint.setPathEffect(new DashPathEffect(new float[]{20,10,50,100},15));
 *     intervals[]：表示组成虚线的各个线段的长度；整条虚线就是由intervals[]中这些基本线段循环组成的。比如，我们定义new float[] {20,10}
 *     ；那这个虚线段就是由两段线段组成的，第一个可见的线段长为20，每二个线段不可见，长度为10；
 *     phase：
 *     开始绘制的偏移值
 *     .....
 * 11.setXfermode(Xfermode xfermode);
 * 设置图形重叠时的处理方式，如合并，取交集或并集，经常用来制作橡皮的擦除效果
 * 12.setMaskFilter(MaskFilter maskfilter);
 * 设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等
 * 13.setColorFilter(ColorFilter colorfilter);
 * 设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果
 * 14.setShader(Shader shader);
 * 设置图像效果，使用Shader可以绘制出各种渐变效果
 * 15.setShadowLayer(float radius ,float dx,float dy,int color);
 * 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
 *
 *
 *
 *
 * ******************文字相关的*********
 *             float getFontSpacing()
 *             获取字符行间距。
 *             float getLetterSpacing()
 *             void setLetterSpacing(float letterSpacing)
 *             设置和获取字符间距
 *
 *             final boolean isUnderlineText()
 *             void setUnderlineText(boolean underlineText)
 *             是否有下划线和设置下划线。
 *             final boolean isStrikeThruText()
 *             void setStrikeThruText(boolean strikeThruText)
 *             获取与设置是否有文本删除线。
 *
 *             float getTextSize()
 *             void setTextSize(float textSize)
 *             获取与设置文字大小，注意：Paint.setTextSize传入的单位是px，TextView.setTextSize传入的单位是sp，注意使用时不同分辨率处理问题。
 *
 *             Typeface getTypeface()
 *             Typeface setTypeface(Typeface typeface)
 *             获取与设置字体类型。Android默认有四种字体样式：BOLD(加粗)、BOLD_ITALIC(加粗并倾斜)、ITALIC(倾斜)、NORMAL(正常)
 *             ，我们也可以通过Typeface类来自定义个性化字体。
 *
 *             float getTextSkewX()
 *             void setTextSkewX(float skewX)
 *             获取与设置文字倾斜，参数没有具体范围，官方推荐值为-0.25，值为负则右倾，为正则左倾，默认值为0。
 *
 *             Paint.Align getTextAlign()
 *             void setTextAlign(Paint.Align align)
 *             获取与设置文本对齐方式，取值为CENTER、LEFT、RIGHT，也就是文字绘制是左边对齐、右边还是局中的。
 *
 *             setSubpixelText(boolean subpixelText)
 *             固定的几个范围：320*480，480*800，720*1280，1080*1920等等；那么如何在同样的分辨率的显示器中增强显示清晰度呢？
 *             亚像素的概念就油然而生了，亚像素就是把两个相邻的两个像素之间的距离再细分，再插入一些像素，这些通过程序加入的像素就是亚像素。在两个像素间插入的像素个数是通过程序计算出来的，一般是插入两个、三个或四个。
 *             所以打开亚像素显示，是可以在增强文本显示清晰度的，但由于插入亚像素是通过程序计算而来的，所以会耗费一定的计算机性能。
 *
 *             int breakText(String text, boolean measureForwards, float maxWidth, float[]
 *             measuredWidth)
 *             比如文本阅读器的翻页效果，我们需要在翻页的时候动态折断或生成一行字符串，这就派上用场了~
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

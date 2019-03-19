package com.love.jax.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.love.jax.utils.Logger;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/18 15:39
 * TODO:
 */
public class PathMeasureView extends View{

    private int mViewHeight;
    private int mViewWidth;
    private Paint paint;

    public PathMeasureView(Context context) {
        super(context);
        initPaint();
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.translate(mViewWidth/2, mViewHeight/2);

      /*   //-------------PathMeasure获取绘制路径长度---------
      Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);

        PathMeasure measure = new PathMeasure(path, false);
        PathMeasure measure2 = new PathMeasure(path, true);
        Logger.i("wog", "lengthMeasure="+measure.getLength());//600
        Logger.i("wog","lengthMeasure2="+ measure2.getLength());//800
        //forceClosed:不管path绘制的是否关闭，forceClosed=true都会自动测量path包括闭合部分的长度

        canvas.drawPath(path, paint);*/


        //-------------nextContour获取下一个path---------

       /* Path path = new Path();
        //多路径的效果需要关闭硬件加速！！
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.addRect(-100, -100, 100, 100, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);
        float length = measure.getLength();
        boolean nextContour = measure.nextContour();//获取下一个路径，有可能没有多个路径了，返回false
        float length2 = measure.getLength();
        Logger.i("wog", "length="+length);
        Logger.i("wog","length2="+ length2);
        canvas.drawPath(path, paint);*/


//        ----------getSegment:截取片段---------------
       /* Path path = new Path();
        //多路径的效果需要关闭硬件加速！！
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);
        float length = measure.getLength();
        Logger.i("wog", "length="+length);
        canvas.drawPath(path, paint);

        Path dst = new Path();
        dst.lineTo(-300, -300);

        //startWithMoveTo:false，代表该起始点是否位上一个的结束点(是否保持连续性)。
        measure.getSegment(200, 600, dst , false);
        paint.setColor(Color.GREEN);
        canvas.drawPath(dst, paint);*/

        //------------getPosTan--------------------
        Path path = new Path();
        path.addCircle(0, 0, 300, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);
        float[] pos = new float[2];
        float[] tan = new float[2];//tan=y/x(参考坐标系)
        measure.getPosTan(measure.getLength()/4, pos , tan );
        Logger.i("wog","position:x-"+pos[0]+", y-"+pos[1]);
        Logger.i("wog","tan:x-"+tan[0]+", y-"+tan[1]);

        canvas.drawPath(path, paint);

    }
}

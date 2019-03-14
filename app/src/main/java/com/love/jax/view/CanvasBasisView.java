package com.love.jax.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/13 10:42
 * TODO:canvas的高级使用基础
 */
public class CanvasBasisView extends View{

    public CanvasBasisView(Context context) {
        super(context);
    }

    public CanvasBasisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);// 填充
        paint.setStyle(Paint.Style.STROKE);//描边
        paint.setStrokeWidth(10); //画笔宽度
//        drawBasis(paint,canvas);//绘制基础图形
        drawBasisCanvas(paint,canvas);
    }

    //canvas变换技巧
    private void drawBasisCanvas(Paint paint, Canvas canvas) {
        //画布平移translate
//        RectF r = new RectF(0, 0, 400, 500);
//        canvas.drawRect(r, paint);
//        paint.setColor(Color.BLUE);
//        //将画布平移
//        canvas.translate(50, 50);
//        //当canvas执行drawXXX的时候就会新建一个新的画布图层
//        canvas.drawRect(r, paint);
//        RectF r2 = new RectF(0, 0, 400, 500);
//        //虽然新建了一个画布图层，但是还是会沿用之前设置的平移变换。不可逆的。（save和restore来解决）
//        canvas.drawRect(r2, paint);


        //2.缩放Scale
//        RectF r = new RectF(0, 0, 400, 500);
//        canvas.drawRect(r, paint);
//        paint.setColor(Color.BLUE);
////        sx,sy：分别对x/y方向的一个缩放系数,画布的缩放会导致里面所有的绘制的东西都会有一个缩放效果
//        canvas.scale(1.5f, 0.5f);
//        canvas.drawRect(r, paint);

        //3.旋转Rotate
//        RectF r = new RectF(200, 200, 400, 500);
//        canvas.drawRect(r, paint);
//        paint.setColor(Color.BLUE);
////        canvas.rotate(45);//旋转方向，顺时针旋转45度，旋转点为左上角原点坐标
//        canvas.rotate(45, 200, 200);//指定旋转点
//        canvas.drawRect(r, paint);


        //4.斜拉画布Skew
//        RectF r = new RectF(200, 200, 400, 500);
//        canvas.drawRect(r, paint);
//        paint.setColor(Color.BLUE);
//        //sx,sy倾斜度：X轴方向上倾斜60度，tan60=根号3
//        canvas.skew(1.73f, 0);
//        canvas.drawRect(r, paint);


        //5.裁剪画布clip
        RectF r = new RectF(200, 200, 400, 500);
        canvas.drawRect(r, paint);
        paint.setColor(Color.BLUE);
        //Canvas变换操作不会对前面造成影响
        canvas.clipRect(new Rect(250, 250, 300, 400));
        //裁剪后的画布显示颜色，为矩形实体块
        canvas.drawColor(Color.YELLOW);


    }

    private void drawBasis(Paint paint, Canvas canvas) {
        //画直线
//        canvas.drawLine(0, 0, 100, 100, paint);
        float []pts = {0,0,100,100,200,200,300,300};
//        canvas.drawLines(pts,paint);//绘制了两条直线0-100，200-300，中间有间隔线
        //传递参数问题，造成数组越界
//        canvas.drawLines(pts,20,2,paint);//通过offset设置线的间隔距离，可以实现虚线效果

        //画点
//        canvas.drawPoint(500, 500, paint);
//        canvas.drawPoints(pts, paint);

        //画矩形 Rect int和RectF float
        RectF r = new RectF(100, 100, 400, 500);
//        canvas.drawRect(r, paint);
//        canvas.drawRect(100, 100, 400, 400, paint);

        //画圆角矩形
        //x-radius ,y-radius圆角的半径
//        canvas.drawRoundRect(r, 30, 40, paint);
//      canvas.drawRoundRect(r, 150, 200, paint);//绘制成了椭圆

        //画圆
//        canvas.drawCircle(300, 300, 200, paint);

        //画椭圆
//        canvas.drawOval(r, paint);

        //画圆弧
//        canvas.drawArc(
//            r,
//            startAngle, //其实角度，相对X轴正方向
//            sweepAngle, //画多少角度的弧度
//            useCenter, //boolean, false：只有一个纯弧线；true：闭合的边
//            paint)；

//        paint.setColor(Color.GREEN);
//        canvas.drawArc(r, 0, 90, false, paint);//顺时针旋转90度

        //根据path画线
//        Path path = new Path();
//        path.moveTo(100,100);//落笔位置
//        path.lineTo(200,300);
//        path.lineTo(800,500);
//        path.close();//连接起始位置

//        path.cubicTo(100,100,200,500,300,300);//贝塞尔三次曲线
//        canvas.drawPath(path,paint);


        //圆角矩形路径(类似滴滴logo)
//        float radii[] = {10,10,10,10,10,10,50,60};
//        path.addRoundRect(r, radii, Path.Direction.CCW);
//        canvas.drawPath(path, paint);

        RectF r1 = new RectF(100, 100, 400, 500);
        Path path = new Path();
        path.addOval(r1, Path.Direction.CCW);

        //创建一块矩形区域
        Region region = new Region(100,100,400,500);
        Region region1 = new Region();
        region1.setPath(path,region);//path的椭圆区域和矩形区域进行交集

//        结合区域迭代器使用（得到图形里面的所有的矩形区域）
        RegionIterator iterator = new RegionIterator(region1);
        Rect rect = new Rect();
        while (iterator.next(rect)){
            canvas.drawRect(rect, paint);
        }
//        region.union(r1);//合并
//        region1.op(r1, Path.Op.INTERSECT);//交集部分



    }






}

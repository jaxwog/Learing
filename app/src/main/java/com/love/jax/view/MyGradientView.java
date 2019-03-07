package com.love.jax.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

import com.love.jax.R;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/6 16:35
 * TODO:渲染 Shader
 */
public class MyGradientView extends View{

    private Bitmap bitmap;
    private Paint paint;
    private BitmapShader bitmapShader;
    private int width;
    private int height;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private ComposeShader composeShader;
    public MyGradientView(Context context) {
        super(context);
//        bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.avatar)).getBitmap();
        bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.avatar3)).getBitmap();
        paint = new Paint();
        width = bitmap.getWidth();
        height = bitmap.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);//设置颜色
//        canvas.drawBitmap(bitmap, 0, 0, paint);//把图片绘制出来


        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
//        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);

        //设置像素矩阵，来调整大小，为了解决宽高不一致的问题。
         float scale = Math.max(width, height)*1.0f/Math.min(width, height);
         Matrix matrix = new Matrix();
         matrix.setScale(scale, scale);//缩放比例
         bitmapShader.setLocalMatrix(matrix);

// canvas.drawRect(new Rect(0, 0, 800, 800), paint);//绘制图像，根据图片属性设置效果
// canvas.drawCircle(height/2, height/2, height/2, paint);//以长边为直径进行，进行图片显示（需要进行缩放）
 canvas.drawCircle(scale*Math.min(width, height)/2f, scale*Math.max(width, height)/2f, Math.max(width, height)/2f, paint);

//  canvas.drawOval(new RectF(0, 0, width, height), paint);//绘制圆形图片，用来实现圆形头像效果
// canvas.drawOval(new RectF(0, 0, width, width), paint);

//设置圆形图片
//        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//        shapeDrawable.getPaint().setShader(bitmapShader);
//        shapeDrawable.setBounds(0,0,width,width);
//        shapeDrawable.draw(canvas);


        /**线性渐变
         * x0, y0, 起始点
         *  x1, y1, 结束点
         * int[]  colors, 中间依次要出现的几个颜色
         * float[] positions,数组大小跟colors数组一样大，中间依次摆放的几个颜色分别放置在那个位置上(参考比例从左往右)
         *    tile
         */
        LinearGradient linearGradient = new LinearGradient(500, 20, 900, 400, colors, null, Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawRect(500, 20, 900, 400, paint);

//        RadialGradient环形渲染    水波纹效果，充电水波纹扩散效果、调色板
        radialGradient = new RadialGradient(200, 650, 100, colors, null, Shader.TileMode.REPEAT);
        paint.setShader(radialGradient);
        canvas.drawCircle(200, 650, 200, paint);

//        SweepGradient梯度渲染(扫描渲染)    微信等雷达扫描效果。手机卫士垃圾扫描
      sweepGradient = new SweepGradient(650, 650, colors, null);
      paint.setShader(sweepGradient);
      canvas.drawCircle(650, 650, 200, paint);

//        ComposeShader组合渲染 组合方式，覆盖等
        composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        canvas.drawRect(0, 900, 800, 1800, paint);











    }
}

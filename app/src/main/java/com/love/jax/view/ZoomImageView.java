package com.love.jax.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.love.jax.R;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/7 10:42
 * TODO: 实现触摸放大镜效果
 */
public class ZoomImageView extends View {
    private Bitmap bitmap;
    private ShapeDrawable drawable;
    //放大倍数
    private static final int FACTOR = 3;
    //放大镜的半径
    private static final int RADIUS = 100;
    private Matrix matrix = new Matrix();

    public ZoomImageView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.activity_start);
        Bitmap bmp = bitmap;
        //放大后的整个图片
        bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth()*FACTOR, bmp.getHeight()*FACTOR, true);
        //制作一个圆形的图片(放大的局部)，盖在canvas上面
        BitmapShader shader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setShader(shader);
        //切出矩形区域---用于绘制圆（内切圆）
//        drawable.setBounds(0, 0, RADIUS*2, RADIUS*2);
        drawable.setBounds(0, 0, 0, 0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        //画制作好的圆形图片
        drawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        matrix.setTranslate(RADIUS - x*FACTOR, RADIUS - y*FACTOR);
        drawable.getPaint().getShader().setLocalMatrix(matrix);

        //绘制圆环位置
        drawable.setBounds(x-RADIUS, y-RADIUS, x+RADIUS, y+RADIUS);
//        drawable.setBounds(x-2*RADIUS, y-2*RADIUS, x, y);

        invalidate();
        return true;
    }
}

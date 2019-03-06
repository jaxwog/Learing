package com.love.jax.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
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
        bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.avatar)).getBitmap();
//        bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.avatar3)).getBitmap();
        paint = new Paint();
        width = bitmap.getWidth();
        height = bitmap.getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, paint);












    }
}

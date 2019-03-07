package com.love.jax.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.love.jax.utils.Logger;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/7 07:52
 * TODO:
 */

@SuppressLint("AppCompatCustomView")
public class LinearGradientTextView extends TextView {

    private TextPaint mPaint;
    private LinearGradient mGradient;
    Matrix matrix;
    private float translateX;
    private float deltaX = 20;

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 绘制完会调用该方法
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = getPaint();
        //GradientSize=两个文字的大小
        String text = getText().toString();
        Logger.i("wog","onSizeChanged Crash text = "+text);
        float textWidth = mPaint.measureText(text);//获取字体的宽度
        int GradientSize =(int) (3*textWidth/text.length()); //字体高亮及显示区域
        Logger.i("wog","onSizeChanged Crash textwidth = "+textWidth);
        //设置线性渲染，从最左侧开始，颜色进行改变
        mGradient = new LinearGradient(-GradientSize, 0, 0, 0, new int[]{0x22ffffff,0xffffffff,0x22ffffff}, new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);//边缘融合
        mPaint.setShader(mGradient);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth = getPaint().measureText(getText().toString());
        Logger.i("wog","onDraw crash textwidth = "+textWidth);
        translateX += deltaX;
        if(translateX > textWidth + 1|| translateX < 1){
            deltaX = -deltaX;
        }
//      matrix.setScale(sx, sy)
        //设置矩阵，添加需要绘制的区域
        matrix.setTranslate(translateX, 0);
        mGradient.setLocalMatrix(matrix);

        //延迟提交信息，减少性能需求
        postInvalidateDelayed(50);
    }
}

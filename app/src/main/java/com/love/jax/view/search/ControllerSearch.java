package com.love.jax.view.search;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * com.love.jax.view.search
 * Created by jax on 2019/3/14 16:48
 * TODO:控制搜索图标变为一条直线动画
 */

public class ControllerSearch extends BaseController{
    private String mColor = "#4CAF50";
    private int cx, cy, cr;
    private RectF mRectF;
    private int j = 15;

    public ControllerSearch(){
        mRectF = new RectF();
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.parseColor(mColor));
        switch (mState){
            case STATE_ANIM_NONE:
                drawNormalView(paint, canvas);
                break;
            case STATE_ANIM_START:
                drawStartAnimView(paint, canvas);
                break;
            case STATE_ANIM_STOP:
                drawStopAnimView(paint, canvas);
                break;
        }

    }

    //重新把直线绘制成搜索图标(相当于可逆过程)需要修改
    private void drawStopAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        //绘制圆弧和把手
        if (mPro>0.5){
            /**
             * -360 ~ 0 需要变换的范围
             * 	 0  ~ 0.5 mpro实际的变化范围
             * 转换公式：360*(mpro*2-1),
             */
            //绘制圆弧
            canvas.drawArc(
                    mRectF,45,360*(1-mPro*2),false,paint
            );
            //绘制把手
            canvas.drawLine(mRectF.right-j,
                    mRectF.bottom-j,
                    mRectF.right+cr-j,
                    mRectF.bottom+cr-j,
                    paint);
        }else {
            /**
             *   0    ~ 1 需要变换的范围
             *  0.5  ~ 1 mpro实际的变化范围
             * 转换公式：(mpro*2-1),
             */
            //绘制把手
            canvas.drawLine(mRectF.right - j + cr*(1-mPro*2),
                    mRectF.bottom - j + cr*(1-mPro*2),
                    mRectF.right - j + cr,
                    mRectF.bottom+cr-j,
                    paint);

        }

        //绘制下面的直线
        canvas.drawLine((mRectF.right - j + cr)*mPro,
                mRectF.bottom+cr-j,
                mRectF.right - j + cr,
                mRectF.bottom+cr-j,
                paint);

        canvas.restore();


        mRectF.left = cx - cr +250*(1-mPro);
        mRectF.top = cy - cr;
        mRectF.right = cx + cr + 250*(1-mPro);
        mRectF.bottom = cy + cr;



    }

    private void drawStartAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        //绘制圆弧和把手
        if (mPro<=0.5){
            /**
             * -360 ~ 0 需要变换的范围
             * 	 0  ~ 0.5 mpro实际的变化范围
             * 转换公式：360*(mpro*2-1),
             */
            //绘制圆弧
            canvas.drawArc(
                    mRectF,45,360*(mPro*2-1),false,paint
            );
            //绘制把手
            canvas.drawLine(mRectF.right-j,
                    mRectF.bottom-j,
                    mRectF.right+cr-j,
                    mRectF.bottom+cr-j,
                    paint);
        }else {
            /**
             *   0    ~ 1 需要变换的范围
             * 	 0.5  ~ 1 mpro实际的变化范围
             * 转换公式：(mpro*2-1),
             */
            //绘制把手
            canvas.drawLine(mRectF.right - j + cr*(mPro*2-1),
                    mRectF.bottom - j + cr*(mPro*2-1),
                    mRectF.right - j + cr,
                    mRectF.bottom+cr-j,
                    paint);

        }

        //绘制下面的直线
        canvas.drawLine((mRectF.right - j + cr)*(1-mPro*0.8f),
                mRectF.bottom+cr-j,
                mRectF.right - j + cr,
                mRectF.bottom+cr-j,
                paint);

        canvas.restore();


        mRectF.left = cx - cr + 250*mPro;
        mRectF.top = cy - cr;
        mRectF.right = cx + cr + 250*mPro;
        mRectF.bottom = cy + cr;




    }

    private void drawNormalView(Paint paint, Canvas canvas) {
        //确定在中心位置坐标，根据中心点坐标画搜索图标
        cr = getWidth()/22;
        cx = getWidth()/2;
        cy = getHeight()/2;

        //确定四个点坐标
        mRectF.left = cx - cr;
        mRectF.top = cy - cr;
        mRectF.right = cx+cr;
        mRectF.bottom = cy + cr;

        canvas.save();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        canvas.rotate(45,cx,cy);
        canvas.drawLine(cx + cr, cy, cx + cr*2, cy, paint);
        canvas.drawArc(mRectF,0,360,false,paint);
        canvas.restore();



    }

    @Override
    public void startAnim() {
        super.startAnim();
        mState = STATE_ANIM_START;
        startViewAnimator();
    }

    @Override
    public void resetAnim() {
        super.resetAnim();
        mState = STATE_ANIM_STOP;
        startViewAnimator();
    }
}

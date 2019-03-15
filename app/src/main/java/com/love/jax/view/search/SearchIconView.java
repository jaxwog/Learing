package com.love.jax.view.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * com.love.jax.view.search
 * Created by jax on 2019/3/14 16:42
 * TODO:
 */
public class SearchIconView extends View {

    private Paint mPaint;
    private BaseController mController;

    public SearchIconView(Context context) {
        super(context);
        init();
    }

    public SearchIconView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setController(BaseController controller){
        mController = controller;
        mController.setBaseView(this);
        invalidate();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mController.draw(canvas,mPaint);
    }

    public void startAnimation(){
        if (mController!=null){
            mController.startAnim();
        }
    }

    public void resetAnimation(){
        if(mController!=null){
            mController.resetAnim();
        }
    }

}

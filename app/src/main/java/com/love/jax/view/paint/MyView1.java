package com.love.jax.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.love.jax.R;

public class MyView1 extends View {


    private static final String TAG = "BARRY";

    private Paint mPaint = null;
    private Bitmap mBitmap = null;

    public MyView1(Context context) {
        this(context, null);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2);
        init();
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //默认有一个绘图坐标系,在ViewRootImpl中定义好的
        //第1次保存，并通过canvas.getSaveCount的到当前状态栈容量
        canvas.save();
        Log.i(TAG, "Current SaveCount1 = " + canvas.getSaveCount());

        // translate  他对坐标系进行了改变
        canvas.translate(400, 400);
        RectF rectF = new RectF(0, 0, 600, 600);
        //此处再次进行绘制运用了-50那么这里不是将坐标系改变，而是扩大了50像素
//        RectF r3 = new RectF(-50,-50,400,400);

        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        //第2次保存，并通过canvas.getSaveCount的到当前状态栈容量
        canvas.save();
        Log.i(TAG, "Current SaveCount2 = " + canvas.getSaveCount());

        canvas.rotate(45);

        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        //第3次保存，并通过canvas.getSaveCount的到当前状态栈容量
        canvas.save();
        Log.i(TAG, "Current SaveCount3 = " + canvas.getSaveCount());

        canvas.rotate(45);

        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        //第4次保存，并通过canvas.getSaveCount的到当前状态栈容量
        canvas.save();
        Log.i(TAG, "Current SaveCount4 = " + canvas.getSaveCount());
        //通过canvas.restoreToCount出栈到第三层状态
        canvas.restoreToCount(1);//出栈到指定层
        Log.i(TAG, "restoreToCount--Current SaveCount = " + canvas.getSaveCount());

        canvas.translate(0, 200);

        //rectF = new RectF(0,0,600,600);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        //通过canvas.restoreToCount出栈到第1层(最原始的那一层)状态
        canvas.restoreToCount(3);
        Log.i(TAG, "restoreToCount--Current SaveCount = " + canvas.getSaveCount());
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);


    }
}

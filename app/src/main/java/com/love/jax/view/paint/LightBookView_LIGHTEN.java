package com.love.jax.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.love.jax.R;

/**
 * @author barry
 * @time 2018-6-20
 * @version V1.0
 */
public class LightBookView_LIGHTEN extends View {
    private Paint mBitPaint;
    private Bitmap BmpDST,BmpSRC;

    public LightBookView_LIGHTEN(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = new Paint();
        BmpDST = BitmapFactory.decodeResource(getResources(),R.drawable.book_bg,null);
        BmpSRC = BitmapFactory.decodeResource(getResources(), R.drawable.book_light,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(BmpDST,0,0,mBitPaint);
        //目标
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        //源
        canvas.drawBitmap(BmpSRC,0,0,mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}

package com.love.jax.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.love.jax.R;
import com.love.jax.utils.MapUtils;

import static com.love.jax.utils.MapUtils.map;
import static com.love.jax.utils.MapUtils.touchFlag;
import static com.love.jax.utils.MapUtils.startRow;
import static com.love.jax.utils.MapUtils.startCol;
import static com.love.jax.utils.MapUtils.endRow;
import static com.love.jax.utils.MapUtils.endCol;

/**
 * com.love.jax.view
 * Created by jax on 2020/3/17 16:57
 * TODO:
 */
public class ShowMapView extends View {
    public ShowMapView(Context context) {
        super(context);
    }

    public ShowMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        //每格地图大小为80*80,注意:数组和屏幕坐标X和Y相反
        int row = (int) y / 80;
        int col = (int) x / 80;
        if (row>19||col>13){
            return super.onTouchEvent(event);
        }
        if (map[row][col] == 0) {
            touchFlag++;
            if (touchFlag == 1) {
                startRow = row;
                startCol = col;
                map[row][col] = 2;
            } else if (touchFlag == 2) {
                endRow = row;
                endCol = col;
                map[row][col] = 2;
            }
        }
        this.invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.route);
                    canvas.drawBitmap(bm, j * 80, i * 80, paint);
                } else if (map[i][j] == 1) {
                    Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.wall);
                    canvas.drawBitmap(bm, j * 80, i * 80, paint);
                } else if (map[i][j] == 2) {
                    Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.path);
                    canvas.drawBitmap(bm, j * 80, i * 80, paint);
                }
            }
        }
        if(MapUtils.path!=null) {
            canvas.drawPath(MapUtils.path, paint);
        }

    }
}

package com.love.jax.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.love.jax.R;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/12 14:41
 * TODO:实现滤镜效果，用滤镜类或者使用矩阵变换
 */
public class MaskFilterView extends View {

    private float progress;
    public MaskFilterView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //需要关闭硬件加速（没有关闭则没效果）
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        RectF r = new RectF(100, 100, 300, 300);
        /**模糊遮罩滤镜效果
         * BlurMaskFilter.Blur.INNER
         * BlurMaskFilter.Blur.NORMAL
         * BlurMaskFilter.Blur.OUTER
         * BlurMaskFilter.Blur.SOLID
         */
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        //浮雕滤镜效果（实现凸起的立体效果）
        /**
         * direction, 指定长度为xxx的数组标量[x,y,z]，用来指定光源的位置
         * ambient, 指定周边背景光源（0~1）
         * specular, 指镜面反射系数
         * blurRadius 指定模糊半径
         */
//        paint.setMaskFilter(new EmbossMaskFilter(new float[]{30, 30, 30}, 0.2f, 60, 80));
//        canvas.drawRect(r, paint);
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chrysanthemum2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon3);
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.btn_dialog);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.f_girl_dress);
//        canvas.drawBitmap(bitmap, 100, 300, paint);

        argbMatrix(canvas, bitmap);


    }


    /**
     * 颜色RGB的滤镜处理
     * 颜色通道过滤
     *
     * @param canvas
     * @param bitmap RGBA矩阵为：  255，255，255，255，1 用于和颜色矩阵进行运算得到ARGB颜色值
     *               五阶矩阵，用于计算颜色rgba，为4*5矩阵，乘以5*1矩阵，得到的是4*1矩阵（计算后的RGBA）
     *               颜色矩阵为：
     *               1.2f,0,0,0,0,   红色缩放1.2----平移量为0
     *               0,1,0,0,200,  绿色 缩放为1---平移量为200
     *               0,0,1,0,0,  蓝色
     *               0,0,0,1,0,  透明度
     */
    private void argbMatrix(Canvas canvas, Bitmap bitmap) {

        //需要关闭硬件加速（没有关闭则没效果）
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//            paint.setColor(Color.argb(255, 200, 100, 100));
//            canvas.drawRect(0, 0, 400, 400, paint);
        canvas.drawBitmap(bitmap, null, new RectF(0, 0, 400, 400 * bitmap.getHeight() / bitmap
                .getWidth()), paint);

        canvas.translate(400, 0);

        //去掉红色，绿色增加200
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                0,0,0,0,0,
//                0,1,0,0,200,
//                0,0,1,0,0,
//                0,0,0,1,0,
//        });

        //反向效果
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                -1,0,0,0,255,
//                0,-1,0,0,255,
//                0,0,-1,0,255,
//                0,0,0,1,0,
//        });

        //颜色增强(美白效果)（可以起到一个变亮的效果）---矩阵缩放方式
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                1.2f,0,0,0,0,
//                0,1.2f,0,0,0,
//                0,0,1.2f,0,0,
//                0,0,0,1.2f,0,
//        });

        /**
         *
         去色原理：只要把RGB三通道的色彩信息设置成一样；即：R＝G＝B，那么图像就变成了灰色，并且，为了保证图像亮度不变，
         同一个通道中的R+G+B=1:如：0.213+0.715+0.072＝1；   RGB=0.213, 0.715, 0.072； 三个数字是根据色彩光波频率及色彩心理学计算出来的.
         */

        //处理图片为黑白图片（图像学：如何让图片成为灰色即黑白？R+G+B=1）
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                0.213f, 0.715f, 0.072f, 0, 0,
//                0.213f, 0.715f, 0.072f, 0, 0,
//                0.213f, 0.715f, 0.072f, 0, 0,
//                0, 0, 0, 1f, 0,
//        });

        //发色效果（比如红色和绿色交换----把第一行和第二行交换）
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                0,1f,0,0,0,
//                1f,0,0,0,0,
//                0,0,1f,0,0,
//                0,0,0,1f,0,
//        });

        //复古风格(固定模板)
//        ColorMatrix matrix = new ColorMatrix(new float[]{
//                1/2f,1/2f,1/2f,0,0,
//                1/3f,1/3f,1/3f,0,0,
//                1/4f,1/4f,1/4f,0,0,
//                0,0,0,1f,0,
//            });


        ColorMatrix matrix = new ColorMatrix();
//        matrix.set(ColorMatrix);
        matrix.reset();//重置
        //色彩缩放
//        matrix.setScale(1, 1, 1.4f, 1);
        //饱和度设置（1，是原来不变；0灰色；>1增加饱和度）
//        matrix.setSaturation(progress);
    /**
     * axis,代表绕哪一个轴旋转，0,1,2
     * (0红色轴，1绿色，2蓝色)
     * degrees：旋转的度数
     */
//     matrix.setRotate(0, progress);

        /**
         * mul,multiply相乘 ---缩放
         * add，相加---平移
         */
//        paint.setColorFilter(new LightingColorFilter(0x00ff00, 0xff0000));
        //点击变色实现
      paint.setColorFilter(new LightingColorFilter(0xffffff, (int) progress));
//      paint.setColorFilter(new LightingColorFilter(0xffffff, 0x000000));


        // Xfermode图形混合滤镜   参考Xfermode.txt 文件
//      android.graphics.PorterDuff.Mode.
//        paint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN));
//        paint.setColorFilter(new PorterDuffColorFilter(Color.argb(255, 140, 90, 200), PorterDuff.Mode.MULTIPLY));

        //设置颜色过滤器
//        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(bitmap, null, new RectF(0, 0, 400, 400 * bitmap.getHeight() / bitmap
                .getWidth()), paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//              progress += 0.3f;
//                 progress += 20f;
               progress = 0xff0000;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                progress = 0x000000;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}

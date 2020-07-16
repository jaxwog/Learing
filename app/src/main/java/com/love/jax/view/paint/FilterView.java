package com.love.jax.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.love.jax.R;

/**
 *     PNG的文件结构
 *
 *     对于一个PNG文件来说，其文件头总是由位固定的字节来描述的：
 *
 *     十进制数    137 80 78 71 13 10 26 10
 *     十六进制数   89 50 4E 47 0D 0A 1A 0A
 *     其中第一个字节0x89超出了ASCII字符的范围，这是为了避免某些软件将
 *     PNG文件当做文本文件来处理。文件中剩余的部分由3个以上的PNG的数据块（Chunk）按照特定的顺序组成，因此，一个标准的PNG文件结构应该如下：
 *
 *     PNG文件标志 PNG数据块  ……  PNG数据块
 *     PNG数据块（Chunk）
 *
 *     PNG定义了两种类型的数据块，一种是称为关键数据块(critical chunk)，这是标准的数据块，另一种叫做辅助数据块(ancillary chunks)
 *     ，这是可选的数据块。关键数据块定义了4个标准数据块，每个PNG文件都必须包含它们，PNG读写软件也都必须要支持这些数据块。虽然PNG文件规范没有要求PNG
 *     编译码器对可选数据块进行编码和译码，但规范提倡支持可选数据块。
 *
 *     下表就是PNG中数据块的类别，其中，关键数据块部分我们使用深色背景加以区分。
 *
 *
 *     为了简单起见，我们假设在我们使用的PNG文件中，这4个数据块按以上先后顺序进行存储，并且都只出现一次。
 *
 *     数据块结构
 *
 *     PNG文件中，每个数据块由4个部分组成，如下：
 *
 *     名称  字节数 说明
 *     Length (长度) 4字节 指定数据块中数据域的长度，其长度不超过(231－1)字节
 *     Chunk Type Code (数据块类型码)    4字节 数据块类型码由ASCII字母(A-Z和a-z)组成
 *     Chunk Data (数据块数据)  可变长度    存储按照Chunk Type Code指定的数据
 *     CRC (循环冗余检测)    4字节 存储用来检测是否有错误的循环冗余码
 *
 *
 *     颜色通道：保存图像颜色信息的通道称为颜色通道。这句话是颜色通道的基本定义，我门可以理解为记录色彩信息的那一段数据，
 * 每个图像都有一个或者多个颜色通道，图像中默认的颜色通道信息取决于颜色模式
 *
 * 颜色模式：颜色模式我门可以理解为将某种颜色表现为数字形式的模型，或者说是一种记录图像颜色的方式。分为：RGB模式、CMYK模式、HSB模式、Lab
 * 颜色模式、位图模式、灰度模式、索引颜色模式、双色调模式和多通道模式等。
 * 其实实际上我们就认为，现在我要显示的色彩这个时候是用数字表示，最经典的RGB模式我们可以理解为R(255) G(0) B(0)当前这个像素，显示，在识别的时候为红色，他由红 绿 蓝
 * 三种色彩进行混合，显示出我们要的颜色其程度数值是0-255的范围
 *
 * 总结：也就是说，其实图像的显示，每个点都是由模式所决定的色彩数值混合形成我们想要的颜色，那么我们的滤镜效果实现，其实实际上就是去对于颜色通道进行过滤操作，在其原本的模式数值上面进行操作，达到更改图像色彩效果的目的，这就是我们所为的滤镜
 *
 *
 * 颜色矩阵
 * 在android当中，他所采用的颜色模式是RGBA模式，也就是在红绿蓝的基础上加入了Alpha透明度的概念，那么也就是他现在是一个四通道的模式
 * 在Android当中当android将图像信息获取出来时候，当前图像的颜色通道信息他用的是一个矩阵在进行保存
 * 在android当中真正的表现方式他应用了一个4*5的矩阵
 * 最后方加入一列，作为所为的亚元坐标，其实也就是分量值
 * 那么下面这个颜色的矩阵如果想要将我们的颜色值达到上诉效果，第二行的100表示在之前的颜色基础上增加100个绿色数值
 */
public class FilterView extends View {

    Paint paint;

    Bitmap bitmap;

    private int progress;

    public FilterView(Context context) {
        super(context);

        init();
    }

    private void init() {

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);


        RectF rectF = new RectF(200,100,bitmap.getWidth()+200,bitmap.getHeight());
        paint.reset();
        paint.setColor(Color.RED);
        //canvas.drawRect(rectF,paint);

        //canvas.translate(600,0);
        /**
         * Create a blur maskfilter.
         *
         * @param radius 阴影的半径
         * @param style  NORMOL -- 整个图像都被模糊掉
         *               SOLID -- 图像边界外产生一层与Paint颜色一致阴影效果，不影响图像的本身
         *               OUTER -- 图像边界外产生一层阴影，并且将图像变成透明效果
         *               INNER -- 在图像内部边沿产生模糊效果
         * @return
         */
        //paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));



        /**
         * Create an emboss maskfilter
         *
         * @param direction  指定光源的位置，长度为xxx的数组标量[x,y,z]
         * @param ambient    环境光的因子 （0~1），越接近0，环境光越暗
         * @param specular   镜面反射系数 越接近0，镜面反射越强
         * @param blurRadius 模糊半径 值越大，模糊效果越明显
         */
        //paint.setMaskFilter(new EmbossMaskFilter(new float[]{1,1,1},0.2f,60,80));

        //canvas.drawRect(rectF,paint);
        canvas.drawBitmap(bitmap,null, rectF,paint);
        // 平移运算---加法
        /*ColorMatrix colorMartrix = new ColorMatrix(new float[]{
                1, 0,0,0,0,
                0,1,0,0,100,
                0,0,1,0,0,
                0,0,0,1,0,
        });*/

        // 反相效果 -- 底片效果
       /* ColorMatrix colorMartrix = new ColorMatrix(new float[]{
                -1, 0,0,0,255,
                0,-1,0,0,255,
                0,0,-1,0,255,
                0,0,0,1,0,
        });*/
        // 缩放运算---乘法 -- 颜色增强
        /*ColorMatrix colorMartrix = new ColorMatrix(new float[]{
                1.2f, 0,0,0,0,
                0,1.2f,0,0,0,
                0,0,1.2f,0,0,
                0,0,0,1.2f,0,
        });*/

        // 黑白照片
        //是将我们的三通道变为单通道的灰度模式
        // 去色原理：只要把R G B 三通道的色彩信息设置成一样，那么图像就会变成灰色，
        // 同时为了保证图像亮度不变，同一个通道里的R+G+B =1
        //
        /*ColorMatrix colorMartrix = new ColorMatrix(new float[]{
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0,0,0,1,0,
        });*/



        // 发色效果---（比如红色和绿色交换）
        ColorMatrix colorMartrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0, 0,1,0,0,
                0,1,0,0,0,
                0,0,0,0.5F,0,
        });
        // 复古效果
        /*ColorMatrix colorMartrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f, 1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        });*/
        // 颜色通道过滤
        //两个矩阵
        //本身颜色矩阵 A
        //过滤矩阵  c
        //a*c=out color
//        ColorMatrix colorMartrix = new ColorMatrix(new float[]{
//                1.3F,0,0,0,0,
//                0,1.3F,0,0,0,
//                0,0,1.3F,0,0,
//                0,0,0,1,0,
//        });

        RectF rectF2 = new RectF(200,100 + bitmap.getHeight(),bitmap.getWidth()+200,bitmap.getHeight() * 2);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMartrix));
        canvas.drawBitmap(bitmap,null, rectF2,paint);
    }
}

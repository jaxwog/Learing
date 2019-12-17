package com.love.jax.imageloader.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * com.love.jax.imageloader.utils
 * Created by jax on 2019-12-13 23:48
 * TODO:图片压缩器，图片进行等比缩放
 * 做成抽象类不传入inputstream是单一职责（不是工具类）
 * 网络获取图片先获取到图片的宽高信息options.inJustDecodeBounds = true;
 * 第二次在进行图片的加载
 */
public abstract class BitmapDecoder {

    //子类通过实现该方法拿到图片的配置信息
    public abstract Bitmap decodeBitmapWithOption(BitmapFactory.Options options);

    /**
     * 压缩图片
     * @param reqWidth 指定要缩放后的宽度
     * @param reqHeight 指定要缩放后的高度
     * @return
     */
    public Bitmap decodeBitmap(int reqWidth,int reqHeight){
        //1.初始化Options
        BitmapFactory.Options options = new BitmapFactory.Options();
        //Bitmap对象不占用内存
        //只需要读取图片的宽高信息，无需将整张图片加载到内存中
        options.inJustDecodeBounds = true;
        //2.根据options加载Bitmap，图片的数据存储在options中（第一次读取图片的宽高），子类进行实现
        decodeBitmapWithOption(options);
        //3.计算图片缩放的比例
        calculateSampleSizeWithOption(options,reqWidth,reqHeight);
        //4.通过options设置的缩放比例记载图片（第二次根据缩放比例读取一个缩放后的图片）
        return decodeBitmapWithOption(options);
    }


    /**
     * 计算图片缩放的比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     */
    private void calculateSampleSizeWithOption(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //计算缩放的比例
        //图片的原始宽高
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;
        if(width > reqWidth || height > reqHeight){
            //宽高的缩放比例
            int heightRatio = Math.round((float)height / (float)reqHeight);
            int widthRatio = Math.round((float)width / (float)reqWidth);

            //有的图是长图、有的是宽图，
            // 取较大的值（可能留下空白的地方）是防止图片加载变形，
            inSampleSize = Math.max(heightRatio, widthRatio);
        }

        //全景图
        //当inSampleSize为2，图片的宽与高变成原来的1/2
        //options.inSampleSize = 2
        options.inSampleSize = inSampleSize;

        //每个像素2个字节，通过压缩每个图片的像素存储大小，如果为ARGB_8888,则存放为4个字节
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        //Bitmap占用内存
        options.inJustDecodeBounds = false;
        //当系统内存不足时可以回收Bitmap
        options.inPurgeable = true;
        options.inInputShareable = true;
    }

}

package com.love.jax.imageloader.utils;

import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * com.love.jax.imageloader.utils
 * Created by jax on 2019-12-13 23:45
 * TODO:获取图片的宽高信息
 */
public class ImageViewHelper {

    //默认的图片宽高
    private static int DEFAULT_WIDTH = 200;
    private static int DEFAULT_HEIGHT = 200;


    /**
     * 获取ImageView控件的宽度
     * 1.getWidth（绘制完成，如果视图没有绘制完成没有值）
     * 2.layout_width（有可能设置的是WRAP_CONTENT）
     * 3.maxWidth
     *
     * @param imageView
     * @return
     */
    public static int getImageViewWidth(ImageView imageView) {
        if (imageView != null) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int width = 0;
            //绘制完成，拿到了宽度信息
            if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
                width = imageView.getWidth();
            }
            //如果width没有拿到信息并且params不为空
            if (width <= 0 && params != null) {
                width = params.width;
            }

            //如果还为空，则通过反射拿到配置信息中设置的最大宽度
            if (width <= 0) {
                width = getImageViewFieldValue(imageView, "mMaxWidth");
            }
            return width;
        }

        return DEFAULT_WIDTH;
    }

    /**
     * 获取图片的高度
     *
     * @param imageView
     * @return
     */
    public static int getImageViewHeight(ImageView imageView) {
        if (imageView != null) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int height = 0;
            if (params != null && params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                height = imageView.getWidth();
            }

            if (height <= 0 && params != null) {
                height = params.height;
            }

            if (height <= 0) {
                height = getImageViewFieldValue(imageView, "mMaxHeight");
            }
            return height;

        }

        return DEFAULT_HEIGHT;
    }

    private static int getImageViewFieldValue(ImageView imageView, String fieldName) {
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            //最大宽高都为私有变量，设置可编辑
            field.setAccessible(true);
            int fieldValue = (Integer) field.get(imageView);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                return fieldValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果为宽则返回宽度的默认值
        return 0;
    }
}

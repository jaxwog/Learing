package com.love.jax.imageloader.loader;

import android.graphics.Bitmap;
import android.util.Log;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * “空”加载器
 *
 */
public class NullLoader extends AbsLoader {

    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        Log.e("jax", "onLoad: 图片无法记载!");
        return null;
    }
}

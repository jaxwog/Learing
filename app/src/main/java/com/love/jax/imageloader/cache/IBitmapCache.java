package com.love.jax.imageloader.cache;

import android.graphics.Bitmap;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.cache
 * Created by jax on 2019-12-13 15:34
 * TODO:图片缓存接口
 */
public interface IBitmapCache {

    /**
     * 缓存
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);

    /**
     * 获取缓存
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 移除缓存
     * @param request
     */
    void remove(BitmapRequest request);
}

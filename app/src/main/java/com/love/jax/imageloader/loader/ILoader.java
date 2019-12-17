package com.love.jax.imageloader.loader;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.loader
 * Created by jax on 2019-12-13 15:43
 * TODO:加载器接口
 */
public interface ILoader {
    /**
     * 加载图片
     * @param request
     */
    void loadImage(BitmapRequest request);
}

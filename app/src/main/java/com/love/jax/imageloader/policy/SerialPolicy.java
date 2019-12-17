package com.love.jax.imageloader.policy;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.policy
 * Created by jax on 2019-12-13 23:59
 * TODO:比较序列号加载策略
 */
public class SerialPolicy implements ILoadPolicy {
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request1.getSerialNO() - request2.getSerialNO();
    }
}

package com.love.jax.imageloader.policy;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.policy
 * Created by jax on 2019-12-14 00:04
 * TODO:比较序列号逆序加载
 */
public class ReversePolicy implements ILoadPolicy {
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request2.getSerialNO() - request1.getSerialNO();
    }
}

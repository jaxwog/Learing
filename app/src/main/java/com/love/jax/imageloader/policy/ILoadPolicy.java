package com.love.jax.imageloader.policy;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.policy
 * Created by jax on 2019-12-13 23:58
 * TODO:加载策略接口
 */
public interface ILoadPolicy {
    /**
     * 两个BitmapRequest进行优先级比较
     * @param request1
     * @param request2
     * @return 小于0，request1 < request2，大于0，request1 > request2，等于
     */
    public int compareTo(BitmapRequest request1, BitmapRequest request2);
}

package com.love.jax.utils;

/**
 * com.love.jax.utils
 * Created by jax on 2018/11/13 23:25
 * TODO: 数字处理
 */
public class NumUtils {

    /**
     * 获取指定大小中的正随机数
     *
     * @param length
     * @return
     */
    public static int getRandom(int length) {
        if (length < 0) {
            length = 0;
        }
        return (int) (Math.random() * length);
    }
}

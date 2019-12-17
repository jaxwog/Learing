package com.love.jax.imageloader.config;

import com.love.jax.imageloader.cache.IBitmapCache;
import com.love.jax.imageloader.cache.MemoryCache;
import com.love.jax.imageloader.policy.ILoadPolicy;
import com.love.jax.imageloader.policy.ReversePolicy;

/**
 * com.love.jax.imageloader.config
 * Created by jax on 2019-12-14 00:19
 * TODO:配置信息
 */
public class ImageLoaderConfig {
    //缓存策略
    private IBitmapCache bitmapCache = new MemoryCache();
    //加载策略（honesty is the best policy）
    private ILoadPolicy loadPolicy = new ReversePolicy();
    //线程个数
    //Java虚拟机可用的处理器个数
    private int threadCount = Runtime.getRuntime().availableProcessors();
    //图片记载的显示配置
    private DisplayConfig displayConfig = new DisplayConfig();

    private ImageLoaderConfig() {

    }

    //生成器模式（不同的构建过程，生成不同表现形式的对象）
    //AlertDialog
    public static class Builder {
        private ImageLoaderConfig config;

        public Builder() {
            config = new ImageLoaderConfig();
        }

        /**
         * 设置缓存策略
         *
         * @param bitmapCache
         * @return
         */
        public Builder setCachePolicy(IBitmapCache bitmapCache) {
            config.bitmapCache = bitmapCache;
            return this;//链式编程
        }

        /**
         * 设置加载策略
         *
         * @param loadPolicy
         * @return
         */
        public Builder setLoadPolicy(ILoadPolicy loadPolicy) {
            config.loadPolicy = loadPolicy;
            return this;
        }

        /**
         * 设置线程个数
         *
         * @param count
         * @return
         */
        public Builder setThreadCount(int count) {
            config.threadCount = count;
            return this;
        }


        /**
         * 设置加载过程中的图片
         * @param resID
         * @return
         */
        public Builder setLoadingImage(int resID)
        {
            config.displayConfig.loadingImg=resID;
            return this;
        }
        /**
         * 设置加载过程中的图片
         * @param resID
         * @return
         */
        public Builder setFaildImage(int resID)
        {
            config.displayConfig.failedImg=resID;
            return this;
        }

        public ImageLoaderConfig build() {
            return config;
        }
    }

    public int getThreadCount() {
        return threadCount;
    }

    public ILoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public IBitmapCache getBitmapCache() {
        return bitmapCache;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }
}

package com.love.jax.imageloader.loader;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载器管理类，获取不同的loader策略
 * 没有提供添加方法，线程安全的
 */
public class LoaderManager {

    private static LoaderManager mInstance = new LoaderManager();
    //加载器管理容器
    private Map<String, ILoader> mLoaderMap = new HashMap<String, ILoader>();

    private NullLoader mNullLoader = new NullLoader();

    //设置静态代码块，同context
    private LoaderManager() {
        register("http", new UrlLoader());
        register("https", new UrlLoader());
        register("file", new LocalLoader());
    }

    public static LoaderManager getInstance() {
        return mInstance;
    }

    /**
     * 根据图片地址的协议获取特定的图片加载器
     *
     * @param schema
     * @return
     */
    public ILoader getLoader(String schema) {
        if (mLoaderMap.containsKey(schema)) {
            return mLoaderMap.get(schema);
        }
        //没有找到合适的，返回空加载器
        return mNullLoader;
    }

    /**
     * 注册加载器，可以支持系统中未来出现的其他协议的加载器
     *
     * @param schema
     * @param loader
     */
    public final void register(String schema, ILoader loader) {
        mLoaderMap.put(schema, loader);
    }

}

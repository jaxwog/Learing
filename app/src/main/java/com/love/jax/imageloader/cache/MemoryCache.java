package com.love.jax.imageloader.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.cache
 * Created by jax on 2019-12-13
 * TODO:内存缓存
 */
public class MemoryCache implements IBitmapCache {

    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        //缓存的最大值（可用内存的1/8）
        int maxSize = (int)Runtime.getRuntime().freeMemory() / 1024 / 8;
        mLruCache = new LruCache<String, Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //一个Bitmap的大小
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        mLruCache.put(request.getImageUriMD5(), bitmap);
        Log.d("jax", "put in MemoryCache:"+request.getImageUri());
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Log.d("jax", "get from MemoryCache:"+request.getImageUri());
        return mLruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUriMD5());
    }
}

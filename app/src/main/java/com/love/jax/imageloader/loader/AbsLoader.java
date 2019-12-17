package com.love.jax.imageloader.loader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.love.jax.imageloader.cache.IBitmapCache;
import com.love.jax.imageloader.config.DisplayConfig;
import com.love.jax.imageloader.core.SimpleImageLoader;
import com.love.jax.imageloader.request.BitmapRequest;

/**
 * com.love.jax.imageloader.loader
 * Created by jax on 2019-12-13 15:48
 * TODO:抽象加载器，做一些具体操作，接口只能声明方法
 */
public abstract class AbsLoader implements ILoader {

    //拿到用户自定义的缓存策略
    private IBitmapCache mCache = SimpleImageLoader.getObject().getConfig().getBitmapCache();
    //显示配置
    private DisplayConfig mDisplayConfig = SimpleImageLoader.getObject().getConfig().getDisplayConfig();

    /**
     * 模板方法
     * 如果缓存中没有图片信息，则加载图片信息并保存到图片缓存中
     */
    @Override
    public void loadImage(BitmapRequest request) {
        //从缓存获取，缓存中没有再加载
        Bitmap bitmap = mCache.get(request);
        if(bitmap == null){
            //加载前显示的图片
            showLoadingImage(request);

            //加载完成，再缓存
            //具体的加载方式，由子类决定
            bitmap = onLoad(request);
            cacheBitmap(request,bitmap);
        }

        //显示
        deliveryToUIThread(request, bitmap);
    }

    /**
     * 加载前显示的图片
     * @param request
     */
    protected void showLoadingImage(BitmapRequest request) {
        //指定了，显示配置
        if(hasLoadingPlaceHolder()){
            final ImageView imageView = request.getImageView();
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(mDisplayConfig.loadingImg);
                }
            });
        }
    }

    protected boolean hasLoadingPlaceHolder(){
        return (mDisplayConfig != null && mDisplayConfig.loadingImg !=-1);
    }

    protected boolean hasFailedPlaceHolder(){
        return (mDisplayConfig != null && mDisplayConfig.failedImg !=-1);
    }

    /**
     * 交给主线程显示
     * @param request
     * @param bitmap
     */
    protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                updateImageView(request, bitmap);
            }

        });
    }


    private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        //加载正常 ，tag防止图片错位
        if(bitmap != null && imageView.getTag().equals(request.getImageUri())){
            imageView.setImageBitmap(bitmap);
        }
        //有可能加载失败
        if(bitmap == null && hasFailedPlaceHolder()){
            imageView.setImageResource(mDisplayConfig.failedImg);
        }
        //监听
        //回调 给圆角图片  特殊图片进行扩展
        if(request.imageListener != null){
            request.imageListener.onComplete(imageView, bitmap, request.getImageUri());
        }
    }

    /**
     * 缓存图片
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if(request != null && bitmap != null){
            synchronized (mCache) {
                mCache.put(request,bitmap);
            }
        }
    }

    /**
     * 具体的加载实现
     * @param request
     * @return
     */
    protected abstract Bitmap onLoad(BitmapRequest request);


}

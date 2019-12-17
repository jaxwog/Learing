package com.love.jax.imageloader.request;

import android.util.Log;

import com.love.jax.imageloader.loader.ILoader;
import com.love.jax.imageloader.loader.LoaderManager;

import java.util.concurrent.BlockingQueue;

/**
 * com.love.jax.imageloader.request
 * Created by jax on 2019-12-14 00:12
 * TODO:请求转发线程，不断从请求队列中获取请求处理
 */
public class RequestDispatcher extends Thread {

    //请求队列，每个转发器都持有BlockingQueue队列
    private BlockingQueue<BitmapRequest> mRequestQueue;

    public RequestDispatcher(BlockingQueue<BitmapRequest> mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    @Override
    public void run() {
        //非阻塞状态，获取请求处理
        while (!isInterrupted()) {
            //从队列中获取优先级最高的请求进行处理
            try {
                //阻塞式的从请求队列RequestQueue中获取请求对象BitmapRequest
                BitmapRequest request = mRequestQueue.take();
                Log.d("jax", "---处理请求" + request.getSerialNO());
                //解析图片地址，获取对象的加载器，把拿到的BitmapRequest对象交给加载器抽象类进行处理
                String schema = parseSchema(request.getImageUri());
                //真正的加载网络数据在加载器中（子线程）执行，获取到loader对象（统一管理）
                ILoader loader = LoaderManager.getInstance().getLoader(schema);
                loader.loadImage(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 解析图片地址，获取schema
     *
     * @param imageUri
     * @return
     */
    private String parseSchema(String imageUri) {
        if (imageUri.contains("://")) {
            return imageUri.split("://")[0];
        } else {
            Log.e("jax", "图片地址schema异常！");
        }
        return null;
    }


}

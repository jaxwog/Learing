package com.love.jax.http;

import com.alibaba.fastjson.JSON;
import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

import java.util.concurrent.FutureTask;

/**
 * com.love.jax.http
 * Created by jax on 2019-12-03 16:47
 * TODO:封装每个网络请求任务，实现Runnable接口，加入到队列中进行网络执行请求
 * 持有请求参数T对象的引用
 */
public class HttpTask<T> implements Runnable {

    private IHttpService httpService;//每个任务需要持有获取网络的接口

    private FutureTask futureTask;

    /**
     * 构造方法
     *
     * @param requestHodler 封装了请求的参数
     */
    public HttpTask(RequestHodler<T> requestHodler) {
        httpService = requestHodler.getHttpService();
        httpService.setHttpListener(requestHodler.getHttpListener());
        httpService.setUrl(requestHodler.getUrl());
        httpService.setRequestType(requestHodler.getType());
        //增加方法
        IHttpListener httpListener = requestHodler.getHttpListener();
        httpListener.addHttpHeader(httpService.getHttpHeadMap());


        try {
            //将请求接口参数对象转换为String类型
            T request = requestHodler.getRequestInfo();
            if (request != null) {
                String requestInfo = JSON.toJSONString(request);
                httpService.setRequestData(requestInfo.getBytes("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        httpService.excute();
    }

    /**
     * 新增方法
     */
    public void start() {
        futureTask = new FutureTask(this, null);
        try {
            ThreadPoolManager.getInstance().execte(futureTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 新增方法
     */
    public void pause() {
        httpService.pause();
        if (futureTask != null) {
            ThreadPoolManager.getInstance().removeTask(futureTask);
        }

    }
}

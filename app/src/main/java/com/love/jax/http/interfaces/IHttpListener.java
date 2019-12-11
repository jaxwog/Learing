package com.love.jax.http.interfaces;

import org.apache.http.HttpEntity;

import java.util.Map;

/**
 * com.love.jax.http.interfaces
 * Created by jax on 2019-12-03 16:48
 * TODO: 处理结果回调，持有idataListener 的引用，处理结果返回给调用层
 *
 */
public interface IHttpListener {

    /**
     *
     * @param httpEntity 网络请求成功后，返回结果为该类型
     */
    void onSuccess(HttpEntity httpEntity);

    //网络请求失败回调
    void onFail();

    //添加头信息，用于处理断点续传操作
    void addHttpHeader(Map<String,String> headerMap);
}

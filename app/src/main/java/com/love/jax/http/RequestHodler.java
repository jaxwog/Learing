package com.love.jax.http;

import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

/**
 * com.love.jax.http
 * Created by jax on 2019-12-03 19:07
 * TODO:封装网络请求参数
 */
public class RequestHodler<T> {
    /**
     * 执行下载类
     */
    private IHttpService httpService;

    /**
     * 获取数据  回调结果的类
     */
    private IHttpListener httpListener;

    /**
     * 请求参数对应的实体
     */
    private T requestInfo;

    //请求网址url
    private String url;

    //请求类型，post或者get
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public IHttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(IHttpService httpService) {
        this.httpService = httpService;
    }

    public IHttpListener getHttpListener() {
        return httpListener;
    }

    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    public T getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(T requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

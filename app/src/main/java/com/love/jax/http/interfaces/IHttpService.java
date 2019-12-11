package com.love.jax.http.interfaces;

import java.util.Map;

/**
 * com.love.jax.http.interfaces
 * Created by jax on 2019-12-03 16:47
 * TODO:获取网络，该任务执行完成后，调用处理返回结果接口：IHttpListener
 */
public interface IHttpService {

    /**
     * 设置url
     * @param url
     */
    void setUrl(String url);

    /**
     * 执行获取网络
     */
    void excute();

    /**
     * 设置处理接口
     * @param httpListener
     */
    void setHttpListener(IHttpListener httpListener);


    /**
     * 设置请求参数
     * byte[]  2
     */
    void setRequestData(byte[] requestData);

    /**
     * 根据传入的类型判断post或者get请求
     * @param type post 、 get
     */
    void setRequestType(String type);



    /**
     *
     * 以下的方法是 额外添加的
     * 获取请求头的map
     * @return
     */
    Map<String,String> getHttpHeadMap();

    //取消下载
    boolean cancle();

    //是否取消下载
    boolean isCancle();

    //暂停下载
    void pause();

    //是否暂停下载
    boolean isPause();
}

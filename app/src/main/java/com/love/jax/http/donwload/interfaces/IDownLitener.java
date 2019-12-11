package com.love.jax.http.donwload.interfaces;

import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

/**
 * com.love.jax.http.donwload.interfaces
 * Created by jax on 2019-12-05 09:52
 * TODO:处理网络返回数据
 */
public interface IDownLitener extends IHttpListener {

    //持有IHTTPService对象，进行暂停等的服务
    void setHttpServive(IHttpService httpServive);

    //设置取消
    void  setCancleCallable();

    //设置暂停
    void  setPauseCallable();
}

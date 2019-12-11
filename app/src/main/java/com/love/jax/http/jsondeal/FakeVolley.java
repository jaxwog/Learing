package com.love.jax.http.jsondeal;

import com.love.jax.http.HttpTask;
import com.love.jax.http.RequestHodler;
import com.love.jax.http.ThreadPoolManager;
import com.love.jax.http.interfaces.IDataListener;
import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

import java.util.concurrent.FutureTask;

/**
 * com.love.jax.http.jsondeal
 * Created by jax on 2019-12-03 19:13
 * TODO:网络请求调用层接触到方法
 */
public class FakeVolley {

    /**
     *  暴露给调用层
     * @param requestInfo  请求参数类
     * @param url 网络地址
     * @param response  返回参数类
     * @param type 网络请求类型，post或者get
     * @param dataListener 网络调用完成（成功、失败）回调给调用层的接口
     * @param <T> 请求参数类型
     * @param <M> 响应参数类型
     */
    public static <T,M> void sendRequest(T  requestInfo, String url, Class<M> response,String type, IDataListener dataListener) {
        //将请求的参数封装为RequestHodler对象
        RequestHodler<T> requestHodler=new RequestHodler<>();
        requestHodler.setUrl(url);
        requestHodler.setType(type);
        requestHodler.setRequestInfo(requestInfo);
        //采用策略模式进行对象的实例化
        IHttpService httpService=new JsonHttpService();
        IHttpListener httpListener=new JsonDealLisener<>(response,dataListener);
        requestHodler.setHttpService(httpService);
        requestHodler.setHttpListener(httpListener);

        //将封装的对象通过创建兑现的方式传递给HttpTast任务,此时任务依然没有执行
        HttpTask<T> httpTask=new HttpTask<>(requestHodler);
        try {
            //将每个HTTPTask任务只是加入到线程池中
            ThreadPoolManager.getInstance().execte(new FutureTask<Object>(httpTask,null));
        } catch (InterruptedException e) {
            dataListener.onFail();
        }
    }
}

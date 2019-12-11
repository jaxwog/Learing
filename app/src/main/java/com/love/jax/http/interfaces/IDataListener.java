package com.love.jax.http.interfaces;

/**
 * com.love.jax.http.interfaces
 * Created by jax on 2019-12-03 16:49
 * TODO: 回调结果给调用层，封装到RequestHolder类中
 * M表示为返回数据的类型 ，reponceClass 的数据类型
 */
public interface IDataListener<M> {

    void onSuccess(M m);


    void onFail();

}

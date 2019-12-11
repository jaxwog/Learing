package com.love.jax.http.jsondeal;

import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.love.jax.http.interfaces.IDataListener;
import com.love.jax.http.interfaces.IHttpListener;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import android.os.Handler;

/**
 * com.love.jax.http.jsondeal
 * Created by jax on 2019-12-03 17:19
 * TODO:处理json类型的网络返回数据内容,数据需要切换到主线程
 */
public class JsonDealLisener<M> implements IHttpListener {

    private Class<M> responceClass;//持有返回的类型
    private IDataListener<M> dataListener;//持有接口进行回调

    /**
     * 获取主线程的Handle
     * 通过handle切换至主线程
     */
    Handler mHandler = new Handler(Looper.getMainLooper());


    /**
     * 构建时候把参数传入进来
     * @param responceClass 网络成功返回结果需要的M对象类型
     * @param dataListener 持有dataListener接口，成功后进行回调，在调用层面进行处理
     */
    public JsonDealLisener(Class<M> responceClass, IDataListener<M> dataListener) {
        this.responceClass = responceClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(HttpEntity httpEntity) {
        InputStream inputStream = null;
        try {
            //得到网络数据内容，把内容转换为String类型
            inputStream = httpEntity.getContent();
            String content = getContent(inputStream);
            //将String类型转换为调用层需要的M对象类型
            final M responce= JSON.parseObject(content,responceClass);
            //通过Handler把run方法放入到主线程执行，切换线程
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onSuccess(responce);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            dataListener.onFail();
        }


    }

    /**
     * 把inputstream类型转换为String类型
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        String content=null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

            } catch (IOException e) {

                System.out.println("Error=" + e.toString());
                dataListener.onFail();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {

                    System.out.println("Error=" + e.toString());

                }
            }
            //网络数据读取成功，返回读取成功的String类型
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            dataListener.onFail();
        }
        return content;
    }

    @Override
    public void onFail() {
        dataListener.onFail();
    }

    @Override
    public void addHttpHeader(Map<String, String> headerMap) {

    }
}

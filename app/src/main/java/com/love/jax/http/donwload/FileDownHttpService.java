package com.love.jax.http.donwload;

import android.util.Log;

import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * com.love.jax.http.donwload
 * Created by jax on 2019-12-05 10:13
 * TODO:获取网络，拼接网络请求参数
 */
public class FileDownHttpService implements IHttpService {

    /**
     * 即将添加到请求头的信息
     */
    private Map<String, String> headerMap = Collections.synchronizedMap(new HashMap<String,
            String>());

    /**
     * 含有请求处理的 接口
     */
    private IHttpListener httpListener;

    private HttpClient mHttpClient = new DefaultHttpClient();
    private HttpGet mHttpGet;
    private String url;
    private String type;

    private byte[] requestDate;

    /**
     * httpClient获取网络的回调
     */
    private HttpRespnceHandler httpRespnceHandler = new HttpRespnceHandler();

    /**
     * 增加方法
     */
    private AtomicBoolean pause = new AtomicBoolean(false);

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void excute() {

        mHttpGet = new HttpGet(url);
        constrcutHeader();
        try {
            mHttpClient.execute(mHttpGet, httpRespnceHandler);
        } catch (IOException e) {
            httpListener.onFail();
        }

    }

    private void constrcutHeader() {
        Iterator iterator = headerMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = headerMap.get(key);
            Log.i("jax", " 请求头信息  " + key + "  value " + value);
            mHttpGet.addHeader(key, value);
        }
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestDate = requestData;
    }

    @Override
    public void setRequestType(String type) {
        this.type = type;
    }

    @Override
    public Map<String, String> getHttpHeadMap() {
        return headerMap;
    }

    @Override
    public boolean cancle() {
        return false;
    }

    @Override
    public boolean isCancle() {
        return false;
    }

    @Override
    public void pause() {
        pause.compareAndSet(false, true);
    }

    @Override
    public boolean isPause() {
        return pause.get();
    }


    private class HttpRespnceHandler extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            //响应吗
            int code = response.getStatusLine().getStatusCode();
            //断点续传用206，正常请求200
            if (code == 200 || code == 206) {
                httpListener.onSuccess(response.getEntity());
            } else {
                httpListener.onFail();
            }

            return null;
        }
    }

}

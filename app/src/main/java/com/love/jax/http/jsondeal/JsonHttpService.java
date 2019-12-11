package com.love.jax.http.jsondeal;

import com.love.jax.http.interfaces.IHttpListener;
import com.love.jax.http.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.IOException;
import java.util.Map;


/**
 * com.love.jax.http.jsondeal
 * Created by jax on 2019-12-03 18:40
 * TODO:获取网络
 */
public class JsonHttpService implements IHttpService {

    private IHttpListener httpListener;//持有处理网络返回数据的接口

    private HttpClient mHttpClient=new DefaultHttpClient();
    private HttpPost mHttpPost;
    private HttpGet mHttpGet;
    private String url;

    private byte[] requestDate;
    private String type;

    /**
     * httpClient获取网络的回调
     */
    private HttpRespnceHandler httpRespnceHandler=new HttpRespnceHandler();


    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void excute() {
        if ("post".equals(type)){
        mHttpPost = new HttpPost(url);
        ByteArrayEntity byteArrayEntity=new ByteArrayEntity(requestDate);
        mHttpPost.setEntity(byteArrayEntity);
            try {
                mHttpClient.execute(mHttpPost,httpRespnceHandler);
            } catch (IOException e) {
                httpListener.onFail();
            }
        }else if ("get".equals(type)){
            mHttpGet = new HttpGet(url);
            try {
                mHttpClient.execute(mHttpGet,httpRespnceHandler);
            } catch (IOException e) {
                httpListener.onFail();
            }
        }else {
            httpListener.onFail();
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
        return null;
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

    }

    @Override
    public boolean isPause() {
        return false;
    }


    private class HttpRespnceHandler extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            //响应码
            int code=response.getStatusLine().getStatusCode();
            if(code==200) {
                httpListener.onSuccess(response.getEntity());
            }else {
                httpListener.onFail();
            }
            return null;
        }
    }
}

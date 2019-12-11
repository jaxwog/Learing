package com.love.jax.http.jsondeal;

import java.util.List;

/**
 * com.love.jax.http.jsondeal
 * Created by jax on 2019-12-03 19:03
 * TODO:文本形式请求返回数据
 */
public class ContentBean {

    /**
     * code : 0
     * msg : success
     * data : ["泰康人寿","微回执","微医保","退保","意外险","惠健康","保单补发","车险","贴心保","智赢人生"]
     */

    private int code;
    private String msg;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return " 热门词组 = " + data ;
    }
}

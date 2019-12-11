package com.love.jax.http.donwload.enums;

/**
 * com.love.jax.http.donwload
 * Created by jax on 2019-12-05 10:02
 * TODO:枚举返回当前下载的状态
 */
public enum DownloadStatus {
    //等待状态
    waitting(0),
    //开始状态
    starting(1),
    //下载状态
    downloading(2),
    //暂停状态
    pause(3),
    //完成状态
    finish(4),
    //失败状态
    failed(5);


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
    private DownloadStatus(int value) {
        this.value=value;
    }
}

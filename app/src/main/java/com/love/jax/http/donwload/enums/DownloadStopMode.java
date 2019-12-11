package com.love.jax.http.donwload.enums;

/**
 * com.love.jax.http.donwload.enums
 * Created by jax on 2019-12-05 22:46
 * TODO:暂停任务（1、手动暂停； 0、自动暂停）
 * 自动暂停需要恢复下载
 */
public enum DownloadStopMode {
    /**
     * 后台根据下载优先级调度自动停止下载任务
     */
    auto(0),

    /**
     * 手动停止下载任务
     */
    hand(1);

    DownloadStopMode(Integer value) {
        this.value = value;
    }

    /**
     * 值
     */
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static DownloadStopMode getInstance(int value) {
        for (DownloadStopMode mode : DownloadStopMode.values()) {
            if (mode.getValue() == value) {
                return mode;
            }
        }
        return DownloadStopMode.auto;
    }
}

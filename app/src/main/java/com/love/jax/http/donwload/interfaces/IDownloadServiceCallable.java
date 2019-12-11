package com.love.jax.http.donwload.interfaces;

import com.love.jax.http.donwload.DownloadItemInfo;

/**
 * com.love.jax.http.donwload.interfaces
 * Created by jax on 2019-12-05 10:42
 * TODO:处理回调给调用层的信息
 */
public interface IDownloadServiceCallable {

    //下载状态发生改变
    void onDownloadStatusChanged(DownloadItemInfo downloadItemInfo);

    //接收到的总长度
    void onTotalLengthReceived(DownloadItemInfo downloadItemInfo);

    /**
     * 当前下载的长度发生改变
     * @param downloadItemInfo
     * @param downLenth 下载的长度
     * @param speed 下载的速度
     */
    void onCurrentSizeChanged(DownloadItemInfo downloadItemInfo, double downLenth, long speed);

    //下载成功
    void onDownloadSuccess(DownloadItemInfo downloadItemInfo);

    //暂停下载
    void onDownloadPause(DownloadItemInfo downloadItemInfo);

    /**
     * 下载出错
     * @param downloadItemInfo
     * @param var2 状态码
     * @param var3 文字描述
     */
    void onDownloadError(DownloadItemInfo downloadItemInfo, int var2, String var3);
}

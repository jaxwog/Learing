package com.love.jax.imageloader.request;

import android.widget.ImageView;

import com.love.jax.imageloader.config.DisplayConfig;
import com.love.jax.imageloader.core.SimpleImageLoader;
import com.love.jax.imageloader.policy.ILoadPolicy;
import com.love.jax.imageloader.utils.MD5Utils;

import java.lang.ref.SoftReference;

/**
 * com.love.jax.imageloader.request
 * Created by jax on 2019-12-13 15:35
 * TODO:每个图片地址生成一个请求，添加到请求队列RequestQueue中
 * 把请求图片信息封装成一个BitmapRequest对象，作为一个处理单位
 * 实现Comparable接口，通过加载策略进行返回（通过重写HashCode和equals方法来实现）
 */
public class BitmapRequest implements Comparable<BitmapRequest> {

    //加载策略
    private ILoadPolicy loadPolicy = SimpleImageLoader.getObject().getConfig().getLoadPolicy();
    //序列号
    private int serialNO;

    //图片控件，持有ImageView的软引用
    //当系统内存不足时，把引用的对象进行回收
    private SoftReference<ImageView> mimageViewRef;
    //图片路径
    private String imageUri;
    //MD5的图片路径
    private String imageUriMD5;

    private DisplayConfig displayConfig =
            SimpleImageLoader.getObject().getConfig().getDisplayConfig();
    public SimpleImageLoader.ImageListener imageListener;

    /**
     * 构建对象
     * @param imageView 要显示的控件
     * @param uri 图片网络或者本地路径
     * @param config 图片加载的配置信息
     * @param imageListener 图片加载成功回调接口
     */
    public BitmapRequest(ImageView imageView, String uri, DisplayConfig config,
                         SimpleImageLoader.ImageListener imageListener) {
        this.mimageViewRef = new SoftReference<ImageView>(imageView);
        //设置可见的ImageView的tag为，要下载的图片路径
        imageView.setTag(uri);
        this.imageUri = uri;
        //将图片路径转换为MD5值，防止图片地址信息存在乱码下载报错
        this.imageUriMD5 = MD5Utils.toMD5(imageUri);
        if (config != null) {
            this.displayConfig = config;
        }
        this.imageListener = imageListener;
    }


    //优先级的确定
    @Override
    public int compareTo(BitmapRequest another) {
        //通过加载器（优先级）策略进行返回
        return loadPolicy.compareTo(this, another);
    }


    /**
     * 设置序列号
     *
     * @param serialNO
     */
    public void setSerialNO(int serialNO) {
        this.serialNO = serialNO;
    }

    public int getSerialNO() {
        return serialNO;
    }

    public ImageView getImageView() {
        return mimageViewRef.get();
    }

    public String getImageUri() {
        return imageUri;
    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + serialNO;
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitmapRequest that = (BitmapRequest) o;

        if (serialNO != that.serialNO) return false;
        return loadPolicy != null ? loadPolicy.equals(that.loadPolicy) : that.loadPolicy == null;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }
}

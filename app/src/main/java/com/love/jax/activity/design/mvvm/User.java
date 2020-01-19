package com.love.jax.activity.design.mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.love.jax.R;
import com.love.jax.imageloader.cache.DoubleCache;
import com.love.jax.imageloader.config.ImageLoaderConfig;
import com.love.jax.imageloader.core.SimpleImageLoader;
import com.love.jax.imageloader.policy.ReversePolicy;

/**
 * com.love.jax.activity.design.mvvm
 * Created by jax on 2020-01-15 18:27
 * TODO:mvvm用户数据与视图进行绑定
 * 1、继承自android.databinding.BaseObservable
 * 2、设置参数时候通过方法 notifyPropertyChanged(BR.name)来进行View更新
 * 3、获取参数通过注解 @Bindable
 * 4、设置图片时候通过注解 @BindingAdapter("bind:header") bind标记的是名字
 *    在XML中设置ImageView的参数 app:header="@{user.header}"，注解的方法必须为static类型
 */
public class User extends BaseObservable {
    private String name;

    private String password;

    private String header;


    private static void findImage(ImageView view, String url){
        //配置
        ImageLoaderConfig.Builder build = new ImageLoaderConfig.Builder();
        build.setThreadCount(3) //线程数量
                .setLoadPolicy(new ReversePolicy()) //加载策略
                .setCachePolicy(new DoubleCache(view.getContext())) //缓存策略
                .setLoadingImage(R.mipmap.loading)
                .setFaildImage(R.mipmap.not_found);

        ImageLoaderConfig config = build.build();
        //初始化SimpleImageLoader，包含RequestQueue队列的初始化
        SimpleImageLoader imageLoader;
        imageLoader = SimpleImageLoader.getInstance(config);
        imageLoader.displayImage(view, url);
    }


    public User(String name, String password, String header) {
        this.name = name;
        this.password = password;
        this.header = header;
    }


    @BindingAdapter("bind:header")
    public static void getImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
//        findImage(view,url);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}

package com.love.jax.bean;

/**
 * com.love.jax.bean
 * Created by jax on 2018/11/13 23:03
 * TODO: 首页功能
 */
public class MainFuncEntity {
    public MainFuncEntity() {

    }

    //标题
    private String title;
    //资源id
    private int iconId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}

package com.love.jax.activity.design.mvp.view;

import com.love.jax.bean.Girl;

import java.util.List;

/**
 * com.love.jax.activity.design.mvp.view
 * Created by jax on 2020-01-14 10:11
 * TODO: 抽象的UI层
 * 粒度太小，接口陡然增多
 * 粒度太大，解耦效果不好（单一原则）
 */
public interface IGrilView {
    /**
     * UI业务逻辑   加载进度条
     */
    void showLoading();

    /**
     * 数据加载完成  ，显示UI
     * @param grils  数据内容
     */
    void showGrils(List<Girl> grils);
}

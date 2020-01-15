package com.love.jax.activity.design.mvp.model;

import com.love.jax.bean.Girl;

import java.util.List;

/**
 * com.love.jax.activity.design.mvp.model
 * Created by jax on 2020-01-14 10:14
 * TODO:监听数据返回
 */
public interface IGirlModel {
    void loadGirl(GirlOnLoadlitener girlOnLoadlitener);

    interface GirlOnLoadlitener {
        void onComplete(List<Girl> girls);
    }
}

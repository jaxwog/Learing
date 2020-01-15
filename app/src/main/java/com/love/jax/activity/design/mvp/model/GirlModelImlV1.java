package com.love.jax.activity.design.mvp.model;

import android.os.Handler;
import android.os.Looper;

import com.love.jax.R;
import com.love.jax.bean.Girl;

import java.util.ArrayList;
import java.util.List;

/**
 * com.love.jax.activity.design.mvp.model
 * Created by jax on 2020-01-14 10:16
 * TODO:模型层
 */
public class GirlModelImlV1 implements IGirlModel {
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void loadGirl(final GirlOnLoadlitener girlOnLoadlitener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final List<Girl> data = new ArrayList<Girl>();
                data.add(new Girl(R.drawable.f1, "五颗星", "佰仟媚儿初夏新款韩版时尚潮流女个性字母印花无袖露脐上衣"));
                data.add(new Girl(R.drawable.f2, "四颗星", "迷依诗诺夏天新款韩版女装性感夜店欧美风字母印花牛仔露脐背心上衣"));
                data.add(new Girl(R.drawable.f3, "五颗星", "迷依诗诺春夏欧美新款性感女装单排扣牛仔背心露脐上衣"));
                data.add(new Girl(R.drawable.f4, "三颗星", "美莉丹 新款欧美单排扣牛仔背心露脐上衣"));
                data.add(new Girl(R.drawable.f5, "五颗星", "夏季新款韩版时尚个性字母无袖露脐上衣"));
                data.add(new Girl(R.drawable.f6, "三颗星", "新款欧美单排扣牛仔背心露脐上衣"));
                data.add(new Girl(R.drawable.f7, "四颗星", "夏装新款下摆波浪边挂脖露肩"));
                data.add(new Girl(R.drawable.f8, "五颗星", "夏天新款欧美时尚潮流休闲百"));
                data.add(new Girl(R.drawable.f9, "四颗星", "迷依诗诺夏季新款小香风甜美性感夜"));
                data.add(new Girl(R.drawable.f11, "三颗星", "安巴克夏季新款韩版时尚套装性感"));
                data.add(new Girl(R.drawable.f12, "五颗星", "夏季新款韩版时尚个性字母无袖露脐上衣"));
                data.add(new Girl(R.drawable.f13, "三颗星", "新款欧美单排扣牛仔背心露脐上衣"));
                data.add(new Girl(R.drawable.f14, "四颗星", "夏装新款下摆波浪边挂脖露肩"));
                data.add(new Girl(R.drawable.f15, "五颗星", "夏天新款欧美时尚潮流休闲百"));
                data.add(new Girl(R.drawable.f16, "四颗星", "迷依诗诺夏季新款小香风甜美性感夜"));
                data.add(new Girl(R.drawable.f17, "三颗星", "安巴克夏季新款韩版时尚套装性感"));
                data.add(new Girl(R.drawable.f18, "五颗星", "佰仟媚儿初夏新款韩版时尚潮流女个性字母印花无袖露脐上衣"));
                data.add(new Girl(R.drawable.f19, "四颗星", "迷依诗诺夏天新款韩版女装性感夜店欧美风字母印花牛仔露脐背心上衣"));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //回调
                        girlOnLoadlitener.onComplete(data);
                    }
                });
            }
        }.start();

    }
}

package com.love.jax.activity.design.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.love.jax.activity.design.mvp.present.BasePersenter;

/**
 * com.love.jax.activity.design.mvp
 * Created by jax on 2020-01-14 09:49
 * TODO:接管mvp的声明周期方法
 * V  IGrilView接口
 */
public abstract class BaseMVPActivty<V,T extends BasePersenter<V>>  extends AppCompatActivity {

    protected  T mPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent=createPresent();
        mPresent.attachView((V) this);
    }



    @Override
    protected void onDestroy() {
            mPresent.detach();
        super.onDestroy();

    }

    /**
     * 子类实现具体的构建过程
     * @return
     */
    protected abstract T createPresent() ;

}

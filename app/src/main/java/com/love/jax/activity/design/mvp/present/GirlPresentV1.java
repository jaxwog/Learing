package com.love.jax.activity.design.mvp.present;

import com.love.jax.activity.design.mvp.model.GirlModelImlV1;
import com.love.jax.activity.design.mvp.model.IGirlModel;
import com.love.jax.activity.design.mvp.view.IGrilView;
import com.love.jax.bean.Girl;

import java.util.List;

/**
 * com.love.jax.activity.design.mvp.present
 * Created by jax on 2020-01-14 10:25
 * TODO:具体的present层 ，操作的是IGrilView接口，并持有model的引用
 */
public class GirlPresentV1<T> extends BasePersenter<IGrilView> {

    /**
     * 持有视图层 UI接口的引用  此时的视图层Activity
     */
    IGrilView mGrilView;

    public GirlPresentV1(IGrilView mGrilView) {
        this.mGrilView = mGrilView;
    }


    /**
     * 持有模型Model的引用
     */
    IGirlModel girlModel = new GirlModelImlV1();


    @Override
    public void fectch() {
        mGrilView.showLoading();
        if (girlModel != null) {
            /**
             * 参数为数据回调监听
             */
            girlModel.loadGirl(new IGirlModel.GirlOnLoadlitener() {
                @Override
                public void onComplete(List<Girl> girls) {
                    //回调视图层
                    mGrilView.showGrils(girls);
                }
            });
        }
    }
}

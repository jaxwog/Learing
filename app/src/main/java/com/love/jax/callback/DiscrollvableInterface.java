package com.love.jax.callback;

/**
 * com.love.jax.callback
 * Created by jax on 2019/1/31 17:14
 * TODO:实现ScrollView滑动监听，处理属性动画
 * 在外面包裹的View里面实现，在ScrollView中调用该方法
 */
public interface DiscrollvableInterface {

    /**
     * 当滑动的时候调用该方法，用来控制里面的控件执行相应的动画
     * @param ratio 0-1,滑动的百分比
     */
    public void onDiscrollve(float ratio);

    /**
     * 重置view的属性----恢复view的原来属性
     */
    public void onResetDiscrollve();
}

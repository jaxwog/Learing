package com.love.jax.activity.animation;


import android.annotation.SuppressLint;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class OptionsTransition2Activity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.bt)
    Button bt;


    @Override
    protected void initJestListener() {
        animaH();
    }

    @SuppressLint("NewApi")
    private void animaH(){
        //三种系统带的：滑动效果(Slide)、展开效果Explode、渐变显示隐藏效果Fade
//        Slide slide = new Slide();
//        slide.setDuration(300);
//        getWindow().setExitTransition(slide);//出去的动画
//        getWindow().setEnterTransition(slide);//进来的动画

//        Explode explode = new Explode();
//        explode.setDuration(300);
//        getWindow().setExitTransition(explode);//出去的动画
//        getWindow().setEnterTransition(explode);//进来的动画


        Fade fade = new Fade();
        fade.setDuration(300);
        getWindow().setExitTransition(fade);//出去的动画
        getWindow().setEnterTransition(fade);//进来的动画
    }

    @Override
    protected void initFlag() {
        super.initFlag();
        //设置允许使用转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_options_transition2;
    }



    @OnClick({R.id.iv1, R.id.bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv1:
                break;
            case R.id.bt:
                jump();
                break;
        }
    }
    //按返回键的时候自动实现了返回的共享元素转场动画
    private void jump() {
       this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

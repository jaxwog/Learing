package com.love.jax.activity.animation;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 相同元素的转场动画
 */
public class OptionsTransitionActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.iv1)
    ImageView iv1;

    @Override
    protected void initJestListener() {

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
        return R.layout.activity_options_transition;
    }



    @OnClick({R.id.bt, R.id.iv1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt:
                jump();
                break;
            case R.id.iv1:
                break;
        }
    }

    @SuppressLint("NewApi")
    private void jump() {
        // -----------------------------------左进右出-----------------------------------
//        startActivity(new Intent(mContext, OptionsTransition2Activity.class));
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

// -----------------------------------注释-----------------------------------
//        ActivityOptions  //不用考虑兼容性问题，最小支持到sdk21
//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//        .makeSceneTransitionAnimation(
//            activity, //当前的Activity
//            sharedElement,//共享元素---哪一个View
//        sharedElementName)//共享元素的名称 android:transitionName="yingjia"


        // -----------------------------------图片转场动画-----------------------------------
//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(this, iv1, "yingjia");
//        Intent intent = new Intent(this, OptionsTransition2Activity.class);
//        startActivity(intent, optionsCompat.toBundle());


        // -----------------------------------多个共享元素转场动画-----------------------------------
//        new Pair<>(first, second)
//        Pair<View ,String > pair1 = new Pair<>(iv1,"vi1");
//        Pair<View ,String > pair2 = new Pair<>(bt,"bt");
//        ActivityOptionsCompat	 optionsCompat = ActivityOptionsCompat
////            .makeSceneTransitionAnimation(this, Pair.create(iv1, "iv1"),Pair.create(bt, "bt"));
//         .makeSceneTransitionAnimation(this, pair1,pair2);
//        Intent intent = new Intent(this, OptionsTransition2Activity.class);
//        startActivity(intent, optionsCompat.toBundle());


        // -----------------------------------普通的转换动画（只有API 21才有下面自带效果）-----------------------------------
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


       //如果有共享元素，可以设置共享元素，那么它就会按照共享元素动画执行，其他的子view就会按照Fade动画执行。
//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,iv1, "yingjia");

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, OptionsTransition2Activity.class);
        startActivity(intent, optionsCompat.toBundle());



    }


}

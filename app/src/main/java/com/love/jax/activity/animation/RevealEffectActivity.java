package com.love.jax.activity.animation;


import android.animation.Animator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MaterialDesign动画：揭露效果、水波纹效果
 *      ViewAnimationUtils.createCircularReveal(
        view, //作用在哪个View上面
        centerX, centerY, //扩散的中心点
        startRadius, //开始扩散初始半径
        endRadius)//扩散结束半径
 */
public class RevealEffectActivity extends BaseActivity {


    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_reveal_effect;
    }



    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                @SuppressLint({"NewApi", "LocalSuppress"})
                Animator animator = ViewAnimationUtils.createCircularReveal(btn1,
                        btn1.getWidth()/2,btn1.getHeight()/2,0,btn1.getHeight());
                animator.setDuration(2000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();

                break;
            case R.id.btn2:
                @SuppressLint({"NewApi", "LocalSuppress"})
                Animator animator2 = ViewAnimationUtils.createCircularReveal(btn2,
                        0,0,0, (float) Math.hypot(btn2.getWidth(),btn2.getHeight()));
                //Math.hypot 直角三角形的斜边
                animator2.setDuration(2000);
                animator2.setInterpolator(new AccelerateInterpolator());
                animator2.start();
                break;
        }
    }
}

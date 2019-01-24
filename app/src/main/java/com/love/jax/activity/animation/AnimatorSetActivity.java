package com.love.jax.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;

/**
 *     属性动画集合
 * 1）translationX 和 translationY：这两个属性控制了View所处的位置，它们的值是由layout容器设置的，是相对于坐标原点（0，0左上角）的一个偏移量。
 * 2）rotation, rotationX 和 rotationY：控制View绕着轴点（pivotX和pivotY）旋转。
 * 3）scaleX 和 scaleY：控制View基于pivotX和pivotY的缩放。
 * 4）pivotX 和 pivotY：旋转的轴点和缩放的基准点，默认是View的中心点。
 * 5）x 和 y：描述了view在其父容器中的最终位置，是左上角左标和偏移量（translationX，translationY）的和。
 * 6）aplha：透明度，1是完全不透明，0是完全透明。
 */
public class AnimatorSetActivity extends BaseActivity {


    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.first)
    LinearLayout firstView;
    @BindView(R.id.second)
    ImageView secondView;

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
        return R.layout.activity_animator_set;
    }

    /**
     *  触摸按钮
     *  执行动画：1、翻转 2、透明度 3、缩放 4、执行完翻转之后再翻转到正常状态 5、平移
     */
    public void startFirstAnimation(View v){
        //1、翻转
        ObjectAnimator firstRotationAnim = ObjectAnimator.ofFloat(firstView,"rotationX",0f,25f);
        firstRotationAnim.setDuration(200);
//        firstRotationAnim.start();
        //2、透明度
        ObjectAnimator firstAlphaAnim = ObjectAnimator.ofFloat(firstView,"alpha",1f,0.5f);
        firstAlphaAnim.setDuration(300);
        //3、x缩放
        ObjectAnimator firstScaleXAnim = ObjectAnimator.ofFloat(firstView,"scaleX",1f,0.8f);
        firstScaleXAnim.setDuration(300);
        //3、y缩放
        ObjectAnimator firstScaleYAnim = ObjectAnimator.ofFloat(firstView,"scaleY",1f,0.8f);
        firstScaleYAnim.setDuration(300);
        //4、执行完翻转之后再翻转到正常状态
        ObjectAnimator firstResumeRotationAnim = ObjectAnimator.ofFloat(firstView,"rotationX",25f,0f);
        firstResumeRotationAnim.setDuration(200);
        //等到翻转动画执行完成后，再执行回归
        firstResumeRotationAnim.setStartDelay(200); //延迟执行
        //5、平移 ------由于缩放造成了离顶部有一段距离，需要平移上去
        ObjectAnimator firstTranslationAnim = ObjectAnimator.ofFloat(firstView,"translationY",0f,-0.1f*firstView.getHeight());
        firstTranslationAnim.setDuration(200);
        //第二个View执行动画平移
        ObjectAnimator secondTranslationAnim = ObjectAnimator.ofFloat(secondView,"translationY",secondView.getHeight(),0f);
        secondTranslationAnim.setDuration(200);
        secondTranslationAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                secondView.setVisibility(View.VISIBLE);
                bt.setClickable(false);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(firstRotationAnim,firstAlphaAnim,firstScaleXAnim,firstScaleYAnim,firstResumeRotationAnim,firstTranslationAnim,secondTranslationAnim);
        set.start();



    }

    public void startSecondAnimation(View v){

        //1.翻转
        ObjectAnimator firstRotationAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f,25f);
        firstRotationAnim.setDuration(300);
        //2.透明度
        ObjectAnimator firstAlphaAnim = ObjectAnimator.ofFloat(firstView, "alpha",0.5f, 1f);
        firstAlphaAnim.setDuration(200);
        //3.缩放动画
        ObjectAnimator firstScaleXAnim = ObjectAnimator.ofFloat(firstView, "scaleX",0.8f, 1f);
        firstScaleXAnim.setDuration(300);
        ObjectAnimator firstScaleYAnim = ObjectAnimator.ofFloat(firstView, "scaleY",0.8f, 1f);
        firstScaleYAnim.setDuration(300);
        //改正向旋转设置监听，执行完毕后再执行反向旋转
        ObjectAnimator firstResumeRotationAnim = ObjectAnimator.ofFloat(firstView, "rotationX",25f, 0f);
        firstResumeRotationAnim.setDuration(200);
        firstResumeRotationAnim.setStartDelay(200);//延迟执行
        //由于缩放造成了离顶部有一段距离，需要平移上去
        ObjectAnimator firstTranslationAnim = ObjectAnimator.ofFloat(firstView, "translationY", -0.1f*firstView.getHeight(),0f);
        firstTranslationAnim.setDuration(200);
        //第二个View执行平移动画--网上平移
        ObjectAnimator secondeTranslationAnim = ObjectAnimator.ofFloat(secondView, "translationY", 0f,secondView.getHeight());
        secondeTranslationAnim.setDuration(300);
        secondeTranslationAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                secondView.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                bt.setClickable(true);
            }

        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(firstRotationAnim,firstAlphaAnim,firstScaleXAnim,firstScaleYAnim,firstResumeRotationAnim,firstTranslationAnim,secondeTranslationAnim);
        set.start();

    }

}

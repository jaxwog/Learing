package com.love.jax.activity.animation;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;

/**
 * 属性动画效果
 */
public class ArrtAnimatorActivity extends BaseActivity {


    @BindView(R.id.button1)
    ImageView button1;

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
        return R.layout.activity_arrt_animator;
    }

    int position = 0;

    //处理监听事件
    public void startAnima(View v) {
//-------------------------------------------------------------------------------------------------
        //补间动画（View动画）
//      Animation animation = AnimationUtils.loadAnimation(this, R.animator.z_translation);
//     animation.start();
//     v.startAnimation(animation);
//-------------------------------------------------------------------------------------------------
        //属性动画
//    position += 100;
//    v.setTranslationX(position);
//    v.setAlpha((float)Math.random());

        /**
         * float... values: A set of values that the animation will animate between over time.
         */
//        ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationX", 0f, 300f);
//        oa.setDuration(500);
//        oa.start();
//        ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationY", 0f, 300f);
//        oa.setDuration(500);
//        oa.start();
//        ObjectAnimator oa = ObjectAnimator.ofFloat(v, "rotationX", 0f, 360f);
//        oa.setDuration(500);
//        oa.start();

//二、----------------------------------多个动画同时执行---------------------------------------------------
        //方法1：设置动画监听，开始第一个动画同时开启其他动画
        //没有这个属性的时候，就是valueanimator
//        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "hana", 0f, 100f);
//        animator.setDuration(1000);
//        //设置动画监听
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //动画在执行的过程当中，不断地调用此方法
////              animation.getAnimatedFraction()//百分比
//                //得到duration时间内 values当中的某一个中间值。0f~100f
//                float value = (float) animation.getAnimatedValue();//
//                button1.setScaleX(0.5f + value / 200);//0.5~1
//                button1.setScaleY(0.5f + value / 200);//0.5~1
//            }
//        });
//        animator.start();
//
//        animator.addListener(new Animator.AnimatorListener() {
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                // TODO Auto-generated method stub
//
//            }
//        });

//-------------------------------------------------------------------------------------------------
        //2)方法2：用ValueAnimator
//        ValueAnimator animator = ValueAnimator.ofFloat(0f, 200f);
//        animator.setDuration(1000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //动画在执行的过程当中，不断地调用此方法
//                animation.getAnimatedFraction();//百分比
//                //得到duration时间内 values当中的某一个中间值。0f~100f
//                float value = (float) animation.getAnimatedValue();//
//                button1.setScaleX(0.5f + value / 200);//0.5~1
//                button1.setScaleY(0.5f + value / 200);//0.5~1
//            }
//        });
//        animator.start();

//-------------------------------------------------------------------------------------------------
//        3）方法3
        //float... values:代表关键帧的值
//        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.7f, 1f);
//        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.7f, 1f);
//        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.7f, 1f);
////      PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("translationX", 0f,300f);
//
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(button1, holder1,
// holder2, holder3);
//        animator.setDuration(1000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                float animatedValue = (float) animation.getAnimatedValue();
//                float animatedFraction = animation.getAnimatedFraction();
//                long playTime = animation.getCurrentPlayTime();
//
//               Logger.i("animation","animatedValue:" + animatedValue + ",  playTime:" + playTime);
//            }
//        });
//        animator.start();

//-------------------------------------------------------------------------------------------------
        //4)方法4
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(button1, "alpha", 1f, 0.7f, 1f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(button1, "scaleX", 1f, 0.7f, 1f);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(button1, "scaleY", 1f, 0.7f, 1f);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(1000);
////      animatorSet.play(animator1);//执行当个动画
////      animatorSet.playTogether(animator1,animator2,animator3);//同时执行
//        animatorSet.playSequentially(animator1, animator2, animator3);//依次执行动画
//        animatorSet.start();

//---------------------------------自定义估值器实现抛物线效果---------------------------------------------------
//        ValueAnimator animator = new ValueAnimator();
//
//        animator.setDuration(15000);
//        animator.setObjectValues(new PointF(0,0));
//
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //得到该点的坐标
//                PointF pointF = (PointF) animation.getAnimatedValue();
//                //分别设置X、Y轴移动距离
//                button1.setX(pointF.x);
//                button1.setY(pointF.y);
//            }
//        });
//
//        //动画开始后不断调用该方法进行计算，然后回调监听方法进行动画效果
//        animator.setEvaluator(new TypeEvaluator<PointF>() {
//            @Override
//            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
//
//                PointF pointF = new PointF();
//                pointF.x = 30f * (fraction* 15f); //初始速度*(执行的百分比*4)
//                pointF.y = 0.5f * 9.8f * (fraction* 15f) * (fraction* 15f);
//                return pointF;
//            }
//
//        });
//        animator.start();

//-------------------------------------------------------------------------------------------------
        //插值器使用
        ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationY", 100f, 1000f);
//        oa.setInterpolator(new AccelerateInterpolator()); // 加速插值器
//        oa.setInterpolator(new AccelerateDecelerateInterpolator());// 加速减速插值器
//        oa.setInterpolator(new AnticipateInterpolator());//回荡秋千插值器
//        oa.setInterpolator(new AnticipateOvershootInterpolator());//先回荡秋千后反力插值器
//        oa.setInterpolator(new BounceInterpolator());//弹跳插值器插值器
//        oa.setInterpolator(new CycleInterpolator(1));// 正弦曲线插值器
//        oa.setInterpolator(new DecelerateInterpolator());//减速插值器
        oa.setInterpolator(new OvershootInterpolator()); //末尾反力插值器
        oa.setDuration(5000);
        oa.start();






    }




}

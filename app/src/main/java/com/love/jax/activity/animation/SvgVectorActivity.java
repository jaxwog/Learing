package com.love.jax.activity.animation;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

/**
 *  SVG矢量动画效果
 *  在21以下生成png图片
 */
public class SvgVectorActivity extends BaseActivity {



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
        return R.layout.activity_svg_vector;
    }


    //对号转错号动画
    public void anim1(View v){
        ImageView iv = (ImageView) v;
        Drawable drawable = iv.getDrawable();
        if(drawable instanceof Animatable){
            ((Animatable)drawable).start();

        }
    }

    //星星转勺子
    public void anim2(View v){
        ImageView iv = (ImageView) v;
        Drawable drawable = iv.getDrawable();
        if(drawable instanceof Animatable){
            ((Animatable)drawable).start();

        }
    }
}

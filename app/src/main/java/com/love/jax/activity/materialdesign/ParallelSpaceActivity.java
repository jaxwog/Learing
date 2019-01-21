package com.love.jax.activity.materialdesign;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.activity.fragment.TranslateFragment;
import com.love.jax.adapter.WelcompagerTransformer;
import com.love.jax.utils.Logger;

import butterknife.BindView;

/**
 * 平行空间效果
 *
 * 	1.布局：RelativeLayout+ViewPager
 2.动画：背景的两个动画，
 1）一进来就执行一个平移动画；---属性动画 view.setTranslationX();
 2)平移完后，执行翻转动画；(手指滑动)
 3）背景颜色渐变（绿色-->蓝色）颜色估值器
 @see android.animation.ArgbEvaluator

 监听viewpager的滑动。--->滑动的百分比。

 3.如何做到手机壳里面的view滑动的时候不会出现在手机壳外面。
 办法很多：1）自定义Drawable（滑动的时候不断地将两张图片剪切并且拼接成一个Drawable。）
 2）HorizontalScrollView + 两张拼在一起的图片
      @see com.love.jax.view.PercentLayoutHelper
 HorizontalScrollView 和手机壳的宽高一致。如何做到？相对于屏幕的宽高决定自己的宽高。
 用百分比布局。官方谷歌的百分比布局：只能相对于屏幕的宽度和高度
 layout_width=50%w
 layout_height=50%h
 这样会造成图片变形。所以只能自定义百分比布局(可以在谷歌的版本上直接修改少量代码。)
 layout_width=50%w
 layout_height=70%w
 */
public class ParallelSpaceActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;

    private int[] layouts = {
            R.layout.welcome11,
            R.layout.welcome2,
            R.layout.welcome3 };

    private WelcompagerTransformer transformer;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
//        System.out.println("offset:"+vp.getOffscreenPageLimit());
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adapter);

        transformer = new WelcompagerTransformer(this);
        vp.setPageTransformer(true, transformer);

//        vp.setOnPageChangeListener(transformer);
        vp.addOnPageChangeListener(transformer);

    }

    @SuppressLint("NewApi")
    @Override
    protected void initData() {
        getWindow().setNavigationBarColor(getResources().getColor(R.color.bg1_green));
        getWindow().setStatusBarColor(getResources().getColor(R.color.bg1_green));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_parallel_space;
    }


    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @SuppressLint("NewApi")
        @Override
        public Fragment getItem(int position) {
            Fragment f = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layouts[position]);
            bundle.putInt("pageIndex", position);
            Logger.i("position","位置"+ position);

            f.setArguments(bundle );
            return f;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }




    }
}

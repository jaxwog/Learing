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

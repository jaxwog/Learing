package com.love.jax.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 顶部显示tab
 * app:tabIndicatorColor="@color/colorPrimary_pink"//指示器的颜色
 * app:tabTextColor="@color/colorPrimary_pink"//tab的文字颜色
 * app:tabSelectedTextColor="@color/colorPrimary_pinkDark"//选中的tab的文字颜色
 * app:tabMode="fixed"//scrollable:可滑动；fixed：不能滑动，平分tabLayout宽度
 * app:tabGravity="center"// fill:tabs平均填充整个宽度;center:tab居中显示
 */
public class TabLayoutTopActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager viewPager;
    private String[] title = {"头条", "新闻", "娱乐", "体育", "科技", "美女", "财经", "汽车", "房子", "头条"};

    @Override
    protected void initJestListener() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        //双向关联如下：1 、2
        //1.TabLayout和Viewpager关联
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabUnselected(TabLayout.Tab arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 被选中的时候回调
                viewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabReselected(TabLayout.Tab arg0) {
                // TODO Auto-generated method stub

            }
        });
        //2.ViewPager滑动关联tabLayout
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));


        //设置tabLayout的标签来自于PagerAdapter
        tablayout.setTabsFromPagerAdapter(adapter);

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_tab_layout_top;
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new NewsDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title[position]);
            f.setArguments(bundle);
            return f;
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }

}

package com.love.jax.activity.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutBottomActivity extends BaseActivity {


    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    private String[] title = {
            "财经",
            "汽车",
            "房子",
            "头条"
    };

    @Override
    protected void initJestListener() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        //1.TabLayout和Viewpager关联
//		tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
//
//			@Override
//			public void onTabUnselected(Tab arg0) {
//			}
//
//			@Override
//			public void onTabSelected(Tab tab) {
//				// 被选中的时候回调
//				viewPager.setCurrentItem(tab.getPosition(),true);
//			}
//
//			@Override
//			public void onTabReselected(Tab arg0) {
//			}
//		});
//		//2.ViewPager滑动关联tabLayout
//		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//		//设置tabLayout的标签来自于PagerAdapter
//		tabLayout.setTabsFromPagerAdapter(adapter);

        viewPager.setAdapter(adapter);
        //方法二：一步到位
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
//			tab.setText(Html.toHtml(text))
            View view = View.inflate(this, R.layout.bottom_navigation, null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            ImageView imageView = view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.search_icon_fire);
//            tab.setIcon(R.drawable.search_icon_casesearch);
            tv_name.setText(title[i]);
            tab.setCustomView(view);

        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_tab_layout_bottom;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
            return title.length;
        }

    }
}

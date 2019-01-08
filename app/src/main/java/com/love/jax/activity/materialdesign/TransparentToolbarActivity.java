package com.love.jax.activity.materialdesign;

import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.callback.TranslucentListener;
import com.love.jax.view.TransparentScrollView;

import butterknife.BindView;

/**
 * ScrollView 根据滑动距离设置Toolbar透明度
 * android:clipToPadding="false" 该控件的绘制范围是否不在Padding里面。false：绘制的时候范围会考虑padding即会往里面缩进。
 * android:clipChildren="false"  子控件是否能不超出padding的区域（比如ScrollView上滑动的时候，child可以滑出该区域）
 */
public class TransparentToolbarActivity extends BaseActivity implements TranslucentListener {


    @BindView(R.id.scrollView)
    TransparentScrollView scrollView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.scrollLayout)
    RelativeLayout scrollLayout;

    int heightPix;

    @Override
    protected void initJestListener() {
        scrollView.setTranslucentListener(this);

//        scrollLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int
//                    oldLeft, int oldTop, int oldRight, int oldBottom) {
//                Logger.e("tang",bottom+" ,"+oldBottom);
//
//                if (oldBottom==0){
//                    heightPix = bottom;
//                    Logger.e("tang","屏幕高度=="+heightPix);
//                }
//
//                Rect r = new Rect();
//                //获取当前界面可视部分
//                TransparentToolbarActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
//                //获取屏幕的高度
////                int screenHeight =  InvoiceInfoActivity.this.getWindow().getDecorView().getRootView().getHeight();
//                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
//                final int heightDifference = heightPix - r.bottom;
//                Logger.i("tang","键盘高度为："+heightDifference);
//                if (oldBottom!=0 && bottom<oldBottom){
//                    scrollView.scrollTo(0,bottom);
//                    Logger.e("tang","移动了哈哈");
//                }
//
//            }
//        });
//

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
        return R.layout.activity_transparent_toolbar;
    }

    @Override
    public void onTranlucent(float alpha) {
          toolbar.setAlpha(alpha);
    }

}

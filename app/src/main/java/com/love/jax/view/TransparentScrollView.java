package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.love.jax.callback.TranslucentListener;

/**
 * com.love.jax.view
 * Created by jax on 2018/12/19 20:15
 * TODO: 设置Toolbar透明度
 */
public class TransparentScrollView extends ScrollView {

    private TranslucentListener listener;


    public void setTranslucentListener(TranslucentListener listener) {
        this.listener = listener;
    }

    public TransparentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null){
            int scrollY = getScrollY();
            int screen_height = getContext().getResources().getDisplayMetrics().heightPixels;
            if(scrollY<=screen_height/3f){//0~1f,而透明度应该是1~0f
                listener.onTranlucent(1-scrollY/(screen_height/3f));//alpha=滑出去的高度/(screen_height/3f)
            }
        }
    }

}


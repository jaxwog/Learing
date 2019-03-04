package com.love.jax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * com.love.jax.view
 * Created by jax on 2019/3/1 17:40
 * TODO: ListView 展开
 * 判断ListView滑动到底部把事件交给外层的ScrollView进行处理
 */
public class TouchListView extends ListView {
    public TouchListView(Context context) {
        super(context);
    }

    public TouchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandeHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandeHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

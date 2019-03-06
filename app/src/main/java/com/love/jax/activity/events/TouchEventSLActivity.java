package com.love.jax.activity.events;


import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 解决ScrollView中嵌套ListView的滑动冲突
 * 展开ListView内容展示效果
 */
public class TouchEventSLActivity extends BaseActivity {

    List datas;
    @BindView(R.id.lv_1)
    ListView lv1;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, datas);
        lv1.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        datas= new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            datas.add("测试数据"+i);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_touch_event_sl;
    }

}

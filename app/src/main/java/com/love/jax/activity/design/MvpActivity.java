package com.love.jax.activity.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.activity.design.mvp.BaseMVPActivty;
import com.love.jax.activity.design.mvp.present.GirlPresentV1;
import com.love.jax.activity.design.mvp.view.IGrilView;
import com.love.jax.adapter.GirlListAdapter;
import com.love.jax.bean.Girl;

import java.util.List;

/**
 * 属于视图层 View
 * mvp 把视图层中UI逻辑抽象成View接口  把业务逻辑抽象成Presenter    Model还是原来的Model
 * 架构是对客观认识不足的妥协
 * 规范是对主观认识不足的妥协
 * View持有  Present层 对象的引用
 * Present 层持有 IGrilView 接口的引用，在present中直接操作接口
 *
 *
 */
public class MvpActivity extends BaseMVPActivty<IGrilView, GirlPresentV1<IGrilView>> implements IGrilView {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        listView= (ListView) findViewById(R.id.listview);
        mPresent.fectch();
    }

    @Override
    protected GirlPresentV1<IGrilView> createPresent() {
        return new GirlPresentV1<>(this);
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"正在拼命加载",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGrils(List<Girl> grils) {
        listView.setAdapter(new GirlListAdapter(this,grils));
    }
}

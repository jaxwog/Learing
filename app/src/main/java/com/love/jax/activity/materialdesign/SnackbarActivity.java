package com.love.jax.activity.materialdesign;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

/**
 * Snackbar:的提出实际上是界于Toast和Dialog的中间产物。
 * Toast: 用户无法交互；
 * Dialog：用户可以交互，但是体验会打折扣，会阻断用户的连贯性操作；
 * Snackbar既可以做到轻量级的用户提醒效果，又可以有交互的功能（必须是一种非必须的操作）。
 *    对源码进行分析，自定义Toast和snackbar 更换弹窗显示格式，或者字体颜色
 *    @see Snackbar 控制显示样式等内容
 *    @see android.support.design.widget.SnackbarManager 通过发送消息来更新显示
 */
public class SnackbarActivity extends BaseActivity {


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
        return R.layout.activity_snackbar;
    }


    public void showCustomToast(View v){
//		Toast.makeText(this, "吐司", 0).show();
        Toast result = new Toast(this);

        LayoutInflater inflate = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.custom_toast, null);
        TextView tv = (TextView)view.findViewById(R.id.textView1);
        tv.setText("自定义吐司在此！");

        //设置位置
        result.setGravity(Gravity.CENTER, 0, 0);
        result.setView(view);
        result.setDuration(Toast.LENGTH_LONG);
        result.show();
    }

    public void showSnackbar(View v){
        //LENGTH_INDEFINITE:无穷
        Snackbar snackbar = Snackbar.make(v, "是否开启加速模式？", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showCustomToast(null);
            }
        });
        //不能设置多个action，会被覆盖
//        snackbar.setAction("取消", new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                showCustomToast(null);
//            }
//        });
        snackbar.setCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                // TODO Auto-generated method stub
                showCustomToast(null);
                super.onDismissed(snackbar, event);
            }

            @Override
            public void onShown(Snackbar snackbar) {
                // TODO Auto-generated method stub
                super.onShown(snackbar);
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();

    }
}

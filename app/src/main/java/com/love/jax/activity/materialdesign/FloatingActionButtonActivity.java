package com.love.jax.activity.materialdesign;

import android.animation.ObjectAnimator;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.eventbus.JaxEventBus;
import com.love.jax.json.User;

import org.greenrobot.eventbus.EventBus;

/**
 * 悬浮动作按钮
 *
 特性：1.阴影效果--景深（反馈动作：按下去阴影加深elevation）
 2.水波纹效果
 兼容性注意:
 需要写两个layout/layout-v21
 layout-v21:添加layout_margin="16dp"
 layout: 添加layout_margin="0dp"

 app:backgroundTint="?attr/colorPrimary"背景着色
 app:elevation="10dp"阴影深度
 android:layout_margin="0dp"
 app:fabSize="mini"大小：normal，mini
 */
public class FloatingActionButtonActivity extends BaseActivity {

    private boolean reverse = false;
    @Override
    protected void initJestListener() {

    }

    public void rotate(View v){
        float toDegree= reverse?-180f:180f;
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(v, "rotation", 0.0f,toDegree)
                .setDuration(200);
        animator.start();
        reverse = !reverse;
        User user = new User();
        user.setId(18);
        user.setName("jax");
        user.setPwd("123456");
//        EventBus.getDefault().post(user);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JaxEventBus.getDefault().post(user);
            }
        }).start();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_floating_action_button;
    }
}

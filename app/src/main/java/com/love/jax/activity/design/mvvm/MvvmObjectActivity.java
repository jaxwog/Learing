package com.love.jax.activity.design.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.love.jax.R;
import com.love.jax.databinding.ActivityMvvmObjectBinding;

/**
 * mvvm 设计模式，model与View进行关联
 * 1、在build中使用dataBinding {enabled true}
 * 2、在xml中使用<layout/>标签
 * <data>
 *         <!--此处定义该布局要用到的数据的名称及类型-->
 *         <variable
 *             name="user"
 *             type="com.love.jax.activity.design.mvvm.User" />
 *  </data>
 *  3、关联实体类和View对象----> android:text="@{`    `+field.name}"
 *  4、实体类中设置
 *  5、Activity中设置 DataBindingUtil.setContentView  返回ActivityMvvmObjectBinding对象，设置对象
 */
public class MvvmObjectActivity extends AppCompatActivity {

    User user;
    Handler handler=new Handler();
    UserField userField=new UserField();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不能用 setContentView
//        setContentView(R.layout.activity_mvvm_object);
      ActivityMvvmObjectBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_mvvm_object);

        user=new User("李四","12345678","http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg");
        binding.setUser(user);
        userField.name.set("李四");
        userField.password.set("12345678");
        binding.setField(userField);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                user.setName("sindy");
//                user.setPassword("1111111");
                userField.name.set("sindy");
                userField.password.set("111111111");
            }
        },2000);

    }
}

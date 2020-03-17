package com.love.jax.activity.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.butterknife_annotations.BindViewJax;

/**
 * 编译时原理：动态生成数据，无需反射，完全没有性能消耗
 * ButterKnife  dagger2  eventbus   robust   换肤操作
 * 适合频繁高又不得不去写的代码
 * ButterKnife 专注View绑定和click事件处理
 * APT（Annotation Processing Tools）
 *     处理注解的工具，对源代码文件进行检测，找出其中的annotation进行额外处理。annotation处理器在处理注解时可以根据源文件中的
 * 注解生成额外的源文件和其它文件，apt还会编译生成的源文件和原来的源文件，将他们一起生成class
 *
 *
 */
public class ButterKnifeActivity extends AppCompatActivity   {

    @BindViewJax(R.id.btn_btk)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnifeJax.bind(this);
    }

    public void click(View view) {
        if (mButton!=null)
        Toast.makeText(this,mButton.toString(),Toast.LENGTH_LONG).show();
    }
}

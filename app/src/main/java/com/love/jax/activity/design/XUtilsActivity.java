package com.love.jax.activity.design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.love.jax.R;
import com.love.jax.ioc.xutils.InjectUtils;
import com.love.jax.ioc.xutils.annotion.ContentView;
import com.love.jax.ioc.xutils.annotion.OnClick;
import com.love.jax.ioc.xutils.annotion.OnLongClick;
import com.love.jax.ioc.xutils.annotion.ViewInject;

/**
 * 仿照xutils进行注解，发生在运行时，通过反射拿取对象
 */
@ContentView(R.layout.activity_xutils)
public class XUtilsActivity extends AppCompatActivity {

    @ViewInject(R.id.tv_ioc)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);

        Log.i("jax", "onCreate:XUtilsActivity mTextView =  " + mTextView);

    }

    @OnClick({R.id.btn_ioc1,R.id.btn_ioc2})
    public void onClick(View v) {
        if (v.getId()==R.id.btn_ioc1){
            Toast.makeText(this, "单击1"+v.getId(), Toast.LENGTH_SHORT).show();
        }else if (v.getId()==R.id.btn_ioc2) {
            Toast.makeText(this, "单击2", Toast.LENGTH_SHORT).show();
        }
    }


    @OnLongClick({R.id.btn_ioc1,R.id.btn_ioc2})
    public boolean longClick(View v) {
        if (v.getId()==R.id.btn_ioc1){
            Toast.makeText(this, "长按1"+v.getId(), Toast.LENGTH_SHORT).show();
        }else if (v.getId()==R.id.btn_ioc2) {
            Toast.makeText(this, "长按2", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}

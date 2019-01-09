package com.love.jax.activity.materialdesign;



import com.love.jax.R;
import com.love.jax.activity.BaseActivity;

/**
 * 卡片布局
 * 特性
 1) 边框圆角效果
 5.x 图片和布局都可以很好的呈现圆角效果，图片也变圆角了
 4.x 图不能变成圆角，如果要做成5.x一样的效果：通过加载图片的时候自己去处理成圆角
 2）阴影效果

 3）5.x上有Ripple水波纹效果（低版本需要自己做自定义的）
 android:foreground="?attr/selectableItemBackground"
 android:clickable="true"
 4）5.x实现按下的互动效果---下沉，松开弹起---Z轴位移效果 （低版本也需要自己自定义做）

 5）可以设置内容的内边距
 app:contentPadding="5dp"

 同一套布局的兼容性开发：(5.x上面不需要设置app:contentPadding="5dp"，而4.x上面不需要设置)
 layout
 layout-v21
 细节：
 5.x上面，边距阴影比较小，需要手动添加边距16dp
 4.x上面，边距太大， 手动修改边距0dp（原因：兼容包里面设置阴影效果自动设置了margin来处理16dp）

 */
public class CardViewActivity extends BaseActivity {


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
        return R.layout.activity_card_view;
    }
}

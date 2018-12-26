package com.love.jax.activity;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;

import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.love.jax.R;

import butterknife.BindView;


/**
 * 获取图片颜色，更加符合美学设计
 */
public class PaletteActivity extends BaseActivity {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //得到bitmap里面的的一些色彩信息---通过Palette类分析出来的
//		Palette palette = Palette.generate(bitmap);
        //异步任务---可能分析的图片会比较大或者颜色分布比较复杂，会耗时比较久，防止卡死主线程。
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                //暗、柔和的颜色
                int darkMutedColor = palette.getDarkMutedColor(Color.BLUE);//如果分析不出来，则返回默认颜色
                //暗、柔和
                int lightMutedColor = palette.getLightMutedColor(Color.BLUE);
                //暗、鲜艳
                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLUE);
                //亮、鲜艳
                int lightVibrantColor = palette.getLightVibrantColor(Color.BLUE);
                //柔和
                int mutedColor = palette.getMutedColor(Color.BLUE);
                //柔和
                int vibrantColor = palette.getVibrantColor(Color.BLUE);
                //获取某种特性颜色的样品
//				Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
                Palette.Swatch lightVibrantSwatch = palette.getVibrantSwatch();
                //谷歌推荐的：图片的整体的颜色rgb的混合值---主色调
                int rgb = lightVibrantSwatch.getRgb();
                //谷歌推荐：图片中间的文字颜色
                int bodyTextColor = lightVibrantSwatch.getBodyTextColor();
                //谷歌推荐：作为标题的颜色（有一定的和图片的对比度的颜色值）
                int titleTextColor = lightVibrantSwatch.getTitleTextColor();
                //颜色向量
                float[] hsl = lightVibrantSwatch.getHsl();
                //分析该颜色在图片中所占的像素多少值
                int population = lightVibrantSwatch.getPopulation();


                tvTitle.setBackgroundColor(getTranslucentColor(0.6f,rgb));
                tvTitle.setTextColor(titleTextColor);

                tv1.setBackgroundColor(darkMutedColor);
                tv1.setText("darkMutedColor");
                tv2.setBackgroundColor(lightMutedColor);
                tv2.setText("lightMutedColor");
                tv3.setBackgroundColor(darkVibrantColor);
                tv3.setText("darkVibrantColor");
                tv4.setBackgroundColor(lightVibrantColor);
                tv4.setText("lightVibrantColor");
                tv5.setBackgroundColor(mutedColor);
                tv5.setText("mutedColor");
                tv6.setBackgroundColor(vibrantColor);
                tv6.setText("vibrantColor");

            }
        });


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_palette;
    }


    /**
     * 4字节，8位构成1字节
     * 1101 0111 1000 1011  argb表示32位，前八位表示alpha透明度
     *         & 1111 1111   0xff  位运算，得到想要的位置的数据
     *           1000 1011
     */
    protected int getTranslucentColor(float percent, int rgb) {
        // 10101011110001111
//        int blue = Color.blue(rgb);
//        int green = Color.green(rgb);
//        int red = Color.red(rgb);
//        int alpha = Color.alpha(rgb);
        int blue = rgb & 0xff;
        int green = rgb >> 8 & 0xff;
        int red = rgb >> 16 & 0xff; //带符号右移，正数左补零，负数左补1
        int alpha = rgb >>> 24; //无符号右移24位，在左面补零

        alpha = Math.round(alpha * percent);
        Toast.makeText(this, "alpha:" + alpha + ",red:" + red + ",green:" + green, Toast.LENGTH_LONG).show();
        return Color.argb(alpha, red, green, blue);
    }


}

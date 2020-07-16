package com.love.jax.activity.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.love.jax.R;
import com.love.jax.view.MaskFilterView;

/**
 *  滤镜效果
 *  可用于按钮颜色变换，图片颜色
 * 1.滤镜
 *      针对于图片的颜色进行处理
 *    1.1.图形概念
 *    1.2.颜色通道,颜色模式
 *    1.3.颜色矩阵
 *    1.4.矩阵运算
 * 2.xfermode
 *    2.1.概念及使用
 *    2.2.模式分类
 * 	DTS
 * 	SRC
 * 	OTHER
 *在实际使用当中先画完目标图之后再进行xfermode设置，然后用原图去进行交汇然后他们两张图就会按照模式规则进行处理
 */
public class MaskFilterActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MaskFilterView view  = new MaskFilterView(this);
//        setContentView(view);
//        setContentView(R.layout.activity_mask_filter);

        //滤镜
        //setContentView(new FilterView(this));


        //xfermode
        setContentView(R.layout.activity_mask_filter);

        //dts
//        setContentView(R.layout.activity_mask_filter2);
//        findViewById(R.id.rounddstinbtn).setOnClickListener(this);
//        findViewById(R.id.invertdstinbtn).setOnClickListener(this);
//        findViewById(R.id.irregularwavebtn).setOnClickListener(this);
//        findViewById(R.id.heartbitbtn).setOnClickListener(this);
        //other
//        setContentView(R.layout.activity_mask_filter3);
//        findViewById(R.id.lightbookview).setOnClickListener(this);
//        findViewById(R.id.twitterview).setOnClickListener(this);
        //src
//        setContentView(R.layout.activity_mask_filter4);
//        findViewById(R.id.roundsrcin).setOnClickListener(this);
//        findViewById(R.id.invertsrcin).setOnClickListener(this);
//        findViewById(R.id.eraserview).setOnClickListener(this);
//        findViewById(R.id.guaguaview).setOnClickListener(this);
//        findViewById(R.id.roundsrcatop).setOnClickListener(this);
//        findViewById(R.id.invertsrcatop).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()) {
            //src
//            case R.id.roundsrcin:
//                findViewById(R.id.roundsrcin_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.invertsrcin:
//                findViewById(R.id.invertsrcin_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.eraserview:
//                findViewById(R.id.eraserview_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.guaguaview:
//                findViewById(R.id.guaguaview_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.roundsrcatop:
//                findViewById(R.id.roundsrcatop_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.invertsrcatop:
//                findViewById(R.id.invertsrcatop_view).setVisibility(View.VISIBLE);
//                break;

            //other
//            case R.id.lightbookview:
//                findViewById(R.id.lightbookview_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.twitterview:
//                findViewById(R.id.twitterview_view).setVisibility(View.VISIBLE);
//                break;
//            dts
//            case R.id.rounddstinbtn:
//                findViewById(R.id.roundimage).setVisibility(View.VISIBLE);
//                break;
//            case R.id.invertdstinbtn:
//                findViewById(R.id.invertimg).setVisibility(View.VISIBLE);
//                break;
//            case R.id.irregularwavebtn:
//                findViewById(R.id.irregularwaveview).setVisibility(View.VISIBLE);
//                break;
//            case R.id.heartbitbtn:
//                findViewById(R.id.heartbitview).setVisibility(View.VISIBLE);
//                break;
        }

    }

    private void hideAllViews() {
        //src
//        findViewById(R.id.roundsrcin_view).setVisibility(View.GONE);
//        findViewById(R.id.invertsrcin_view).setVisibility(View.GONE);
//        findViewById(R.id.eraserview_view).setVisibility(View.GONE);
//        findViewById(R.id.guaguaview_view).setVisibility(View.GONE);
//        findViewById(R.id.roundsrcatop_view).setVisibility(View.GONE);
//        findViewById(R.id.invertsrcatop_view).setVisibility(View.GONE);

//        other
//        findViewById(R.id.lightbookview_view).setVisibility(View.GONE);
//        findViewById(R.id.twitterview_view).setVisibility(View.GONE);

//        dts
//        findViewById(R.id.roundimage).setVisibility(View.GONE);
//        findViewById(R.id.invertimg).setVisibility(View.GONE);
//        findViewById(R.id.irregularwaveview).setVisibility(View.GONE);
//        findViewById(R.id.heartbitview).setVisibility(View.GONE);
    }


}

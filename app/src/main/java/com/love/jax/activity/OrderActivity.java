package com.love.jax.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.love.jax.R;
import com.love.jax.activity.recycleview.SelectCouponsActivity;
import com.love.jax.bean.CouponEntity;
import com.love.jax.bean.OrderEntity;
import com.love.jax.utils.ConfigSet;
import com.love.jax.utils.Logger;
import com.love.jax.view.CounterView;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity {
    OrderEntity entity;
    private static final String TAG = "OrderActivity";

    @BindView(R.id.view_count)
    CounterView viewCount;
    @BindView(R.id.view_money_pay)
    TextView viewMoneyPay;
    @BindView(R.id.btn_tips)
    FrameLayout btnTips;
    @BindView(R.id.view_preferential)
    TextView viewPreferential;
    @BindView(R.id.btn_preferential)
    FrameLayout btnPreferential;

    CouponEntity mCouponEntity;


    @Override
    protected void initBundle() {
        super.initBundle();
        if (mBundle != null) {
//            Logger.i("wog","获取到的json数据="+mBundle.getString(ConfigSet.INTENT_STRING));
            Gson gson = new Gson();
            entity = gson.fromJson(mBundle.getString(ConfigSet.INTENT_STRING), OrderEntity.class);

            Logger.i(TAG, "获取到的json数据=" + entity.toString());
        }
    }

    @Override
    protected void initJestListener() {


    }

    @Override
    protected void initView() {
        viewCount.setOnCountChangeListener(new CounterView.OnCountChangeListener() {
            @Override
            public void onCountChange(int count) {
                viewMoneyPay.setText("¥ " + count * 100);
                showPayInfo();
            }
        });

        viewCount.getOnCountChangeListener().onCountChange(1);
        viewCount.setMaxCount(10);
        viewCount.setMinCount(1);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_order;
    }


    @OnClick({R.id.btn_tips,R.id.btn_preferential})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tips:
                jumpToActivity(InvoiceInfoActivity.class, mBundle);
                break;
            case R.id.btn_preferential:
                jumpToActivityResult(SelectCouponsActivity.class, 1010);
                break;

        }

    }

    private void showPayInfo(){
        if (mCouponEntity!=null){
            if (mCouponEntity.getPice() == null){
//                viewPreferential.setText(mCouponEntity.getDiscount()+"折优惠券");
                BigDecimal  moneyPay0 = price("100").multiply(BigDecimal.valueOf(0.85));
                BigDecimal  moneyPay1 = price("100").multiply(BigDecimal.valueOf(viewCount.getResult()-1));


                String s = moneyPay0.add(moneyPay1).toString() ;

                viewMoneyPay.setText("￥ "+s);
            }else {
//                viewPreferential.setText(String.format("-￥%s",mCouponEntity.getPice()));
                int i = viewCount.getResult()*100 - Integer.parseInt(mCouponEntity.getPice());
                viewMoneyPay.setText("￥ "+i);
            }
        }else {
            //判断有多少张优惠券可用，显示X张优惠券可用
            viewPreferential.setText("11张可用");
            viewMoneyPay.setText("￥ "+viewCount.getResult()*100);
        }
    }

    private  BigDecimal price(String string){
        if (string.isEmpty())
            return BigDecimal.ZERO;
        return new BigDecimal(string);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK){
            if (requestCode==1010 && null != data){

                    mCouponEntity = (CouponEntity) data.getSerializableExtra("test");
                    if (mCouponEntity!=null){
                       if (mCouponEntity.getPice() == null){
                           viewPreferential.setText(mCouponEntity.getDiscount()+"折优惠券");
                           BigDecimal  moneyPay0 = price("100").multiply(BigDecimal.valueOf(0.85));
                           BigDecimal  moneyPay1 = price("100").multiply(BigDecimal.valueOf(viewCount.getResult()-1));


                           String s = moneyPay0.add(moneyPay1).toString() ;

                           viewMoneyPay.setText("￥ "+s);
                       }else {
                           viewPreferential.setText(String.format("-￥%s",mCouponEntity.getPice()));
                           int i = viewCount.getResult()*100 - Integer.parseInt(mCouponEntity.getPice());
                           viewMoneyPay.setText("￥ "+i);
                       }
                    }else {
                        //判断有多少张优惠券可用，显示X张优惠券可用
                        viewPreferential.setText("11张可用");
                        viewMoneyPay.setText("￥ "+viewCount.getResult()*100);
                    }
                }

//                int s = data.getIntExtra("test",0);
                //获取到优惠券信息，去更新UI显示
//                viewPreferential.setText("-￥"+s);
//                viewPreferential.setText(String.format("-￥%d",s));
//                tv.setText(String.format("User info(name=%s, age=%d)",user.getName(),user.getAge()));
                //去更新价格信息
//
//                if (s ==0){
//                    viewPreferential.setText("优惠券不可用");
//                    viewMoneyPay.setText("￥ "+100);
//                }else {
//                    viewMoneyPay.setText("￥ "+(100-s));
//                }

            }
        }
    }


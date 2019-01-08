package com.love.jax.activity.tkjobs;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.SelectCouponAdapter;
import com.love.jax.bean.CouponEntity;
import com.love.jax.utils.ListUtils;
import com.love.jax.utils.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 齿科服务提交订单选择优惠券页面
 */
public class SelectCouponsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_common_title)
    TextView tvCommonTitle;
    @BindView(R.id.tv_right_save)
    TextView tvRightSave;
    @BindView(R.id.rec_coupons)
    RecyclerView recCoupons;
    @BindView(R.id.common_line)
    TextView commonLine;

    List<CouponEntity> mList = new ArrayList<>();
    SelectCouponAdapter mAdapter ;
    private String mString = "超声波洁牙优惠券8";



    @Override
    protected void initJestListener() {

        mAdapter.setOnItemSelectListener(new SelectCouponAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(int position) {
                Logger.i("wog","选择了优惠券:"+mList.get(position).getTitle());
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        tvCommonTitle.setText("选择优惠券");
        tvRightSave.setText("不使用");
        tvRightSave.setVisibility(View.VISIBLE);
        commonLine.setVisibility(View.GONE);

        mAdapter = new SelectCouponAdapter(mContext);
        recCoupons.setLayoutManager(new LinearLayoutManager(
                this));
        recCoupons.setAdapter(mAdapter);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext,LinearLayoutManager.VERTICAL);
        Drawable drawable = getDrawable(R.drawable.item_divider_coupons);
        decoration.setDrawable(drawable);
        recCoupons.addItemDecoration(decoration);

        mAdapter.setDatas(mList);

        selectViewIntent();
    }

    @Override
    protected void initData() {

        for (int i = 0; i < 17; i++) {
            CouponEntity entity = new CouponEntity();
            if (i % 2==0){
                entity.setDiscount("85");
            }else {
                entity.setPice("30");
                entity.setManager("客户经理：王华"+i);
            }

            entity.setFlag(1);
            entity.setTitle("超声波洁牙优惠券"+i);
            entity.setStartData("2019.02.26");
            entity.setEndData("2019.12.26");
            entity.setContent("适用于儿童护牙套餐所有款式");

            mList.add(entity);
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_select_coupons;
    }



    private void resultData(int type) {

        int flagSelect =   mAdapter.getSelectItem();
        Intent intent = new Intent();

        //1、不使用,返回数据为空，需要接收Activity判断
        if (type==1){
            intent.putExtra("test",(Serializable) null);
        }

        if (flagSelect == -1){
            Logger.i("wog","没有选择优惠券，请选择相应的优惠券");
            return;
        }

        //2、左上角返回按键、实体返回按键
        if (type==2){
            intent.putExtra("test",mAdapter.getList().get(flagSelect));
        }

        setResult(RESULT_OK, intent);
        finish();

    }




    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode() && KeyEvent.ACTION_DOWN == event
                .getAction()) {
            resultData(2);
            return true;
        }

        return super.dispatchKeyEvent(event);
    }


    @OnClick({R.id.iv_back, R.id.tv_right_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                resultData(2);
                break;
            case R.id.tv_right_save:
                resultData(1);
                break;
        }
    }


    /**
     * 初次进入，根据intent传递的数据，设置优惠券选中状态
     */
    private void selectViewIntent(){
        if(!TextUtils.isEmpty(mString)&& !ListUtils.isEmpty(mList)){
            for(int i=0;i<mList.size();i++){
                if(mList.get(i).getTitle().contains(mString)){
                    mAdapter.setmSelectItem(i);
                    recCoupons.scrollToPosition(i);
                    break;
                }
            }
        }

    }


}

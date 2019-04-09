package com.love.jax.activity.tkjobs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.love.jax.JaxApplication;
import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.adapter.SelectCityAdapter;
import com.love.jax.adapter.SelectCountyAdapter;
import com.love.jax.adapter.SelectProvinceAdapter;
import com.love.jax.bean.dao.DbHelper;
import com.love.jax.bean.table.city;
import com.love.jax.bean.table.county;
import com.love.jax.bean.table.province;
import com.love.jax.callback.IDbHelper;
import com.love.jax.utils.Logger;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCityActivity extends BaseActivity {
    @BindView(R.id.text_report_0)
    TextView textReport0;
    @BindView(R.id.cursor_report_0)
    View cursorReport0;
    @BindView(R.id.tab_report_0)
    RelativeLayout tabReport0;
    @BindView(R.id.text_report_1)
    TextView textReport1;
    @BindView(R.id.cursor_report_1)
    View cursorReport1;
    @BindView(R.id.tab_report_1)
    RelativeLayout tabReport1;
    @BindView(R.id.text_report_2)
    TextView textReport2;
    @BindView(R.id.cursor_report_2)
    View cursorReport2;
    @BindView(R.id.tab_report_2)
    RelativeLayout tabReport2;
    @BindView(R.id.rec_selct_city)
    RecyclerView recSelctCity;
    @BindView(R.id.rec_selct_province)
    RecyclerView recSelctProvince;
    @BindView(R.id.rec_selct_county)
    RecyclerView recSelctCounty;
    private IDbHelper iDbHelper;
    List<city> mCityList;
    List<province> mProvinceList;
    List<county> mCountyList;
    SelectCityAdapter mCityAdapter;
    SelectProvinceAdapter mProvinceAdapter;
    SelectCountyAdapter mCountyAdapter;
    private city mCity;
    private province mProvince;
    private county mCounty;



    @Override
    protected void initJestListener() {

        mProvinceAdapter.setOnItemClickListener(new SelectProvinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mProvince = mProvinceList.get(position);

                mCityList = iDbHelper.queryCity(mProvince.getProvinceCode());
                mCityAdapter.setDatas(mCityList);
                showRecy(1);
                showCityTitle(1);

            }
        });

        mCityAdapter.setOnItemClickListener(new SelectCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCity = mCityList.get(position);
                mCountyList = iDbHelper.queryCounty(mCity.getCityCode());

//                county temp = new county();
//                temp.setCityCode(mCity.getCityCode());
//                temp.setCountyCode(mCity.getCityCode());
//                temp.setCountyName("全城");
//                temp.setId(Long.MAX_VALUE);
                mCountyList.add(0,new county(Long.MAX_VALUE,mCity.getCityCode(),"全城",mCity.getCityCode()));
                mCountyAdapter.setDatas(mCountyList);
                showRecy(2);
               showCityTitle(2);

            }
        });


        mCountyAdapter.setOnItemClickListener(new SelectCountyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showRecy(2);
                mCounty = mCountyList.get(position);
                Logger.i("wog","县区选择=="+mCounty.toString());

                showCityTitle(4);


            }
        });
    }

    private void showRecy(int type){
        if (0==type){
            recSelctProvince.setVisibility(View.VISIBLE);
            recSelctCity.setVisibility(View.GONE);
            recSelctCounty.setVisibility(View.GONE);
        }else if (1==type){
            recSelctProvince.setVisibility(View.GONE);
            recSelctCity.setVisibility(View.VISIBLE);
            recSelctCounty.setVisibility(View.GONE);
        }else if (2==type){
            recSelctProvince.setVisibility(View.GONE);
            recSelctCity.setVisibility(View.GONE);
            recSelctCounty.setVisibility(View.VISIBLE);
        }else {
            recSelctProvince.setVisibility(View.GONE);
            recSelctCity.setVisibility(View.GONE);
            recSelctCounty.setVisibility(View.GONE);
        }
    }


    private void showCityTitle(int type){
        if (0 == type){
            textReport0.setText("省份");
            textReport0.setTextColor(ContextCompat.getColor(mContext, R.color.color_1F2845));
            cursorReport0.setVisibility(View.VISIBLE);
            textReport1.setText("城市");
            textReport1.setTextColor(ContextCompat.getColor(mContext, R.color.color_8A91A4));
            cursorReport1.setVisibility(View.GONE);
            textReport2.setText("县区");
            textReport2.setTextColor(ContextCompat.getColor(mContext, R.color.color_8A91A4));
            cursorReport2.setVisibility(View.GONE);
        }else if (1==type){
            textReport0.setText(showLongString(mProvince.getProvinceName()));
            textReport0.setTextColor(ContextCompat.getColor(mContext, R.color.color_4C5979));
            cursorReport0.setVisibility(View.GONE);
            textReport1.setText("城市");
            textReport1.setTextColor(ContextCompat.getColor(mContext, R.color.color_1F2845));
            cursorReport1.setVisibility(View.VISIBLE);
            textReport2.setText("县区");
            textReport2.setTextColor(ContextCompat.getColor(mContext, R.color.color_8A91A4));
            cursorReport2.setVisibility(View.GONE);
        }else if (2==type){
            textReport0.setText(showLongString(mProvince.getProvinceName()));
            textReport0.setTextColor(ContextCompat.getColor(mContext, R.color.color_4C5979));
            cursorReport0.setVisibility(View.GONE);
            textReport1.setText(showLongString(mCity.getCityName()));
            textReport1.setTextColor(ContextCompat.getColor(mContext, R.color.color_4C5979));
            cursorReport1.setVisibility(View.GONE);
            textReport2.setText("县区");
            textReport2.setTextColor(ContextCompat.getColor(mContext, R.color.color_1F2845));
            cursorReport2.setVisibility(View.VISIBLE);
        }else {
            textReport0.setText(showLongString(mProvince.getProvinceName()));
            textReport0.setTextColor(ContextCompat.getColor(mContext, R.color.color_4C5979));
            cursorReport0.setVisibility(View.GONE);
            textReport1.setText(showLongString(mCity.getCityName()));
            textReport1.setTextColor(ContextCompat.getColor(mContext, R.color.color_4C5979));
            cursorReport1.setVisibility(View.GONE);
            textReport2.setText(showLongString(mCounty.getCountyName()));
            textReport2.setTextColor(ContextCompat.getColor(mContext, R.color.color_1F2845));
            cursorReport2.setVisibility(View.VISIBLE);

        }
    }

    private String showLongString(String string){
        if (!TextUtils.isEmpty(string) && string.length() > 5){
           return string = string.substring(0,5)+ "...";
        }
        return string;
    }

    @Override
    protected void initView() {
        showRecy(0);
        showCityTitle(0);

        mProvinceAdapter = new SelectProvinceAdapter(mContext);
        LinearLayoutManager layoutManager0 = new LinearLayoutManager(this);
        layoutManager0.setOrientation(LinearLayoutManager.VERTICAL);
        recSelctProvince.setLayoutManager(layoutManager0);
        recSelctProvince.setAdapter(mProvinceAdapter);

        mCityAdapter = new SelectCityAdapter(mContext);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recSelctCity.setLayoutManager(layoutManager1);
        recSelctCity.setAdapter(mCityAdapter);

        mCountyAdapter = new SelectCountyAdapter(mContext);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        recSelctCounty.setLayoutManager(layoutManager2);
        recSelctCounty.setAdapter(mCountyAdapter);

        mProvinceAdapter.setDatas(mProvinceList);
    }

    @Override
    protected void initFlag() {
        super.initFlag();

    }

    @Override
    protected void initData() {

//            this.iDbHelper = DbHelper.getInstance(this, FILE_PATH, "carrepairlocation.db");
            this.iDbHelper = JaxApplication.getInstance().getDataBase();
//            this.iDbHelper = JaxApplication.getInstance().getDataBase();
            mProvinceList = iDbHelper.queryProvince();

//        this.iDbHelper = JaxApplication.getInstance().getDataBase();
//        mProvinceList = iDbHelper.queryProvince();
//        mCityList = iDbHelper.queryCity("130000");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_select_city;
    }


    @OnClick({R.id.tab_report_0, R.id.tab_report_1, R.id.tab_report_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_report_0:
                showRecy(0);
                showCityTitle(0);
               if (null !=mProvince){
                   textReport0.setText(showLongString(mProvince.getProvinceName()));
               }
                break;
            case R.id.tab_report_1:
                if (null != mCity) {
                    showRecy(1);
                    showCityTitle(1);
                }
                break;
            case R.id.tab_report_2:
                if (null != mCounty) {
                    showRecy(2);
                    showCityTitle(2);

                }
                break;
        }
    }




}

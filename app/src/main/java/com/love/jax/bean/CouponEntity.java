package com.love.jax.bean;

import java.io.Serializable;

/**
 * com.love.jax.bean
 * Created by jax on 2019/1/3 11:08
 * TODO: 优惠券信息
 */
public class CouponEntity implements Serializable {

    private String title; //标题
    private String startData; //有效开始期
    private String endData; //有效结束期
    private String  manager; //客户经理
    private String  pice;  //优惠价格

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    private String  discount; //折扣


    @Override
    public String toString() {
        return "CouponEntity{" + "title='" + title + '\'' + ", startData='" + startData + '\'' +
                ", endData='" + endData + '\'' + ", manager='" + manager + '\'' + ", pice=" +
                pice + ", discount=" + discount + ", flag=" + flag + '}';
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int  flag; //是否过期


}

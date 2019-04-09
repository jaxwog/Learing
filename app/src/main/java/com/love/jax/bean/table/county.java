package com.love.jax.bean.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * com.love.jax.bean.table
 * Created by jax on 2019/4/7 00:46
 * TODO： 县区选择列表
 */
@Entity(nameInDb = "county")
public class county implements Serializable{

    @Id(autoincrement = true)
    private Long id;
    private String countyCode;

    @Override
    public String toString() {
        return "county{" + "id=" + id + ", countyCode='" + countyCode + '\'' + ", countyName='" +
                countyName + '\'' + ", cityCode='" + cityCode + '\'' + '}';
    }

    private String countyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    private String cityCode;

    @Generated(hash = 672268233)
    public county(Long id, String countyCode, String countyName, String cityCode) {
        this.id = id;
        this.countyCode = countyCode;
        this.countyName = countyName;
        this.cityCode = cityCode;
    }

    @Generated(hash = 231032920)
    public county() {
    }
}

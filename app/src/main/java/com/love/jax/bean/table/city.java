package com.love.jax.bean.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * com.love.jax.bean.table
 * Created by jax on 2019/4/6 00:09
 * TODO:城市列表
 */
@Entity(nameInDb = "city")
public class city implements Serializable {

    @Id(autoincrement = true)
    private Long id;
    private String cityCode;
    private String cityName;
    private String provinceCode;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "city{" + "id=" + id + ", cityCode='" + cityCode + '\'' + ", cityName='" +
                cityName + '\'' + ", provinceCode='" + provinceCode + '\'' + '}';
    }



    @Generated(hash = 954288239)
    public city(Long id, String cityCode, String cityName, String provinceCode) {
        this.id = id;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.provinceCode = provinceCode;
    }

    @Generated(hash = 496887412)
    public city() {
    }
}

package com.love.jax.bean.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * com.love.jax.bean.table
 * Created by jax on 2019/4/7 00:39
 * TODO:省份列表
 */
@Entity(nameInDb = "province")
public class province implements Serializable {

    @Id(autoincrement = true)
    private Long id;
    private String provinceCode;

    @Override
    public String toString() {
        return "province{" + "id=" + id + ", provinceCode='" + provinceCode + '\'' + ", " +
                "provinceName='" + provinceName + '\'' + '}';
    }

    private String provinceName;

    @Generated(hash = 41009445)
    public province(Long id, String provinceCode, String provinceName) {
        this.id = id;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }

    @Generated(hash = 1132815257)
    public province() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}

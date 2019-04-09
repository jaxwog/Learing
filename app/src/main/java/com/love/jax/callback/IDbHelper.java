package com.love.jax.callback;

import com.love.jax.bean.table.city;
import com.love.jax.bean.table.county;
import com.love.jax.bean.table.province;

import java.util.List;

/**
 * com.love.jax.callback
 * Created by jax on 2019/4/6 00:25
 * TODO:
 */
public interface IDbHelper {

    public List<city> queryCity(String prvCode);

    public void insCity();

    public List<province> queryProvince();

    public List<county> queryCounty(String cityCode);

}

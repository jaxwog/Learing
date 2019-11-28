package com.love.jax.database;

import com.love.jax.database.db.BaseDao;

import java.util.List;

/**
 * com.love.jax.database
 * Created by jax on 2019-11-27 14:14
 * TODO:
 */
public class DownDao<T> extends BaseDao<T> {
    @Override
    protected String createTable() {
        return "create table if not exists tb_down(tb_time varchar(20),tb_path varchar(10))";
    }

    @Override
    public List<T> query(String sql) {
        return null;
    }
}

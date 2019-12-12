package com.love.jax.database.upbean;

import com.love.jax.database.db.BaseDao;

import java.util.List;

/**
 * com.love.jax.database.update
 * Created by jax on 2019-12-11 15:22
 * TODO:
 */
public class PhotoDao extends BaseDao<Photo> {
    @Override
    protected String createTable() {
        return "create table if not exists tb_photo(\n" +
                "                time TEXT,\n" +
                "                path TEXT,\n" +
                "                to_user TEXT\n" +
                "                )";

    }

    @Override
    public List<Photo> query(String sql) {
        return null;
    }
}

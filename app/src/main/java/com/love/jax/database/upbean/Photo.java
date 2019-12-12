package com.love.jax.database.upbean;

import com.love.jax.database.db.annotion.DbTable;

/**
 * com.love.jax.database.update
 * Created by jax on 2019-12-11 15:21
 * TODO:
 */
@DbTable("tb_photo")
public class Photo {
    public String time;

    public String path;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


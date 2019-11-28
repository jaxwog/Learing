package com.love.jax.database;

import com.love.jax.database.db.annotion.DbFiled;
import com.love.jax.database.db.annotion.DbTable;

/**
 * com.love.jax.database
 * Created by jax on 2019-11-27 14:16
 * TODO:
 */
@DbTable("tb_down")
public class DownFile {

    @DbFiled("tb_time")
    public String time;

    @DbFiled("tb_path")
    public String path;

    public DownFile(String time, String path) {
        this.time = time;
        this.path = path;
    }

    public DownFile( ) {

    }

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


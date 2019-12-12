package com.love.jax.database.upbean;

import com.love.jax.database.db.annotion.DbTable;

/**
 * com.love.jax.database.update
 * Created by jax on 2019-12-11 15:09
 * TODO:模拟登陆用户信息
 */
@DbTable("tb_login")
public class LoginTable {
    public String name;

    public String password;

    public String user_id;
    public  Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

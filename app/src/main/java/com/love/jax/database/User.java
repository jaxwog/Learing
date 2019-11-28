package com.love.jax.database;

import com.love.jax.database.db.annotion.DbFiled;
import com.love.jax.database.db.annotion.DbTable;

/**
 * com.love.jax.database
 * Created by jax on 2019-11-26 15:59
 * TODO:用户实体类
 */
@DbTable("tb_user")
public class User {
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User( ) {

    }

    public User(Integer user_Id, String name, String password) {
        this.user_Id = user_Id;
        this.name = name;
        this.password = password;
    }

    public Integer user_Id;

    @DbFiled("name")
    public String name;

    @DbFiled("password")
    public String password;

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
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

    @Override
    public String toString() {
        return "User{" + "user_Id=" + user_Id + ", name='" + name + '\'' + ", password='" + password + '\'' + '}';
    }
}

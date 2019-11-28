package com.love.jax.database;

import com.love.jax.database.db.BaseDao;

import java.util.List;

/**
 * com.love.jax.database
 * Created by jax on 2019-11-26 23:11
 * TODO:具体的用户信息操作Dao类
 */
public class UserDao extends BaseDao {
    //返回创建数据库的语句，也可以通过注解（拿到表名字，每个字段的名字）生成自动语句
    @Override
    protected String createTable() {
        return "create table if not exists tb_user(user_Id int,name varchar(20),password varchar(10))";
    }

    @Override
    public List query(String sql) {
        return null;
    }
}

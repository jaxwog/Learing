package com.love.jax.database.upbean;


import android.util.Log;

import com.love.jax.database.db.BaseDao;

import java.util.List;

/**
 * com.love.jax.database.update
 * Created by jax on 2019-12-11 15:11
 * TODO:
 */
public class LoginDao extends BaseDao<LoginTable> {

    private static final String TAG = "jax";

    //创建数据库表 ，如果存在同名的数据库runtimeexception，if not exists判断是否存在
    @Override
    protected String createTable() {
        return "create table if not exists tb_login( name TEXT, password TEXT, user_id Text," +
                "status Integer);";
    }

    @Override
    public List<LoginTable> query(String sql) {
        return null;
    }

    /**
     * 插入数据库，如果把所有用户的状态更改为未登录，
     * 如果数据库中存在了当前用户，则把当前用户置为登录状态
     * 如果数据库中不存在该用户，则插入数据到表中
     * @return
     */
    @Override
    public Long insert(LoginTable entity) {
        List<LoginTable> list = query(new LoginTable());
        LoginTable where = null;
//        list.for  ,出来foreach信息
        for (LoginTable user : list) {
            where = new LoginTable();
            where.setUser_id(user.getUser_id());
            user.setStatus(0);
            Log.i(TAG, "用户" + user.getName() + "更改为未登录状态");
            update(user, where);
        }
        for (LoginTable user : list) {
            if (user.name.equals(entity.name)||user.user_id.equals(entity.user_id)){
                entity.setStatus(1);
                update(entity,user);
                Log.i(TAG, "用户" + entity.getName() + "登录");
                return 100L;
            }
        }

        Log.i(TAG, "用户" + entity.getName() + "登录");
        entity.setStatus(1);
        return super.insert(entity);
    }

    /**
     * 得到当前登录的User（登录状态为1）
     * @return
     */
    public LoginTable getCurrentLogin() {
        LoginTable user = new LoginTable();
        user.setStatus(1);
        List<LoginTable> list = query(user);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}

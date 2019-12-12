package com.love.jax.database.db;

import android.os.Environment;

import com.love.jax.database.upbean.LoginDao;
import com.love.jax.database.upbean.LoginTable;

import java.io.File;

/**
 * com.love.jax.database.db
 * Created by jax on 2019-12-11 15:35
 * TODO:
 */
public enum PrivateDataBaseEnums {

    /**
     * 存放本地数据库的路径
     */
    database("local/data/database/");

    /**
     * 文件存储的文件路径
     */
    private String value;

    PrivateDataBaseEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        //如果对象已经存在，从内存中获取，否则获取的数据库对象
        LoginDao userDao = BaseDaoFactory.getInstance().getDataHelper(LoginDao.class,
                LoginTable.class);
        if (userDao != null) {
            LoginTable currentUser = userDao.getCurrentLogin();
            if (currentUser != null) {
                File file = new File(Environment.getExternalStorageDirectory()+"/update", currentUser.getUser_id());
                if (!file.exists()) {
                    file.mkdirs();
                }
//返回的字符串是当前用户的私有数据库的路径
                return file.getAbsolutePath()+"/logic.db";
            }

        }
        return value;
    }


}

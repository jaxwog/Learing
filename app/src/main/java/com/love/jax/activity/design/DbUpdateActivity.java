package com.love.jax.activity.design;


import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.database.db.BaseDaoFactory;
import com.love.jax.database.upbean.LoginDao;
import com.love.jax.database.upbean.LoginTable;
import com.love.jax.database.upbean.Photo;
import com.love.jax.database.upbean.PhotoDao;
import com.love.jax.database.update.UpdateManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据库分包、版本升级
 * 分包主要在根据用户id生成不同的数据库，数据库路径根据用户id进行编写，打开数据库时候就是打开指定路径的数据库
 * 数据库升级包含（判断是否需要升级，如果需要升级-------》》》首先进行备份）
 *1、旧版本数据库更名
 * 2、创建新版本数据库（旧版本数据库表名）
 * 3、旧版本数据迁移到新版本数据库
 * 4、删除旧版本数据表（如果中间过程出现失败，需要进行数据的恢复）
 *
 * alter table tb_login add colmun loginTime text
 * 在表中插入列名，数据库中内容不会消失（只能在表的末尾增加字段，不能删除字段）
 */
public class DbUpdateActivity extends BaseActivity {

    int i=0;

    LoginDao baseDao;

    UpdateManager updateManager;


    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        updateManager=new UpdateManager();
        //创建dao，是获取的存放登录用户的总表
        baseDao= BaseDaoFactory.getInstance().getDataHelper(LoginDao.class, LoginTable.class);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_db_update;
    }

    public void login(View view) {
        LoginTable user=new LoginTable();
        user.setName("V00"+(i++));
        user.setPassword("123456");
        user.setName("张三"+i);
        user.setUser_id("N000"+i);
        baseDao.insert(user);
    }

    /**
     * 添加当前登录用户维护的表的信息
     */
    public void insert(View view) {
        Photo photo=new Photo();
        photo.setPath("data/data/my.jpg");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        photo.setTime(dateFormat.format(new Date()));
        PhotoDao photoDao=BaseDaoFactory.getInstance().getLoginHelper(PhotoDao.class,Photo.class);
        photoDao.insert(photo);
    }

    public void write(View view) {
        /**
         * 写入版本
         */
        updateManager.saveVersionInfo(this,"V002");
    }

    public void update(View view) {
        updateManager.checkThisVersionTable(this);

        updateManager.startUpdateDb(this);
    }
}

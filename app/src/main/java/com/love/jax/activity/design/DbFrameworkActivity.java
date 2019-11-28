package com.love.jax.activity.design;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.database.DBHelper;
import com.love.jax.database.DownDao;
import com.love.jax.database.DownFile;
import com.love.jax.database.User;
import com.love.jax.database.UserDao;
import com.love.jax.database.db.BaseDaoFactory;
import com.love.jax.database.db.IBaseDao;

import java.util.List;

public class DbFrameworkActivity extends BaseActivity {


    IBaseDao<User> mBaseDao ;
    IBaseDao<DownFile> fileDao;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mBaseDao = BaseDaoFactory.getInstance().getDataHelper(UserDao.class, User.class);
        fileDao=BaseDaoFactory.getInstance().getDataHelper(DownDao.class,DownFile.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_db_framework;
    }

    public void update(View view) {
        User where=new User();
        where.setName("teacher");

        User user=new User("David","123456789");
        mBaseDao.update(user,where);

    }

    public void insert(View view) {

         for (int i=0;i<20;i++) {
            DownFile downFile=new DownFile("2019-11-"+i,"123456");
            fileDao.insert(downFile);
         }
        for (int i=0;i<20;i++) {
            User user = new User(i,"teacher", "123456");
            mBaseDao.insert(user);
        }

    }

    public void delect(View view) {
        User user=new User();
        user.setName("David");
        mBaseDao.delete(user);
    }

    public void queryList(View view) {
        User where=new User();
        where.setName("David");
        where.setUser_Id(5);
        List<User> list=mBaseDao.query(where);
        Log.i("jax","查询到  "+list.size()+"  条数据");
        for (int i = 0; i < list.size(); i++) {
            Log.i("jax",list.get(i).toString());
        }
    }


    /**
     * -------------------------普通方式进行数据库操作---------------------------------------------------------------------------
     */
    public void save(View view)
    {
        User user=new User();
        user.setName("David");
        user.setPassword("123456");
        saveUser(user);

        //普通数据库修改记录
        User updateUser=new User();
        updateUser.setName("sindy");
        updateUser.setPassword("111111");
        updateUser(updateUser,"David");
    }
    public void  saveUser(User user)
    {
        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        db.execSQL("create table if not exists tb_user(name varchar(20),password varchar(10))");
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("password", user.getPassword());
        int scheduleID = -1;
        try {
            db.insert("schedule", null, values);
        } finally {
        }
        db.close();
    }

    public void  updateUser(User user,String name)
    {
        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        db.execSQL("create table if not exists tb_user(name varchar(20),password varchar(10))");
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("password", user.getPassword());
        try {
//            db.execSQL("update tb_user set name='王五' where name='李四'");
            db.update("tb_user", values, "1=1 and name=? and password= ?", new String[] { String.valueOf(user.getName()) });
        } finally {
        }
        db.close();
    }

    public void  deleteUser(User user,String name)
    {
        DBHelper dbHelper=new DBHelper(this);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        try {
            db.delete("tb_user","name=?",new String[]{user.getName()});
        } finally {
        }
        db.close();
    }


}

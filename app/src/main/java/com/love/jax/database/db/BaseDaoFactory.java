package com.love.jax.database.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

/**
 * com.love.jax.database.db
 * Created by jax on 2019-11-26 15:23
 * TODO:单例模式，打开数据库，返回basedao操作类
 */
public class BaseDaoFactory {
    //数据库存储路径
    private String sqliteDatabasePath;

    private SQLiteDatabase sqLiteDatabase;

    private static BaseDaoFactory instance=new BaseDaoFactory();

    private BaseDaoFactory() {
        sqliteDatabasePath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/teacher.db";
        openDatabase();
    }

    /**
     *
     * @param clazz 传入的为BaseDao
     * @param entityClass 传入的为数据实体类
     * @param <T> BaseDao持有M数据实体类， 范围为BaseDao的范围
     * @param <M> 无范围
     * @return BaseDao
     */
    public synchronized  <T extends BaseDao<M>,M> T getDataHelper(Class<T> clazz,Class<M> entityClass){
        BaseDao baseDao = null;
        try {
            //通过反射创建具体的BaseDao子类
            baseDao = clazz.newInstance();

            baseDao.init(entityClass,this.sqLiteDatabase);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return (T) baseDao;
    }

    private void openDatabase() {
        //打开数据库，没有数据库就创建一个数据库
        this.sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDatabasePath,null);
    }


    public  static  BaseDaoFactory getInstance() {
        return instance;
    }

}

package com.love.jax.bean.dao;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.love.jax.R;
import com.love.jax.activity.MainActivity;
import com.love.jax.bean.table.city;
import com.love.jax.bean.table.county;
import com.love.jax.bean.table.province;
import com.love.jax.callback.IDbHelper;
import com.love.jax.utils.Logger;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.love.jax.bean.dao.DaoMaster.createAllTables;

/**
 * com.love.jax.bean.dao
 * Created by jax on 2019/4/6 00:27
 * TODO:
 */
public class DbHelper implements IDbHelper {


    private cityDao mCityDao;
    private provinceDao mProvinceDao;
    private countyDao mCountyDao;



    private SQLiteDatabase db;
    private DaoSession daoSession = null;
    private static volatile DbHelper instance = null;

    private void getAllDao() {
        mCityDao = daoSession.getCityDao();
        mProvinceDao = daoSession.getProvinceDao();
        mCountyDao = daoSession.getCountyDao();

    }

    private DbHelper(Context context) {
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "csgData.db", null);
        db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        daoSession = mDaoMaster.newSession();
        getAllDao();
    }

    public void CopyDataToSdcard(Context context, String filePath, String outFileName) {

        try {
            InputStream is = context.getResources().getAssets().open("carrepairlocation.db");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);

            FileOutputStream fos = new FileOutputStream(new File(filePath+outFileName));
            fos.write(buffer);
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private DbHelper(Context context, String filePath,String fileName) {
        File file = new File(filePath+fileName);
        if (!file.exists()) {
            Logger.i("wog","向sd卡写入数据了=====================");
            CopyDataToSdcard(context,filePath,fileName);
        }
        MySelfHelper mHelper = new MySelfHelper(context.getApplicationContext(), filePath+fileName);
        db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        daoSession = mDaoMaster.newSession();
        getAllDao();
    }

    public static class MySelfHelper extends DaoMaster.DevOpenHelper{

        public MySelfHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onCreate(Database db) {
//            super.onCreate(db);
            createAllTables(db, true);
        }
    }

    public static DbHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper(context);
                }
            }
        }

        return instance;
    }

    public static DbHelper getInstance(Context context, String filePath, String fileName) {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    instance = new DbHelper(context,filePath, fileName);
                }
            }
        }

        return instance;
    }
    @Override
    public List<city> queryCity(String prvCode) {
        List<city> meterAuths = mCityDao.queryBuilder().where(cityDao.Properties.ProvinceCode.eq(prvCode)).list();
        Logger.i("jax","大小："+meterAuths.size());
        for (int i = 0; i < meterAuths.size(); i++) {
            Logger.i("jax","位置大小："+i+"-----"+ meterAuths.get(i).toString());
            meterAuths.get(i).toString();
        }
        return meterAuths;

    }
    @Override
    public void insCity() {
        List<city> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            city mcity = new city();
            mcity.setCityCode("123"+i);
            mcity.setCityName("长江");
            mcity.setProvinceCode("130000");
            list.add(mcity);
        }
        mCityDao.insertInTx(list);
    }

    @Override
    public List<province> queryProvince() {
        return mProvinceDao.loadAll();
    }

    @Override
    public List<county> queryCounty(String cityCode) {
        List<county> mlist = mCountyDao.queryBuilder().where(countyDao.Properties.CityCode.eq(cityCode)).list();
        return mlist;
    }
}

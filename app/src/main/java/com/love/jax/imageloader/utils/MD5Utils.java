package com.love.jax.imageloader.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * com.love.jax.imageloader.utils
 * Created by jax on 2019-12-13 16:04
 * TODO:获取指定参数的MD5值
 */
public class MD5Utils {
    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.d("jax", "md5 算法不支持!");
        }
    }

    /**
     * MD5加密
     *
     * @param key
     * @return 如果为空返回HashCode否则返回MD5值
     */
    public static String toMD5(String key) {
        if (digest == null) {
            return String.valueOf(key.hashCode());
        }
        //更新字节
        digest.update(key.getBytes());
        //获取最终的摘要
        return convert2HexString(digest.digest());
    }

    /**
     * 转为16进制字符串
     *
     * @param bytes
     * @return
     */
    private static String convert2HexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            //->8->08
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}

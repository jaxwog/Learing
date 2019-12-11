package com.love.jax.http.donwload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * com.love.jax.http.donwload
 * Created by jax on 2019-12-05 09:37
 * TODO:copy对象，不操作原来的对象数据（可以实现cloneble接口，需要自己实现克隆的参数）
 * 常用的单例模式在应用中经过序列化和反序列化之后创建的是两个对象（枚举模式保证一个对象）
 * 参考原型模式的对象克隆
 */
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 723798441358903231L;

    public BaseEntity() {
    }

    public T copy() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            //序列化对象
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            //反序列化对象
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Object result = objectInputStream.readObject();
            return (T) result;
        } catch (IOException io) {
            io.printStackTrace();
        } catch (ClassNotFoundException classNot) {
            classNot.printStackTrace();
        } finally {
            if(byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }

            if(objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }

        }

        return null;
    }
}

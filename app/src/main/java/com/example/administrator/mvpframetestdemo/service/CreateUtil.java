package com.example.administrator.mvpframetestdemo.service;

import java.lang.reflect.ParameterizedType;

/**
 * Created by：XQyi on 2017/8/7
 * Use: 通过getGenericSuperclass方法可以获取当前对象的直接超类的 Type (ps: 参考 http://blog.csdn.net/tanksyg/article/details/49928115)
 */
public class CreateUtil {

    static <T> T getT(Object o, int i) {
        try {

            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

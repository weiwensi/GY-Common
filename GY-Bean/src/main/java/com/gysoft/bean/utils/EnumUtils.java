package com.gysoft.bean.utils;

import java.io.Serializable;

/**
 * @author 周宁
 * @Date 2018-05-23 20:43
 */
public class EnumUtils {

    /**
     * 根据key获取枚举对象
     * @param type
     * @param index
     * @param <I>
     * @param <V>
     * @return
     */
    public static <I extends BaseEnum,V extends Serializable> I getEnumObject(Class<I> type, V index) {
        I[] types = type.getEnumConstants();
        for (I t : types) {
            if (t.getKey().equals(index)) {
                return t;
            }
        }
        return null;
    }
}

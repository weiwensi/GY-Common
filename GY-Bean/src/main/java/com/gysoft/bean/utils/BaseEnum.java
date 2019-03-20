package com.gysoft.bean.utils;

import java.io.Serializable;

/**
 * @author 周宁
 * @Date 2018-05-23 20:33
 */
public interface BaseEnum<K extends Serializable,V extends Serializable> {

    K getKey();

    V getValue();

}

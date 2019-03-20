package com.gysoft.utils.test.func;

import java.io.Serializable;

/**
 * @author 周宁
 * @Date 2018-11-12 13:53
 */
@FunctionalInterface
public interface Fn<T> extends Serializable {
    Object apply(T source);
}

package com.gysoft.utils.cache;

import java.lang.reflect.Method;

/**
 * 缓存key生成器
 * @author 周宁
 * @Date 2018-05-17 9:16
 */
public interface CacheKeyGenerator {
    /**
     * 缓存key生成方法
     * @author 周宁
     * @param target
     * @param method
     * @param params
     * @throws
     * @version 1.0
     * @return Object
     */
    Object generate(Class<?> target, Method method, Object... params);
}

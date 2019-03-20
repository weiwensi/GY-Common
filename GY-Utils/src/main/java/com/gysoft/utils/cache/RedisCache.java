package com.gysoft.utils.cache;

import java.lang.annotation.*;

/**
 * 缓存注解
 *
 * @author 周宁
 * @Date 2018-05-17 9:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {

    /**
     * cache的key
     */
    String key() default "";

    /**
     * 自定义keyGenerator的实现类的BeanName,需实现CacheKeyGenerator接口
     */
    String keyGenerator() default "";

    /**
     * Redis中缓存的过期时间,单位为秒
     */
    int expires();

    /**
     * 是否存从本地加载缓存
     */
    boolean localCacheLoad() default true;
}

package com.gysoft.utils.cache;

import java.util.function.Supplier;

/**
 * @author 周宁
 * @Date 2018-10-11 19:30
 */
public interface ICache{
    /**
     * 设置缓存
     * @param key 缓存的key
     * @param value 缓存的值
     * @param expired 缓存有效时间
     */
    void set(String key, Object value, long expired)throws Exception;

    /**
     * 获取缓存
     * @param key 缓存的key
     * @return Object
     */
    Object get(String key)throws Exception;

    default Object get(String key, Supplier<CacheObject> supplier)throws Exception{
        if(get(key)==null){
            CacheObject cacheObject = supplier.get();
            set(cacheObject.getKey(),cacheObject.getValue(),cacheObject.getExpired());
            return cacheObject.getValue();
        }else {
            return get(key);
        }
    }

}

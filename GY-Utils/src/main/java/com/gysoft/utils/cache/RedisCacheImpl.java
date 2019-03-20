package com.gysoft.utils.cache;


/**
 * @author 周宁
 * @Date 2018-10-11 19:36
 */
public class RedisCacheImpl implements ICache {

    private final static RedisCacheImpl INSTANCE = new RedisCacheImpl();

    private RedisCacheImpl(){

    }

    public static RedisCacheImpl newInstance(){
        return INSTANCE;
    }

    @Override
    public void set(String key, Object value, long expired)throws Exception {
        RedisClient.setKeyWithExpireTime(key, value, expired);
    }

    @Override
    public Object get(String key)throws Exception {
        return RedisClient.get(key, String.class);
    }

}

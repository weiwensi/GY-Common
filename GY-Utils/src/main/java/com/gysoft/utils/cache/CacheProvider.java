package com.gysoft.utils.cache;

import org.springframework.data.redis.RedisConnectionFailureException;

/**
 * @author 周宁
 * @Date 2018-10-11 19:42
 */
public class CacheProvider {

    /**
     * 读取缓存
     * @param key
     * @param redisCache
     * @return Object
     * @throws Exception
     */
    public static Object readCache(String key,RedisCache redisCache)throws Exception{
        Object result = null;
        if(redisCache.localCacheLoad()){
            result = LocalCacheImpl.newInstance().get(key, () -> CacheObject.builder().key(key).expired(redisCache.expires()).value(RedisClient.get(key,Object.class)).build());
        }else{
            result = RedisCacheImpl.newInstance().get(key);
        }
        return result;
    }

    /**
     * 更新缓存
     * @param key
     * @param obj
     * @param redisCache
     * @throws Exception
     */
    public static void updateCache(String key,Object obj,RedisCache redisCache) throws Exception {
        try {
            RedisCacheImpl.newInstance().set(key,obj,redisCache.expires());
        }catch (RedisConnectionFailureException e){
            e.printStackTrace();
        }finally {
            if(redisCache.localCacheLoad()){
                LocalCacheImpl.newInstance().set(key,obj,redisCache.expires());
            }
        }

    }
}

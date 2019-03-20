package com.gysoft.utils.cache;

import com.gysoft.utils.util.spring.SpringApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 周宁
 * @Date 2018-05-17 13:43
 */
public class RedisClient {

    private static final Object lockObj = new Object();

    private static volatile RedisTemplate<String, Object> redisTemplate = null;

    /**
     * 懒加载初始化redisTemplate
     */
    private static void init() {
        RedisTemplate<String,Object> result = redisTemplate;
        if(result==null){
            synchronized (lockObj){
                if (null == redisTemplate) {
                    redisTemplate = SpringApplicationContext.getApplicationContext().getBean(RedisTemplate.class);
                }
            }
        }
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public static void setKeyWithExpireTime(final String key, final Object value, final long expireTime) {
        init();
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T get(final String key, Class<T> clazz) {
        init();
        return (T) redisTemplate.boundValueOps(key).get();
    }

}

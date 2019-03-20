package com.gysoft.utils.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存对象
 *
 * @author 周宁
 * @Date 2018-10-11 17:31
 */
public class  LocalCacheImpl implements ICache {

    private static final Integer DEFAULT_SIZE = 1024;
    /**
     * 緩存实例
     */
    private final static LocalCacheImpl INSTANCE = new LocalCacheImpl();

    private LocalCacheImpl() {
        cachePool = new ConcurrentHashMap<>(DEFAULT_SIZE);
    }

    public static LocalCacheImpl newInstance() {
        return INSTANCE;
    }

    /**
     * 缓存容器
     */
    private Map<String, CacheObject> cachePool;

    @Override
    public Object get(String key)throws Exception {
        CacheObject cacheObject = cachePool.get(key);
        if (null != cacheObject) {
            long cur = System.currentTimeMillis() / 1000;
            if (cacheObject.getExpired() <= 0 || cacheObject.getExpired() > cur) {
                Object result = cacheObject.getValue();
                return result;
            } else {
                del(key);
            }
        }
        return null;
    }

    @Override
    public void set(String key, Object value, long expired) throws Exception{
        expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key, cacheObject);
    }

    private void del(String key) {
        cachePool.remove(key);
    }

}

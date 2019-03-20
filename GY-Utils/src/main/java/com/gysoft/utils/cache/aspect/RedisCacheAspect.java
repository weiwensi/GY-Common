package com.gysoft.utils.cache.aspect;

import com.alibaba.fastjson.JSON;
import com.gysoft.utils.cache.*;
import com.gysoft.utils.util.EmptyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * @author 周宁
 * @Date 2018-05-17 9:17
 */
@Aspect
@Component
public class RedisCacheAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);

    private final ApplicationContext context;

    @Autowired
    public RedisCacheAspect(ApplicationContext context) {
        this.context = context;
    }

    @Around("@annotation(com.gysoft.utils.cache.RedisCache)")
    public Object loadFromCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = joinPoint.getTarget();
        String methodName = joinPoint.getSignature().getName();
        MethodSignature methodSignature = ((MethodSignature)joinPoint.getSignature());
        Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();
        Method method = object.getClass().getMethod(methodName, parameterTypes);
        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        String key = redisCache.key();
        String keyGenerator = redisCache.keyGenerator();
        checkArgs(key, keyGenerator);
        Class<?> returnType = method.getReturnType();
        if (!key.equals(EmptyUtils.EMPTY_STR)) {
            //do Nothing
        }else{
            CacheKeyGenerator cacheKeyGenerator = (CacheKeyGenerator) context.getBean(keyGenerator);
            key = (String) cacheKeyGenerator.generate(methodSignature.getDeclaringType(), method, joinPoint.getArgs());
        }
        String result = null;
        try{
            result = (String) CacheProvider.readCache(key,redisCache);
        }catch (RedisConnectionFailureException e){
            //redis连接失败不影响程序运行
            return joinPoint.proceed();
        }
        if (result != null) {
            return JSON.parseObject(result, returnType);
        }else{
            return loadFromDbAndUpdateCache(key, joinPoint, redisCache);
        }

    }

    private Object loadFromDbAndUpdateCache(String key, ProceedingJoinPoint joinPoint, RedisCache redisCache) throws Throwable {
        Object result = joinPoint.proceed();
        if (result != null) {
            CacheProvider.updateCache(key,JSON.toJSONString(result),redisCache);
            logger.info("update cache data sucessfully, cacheData = {}", result);
        } else {
            logger.info("no cache data need be cached");
        }
        return result;
    }

    private void checkArgs(String key, String keyGenerator) {
        if (key.equals("") && keyGenerator.equals("")) {
            throw new IllegalArgumentException("must provide a key or keyGenerator");
        }
        if (!key.equals("") && !keyGenerator.equals("")) {
            throw new IllegalArgumentException("Key and keyGenerator can not be used sendTime the same time");
        }
    }

}

package com.gysoft.utils.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口版本管理注解
 * @author 周宁
 * @Date 2018-08-30 11:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ApiVersion {

    /**
     * 接口版本号(对应swagger中的group)
     * @return String[]
     */
    String[] group();

}

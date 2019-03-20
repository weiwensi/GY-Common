package com.gysoft.utils.jdbc.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 实体映射表自定义注解
 * @author 彭佳佳
 * @data 2018年3月7日
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
	/**
	 * 表名称
	 * @return
	 */
	String name() default "";

	/**
	 * 主键字段
	 * @return
	 */
	String pk() default "id";
}

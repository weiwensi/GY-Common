/**
 * 
 */
package com.gysoft.utils.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description: bean 加载器
 * @author DJZ-PJJ
 * @date 2018年4月8日 下午7:56:45
 */
public class  SpringApplicationContext implements ApplicationContextAware{
	
	private static ApplicationContext ctx;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return ctx;
	}
	
	public static <T> T getBean(String beanName){
    	return (T) ctx.getBean(beanName);

    }
	
	public static <T> T getBean(String beanName, Class<T> type){
    	return (T) ctx.getBean(beanName, type);
    }

}

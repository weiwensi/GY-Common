

package com.gysoft.utils.jdbc.pojo;


import java.util.UUID;

/**
 * 主键id生成器
 * @author 彭佳佳
 * @data 2018年3月7日
 */
public class IdGenerator {
    public static String newId() {
        return UUID.randomUUID().toString();
    }

    public static String newShortId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 为账号创建salt
     * @return String
     */
    public static String createSalt(){
        String id = newShortId();
        return id.substring(0,8);
        
    }
    
    public static void main(String[] args) {
    	for(int i=0;i<30;i++) {
    		System.out.println(newShortId());
    	}
	}
}


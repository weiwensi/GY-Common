package com.gysoft.bean.msgpush;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 基础推送消息
 * @author 周宁
 * @Date 2018-09-26 15:18
 */
public interface BasePushMsg {
    /**
     * 收到消息推送的用户
     * @return List<String>
     * @throws Exception
     */
    List<String> getUserNames()throws Exception;

    /**
     * 推送的产品编号
     * @return String
     * @throws Exception
     */
    String getProductNum()throws Exception;

    /**
     * ios设备token
     * @return List<String>
     * @throws Exception
     */
    List<String> getIosDeviceTokens()throws Exception;

    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 获取消息推送时间
     * @return String
     */
    static String getPushTime(){
        return SDF.format(new Date());
    }

    /**
     * 生成动态的唯一Id
     * @return String
     */
    static String getMsgId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}

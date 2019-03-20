package com.gysoft.utils.util.date;

import java.net.URL;
import java.net.URLConnection;

/**
 * @author 周宁
 * @Date 2018-09-14 8:53
 */
public enum MillUnit {

    MILL(1L),
    SECOND(1000L),
    MINUTE(SECOND.getMills() * 60L),
    HOUR(MINUTE.getMills() * 60L),
    DAY(HOUR.getMills() * 24L),
    WEEK(DAY.getMills() * 7L);

    private final long mills;

    MillUnit(long mills) {
        this.mills = mills;
    }

    public long getMills() {
        return mills;
    }

    private static final String WEBURL = "http://www.baidu.com";
    /**
     * 获取网络时间
     * @return long
     */
    public static long getWebsiteTimeMills() {
        try {
            URL url = new URL(WEBURL);// 获取url对象
            URLConnection uc = url.openConnection();// 获取生成连接对象
            uc.connect();// 发出连接请求
            return uc.getDate();// 读取网站日期时间
        } catch (Exception e) {
            return System.currentTimeMillis();
        }
    }
}

package com.gysoft.utils.util.date;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2019-01-30 10:23
 */
public class DateUtils {

    /**
     * 比较两个日期的大小
     * @param start
     * @param end
     * @return int
     */
    public static int dateCompare(String start,String end){
        return start.compareTo(end);
    }

    public static int dateCompare(Date start,Date end){
        return Long.compare(start.getTime(),end.getTime());
    }
}

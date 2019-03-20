package com.gysoft.utils.util.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 日期格式工具类
 *
 * @author 周宁
 * @Date 2018-04-26 9:23
 */
public class DateFormatUtil {

    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyydotMMdotdd = "yyyy.MM.dd";
    public static final String yyyydotMM = "yyyy.MM";
    public static final String yyyyMM = "yyyyMM";

    public static final String yyyy_MM_ddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_ddHHmm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * 格式化毫秒数
     * @param format
     * @param millSeconds
     * @return
     */
    public static String formatMills(String format,Long millSeconds){
        return formatDate(format,new Date(millSeconds));
    }

    /**
     * 格式化Date类型日期
     *
     * @return String
     */
    public static String formatDate(String format, Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return formatLocalDateTime(format, instant.atZone(zoneId).toLocalDateTime());
    }

    /**
     * 格式化LocalDateTime类型日期
     *
     * @param format
     * @return String
     */
    public static String formatLocalDateTime(String format, LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }


    /**
     * 获取某年的所有月份开始的时间戳</br>
     * eg:2017.01.01 00:00:00-2017.12.01 00:00:00时间段的时间戳</br>
     * @author 周宁
     * @param year
     * @throws
     * @version 1.0
     * @return List<Long>
     */
    public static List<Long> getYearMonthMills(int year){
        return getYearMonthMills(year,1,12);
    }

    /**
     * 获取某年指定开始和结束月份的时间戳</br>
     * @param year
     * @param startMonth
     * @param endMonth
     * @return
     */
    public static List<Long> getYearMonthMills(int year,int startMonth,int endMonth){
        List<Long> result = new ArrayList<>();
        if(startMonth<1||endMonth>12){
            throw new RuntimeException("月份应该为【1-12】之间的数字");
        }
        IntStream.range(1,13).filter(value -> value>=startMonth&&value<=endMonth).forEach(value -> result.add(LocalDateTime.of(year,value,1,0,0,0).toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        return result;
    }

    /**
     * 获取两个日期间的所有月份开始时间戳</br>
     * @author 周宁
     * @param startDate
     * @param endDate
     * @throws
     * @version 1.0
     * @return List<Long>
     */
    public static List<Long> getYearMonthMills(Date startDate,Date endDate){
        List<Long> list = new ArrayList<>();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant startInstant = startDate.toInstant();
        Instant endInstant = endDate.toInstant();
        LocalDate startLocalDate = startInstant.atZone(zoneId).toLocalDate();
        LocalDate endLocalDate = endInstant.atZone(zoneId).toLocalDate();
        long distance = ChronoUnit.MONTHS.between(startLocalDate, endLocalDate);
        LocalDateTime startLocalDateTime = LocalDateTime.of(startLocalDate.getYear(),startLocalDate.getMonth(),1,0,0,0);
        Stream.iterate(startLocalDateTime, d -> d.plusMonths(1)).limit(distance + 1).forEach(f -> list.add(f.toEpochSecond(ZoneOffset.of("+8"))*MillUnit.SECOND.getMills()));
        return list;
    }

    public static void main(String[] args) throws ParseException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startDate = sdf.parse("2018-01-01 00:00:00");
		Date endDate = sdf.parse("2019-12-01 00:00:00");
		List<Long> months = getYearMonthMills(startDate,endDate);
		System.out.println(months);
	}
    
}

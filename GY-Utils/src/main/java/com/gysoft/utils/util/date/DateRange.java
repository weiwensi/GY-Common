package com.gysoft.utils.util.date;

import org.apache.commons.lang3.Range;

import java.time.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * 时间范围枚举(今天、昨天、最近三天、最近一周、最近半月、最近一月)
 *
 * @author 周宁
 * @Date 2018-12-07 17:44
 */
public enum DateRange {

    Today("Today") {
        @Override
        protected Instant getStartDate() {
            return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(zoneId).toInstant();
        }
    },

    Yesterday("Yesterday") {
        @Override
        protected Instant getStartDate() {
            return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN).atZone(zoneId).toInstant();
        }

        @Override
        protected Instant getEndDate() {
            return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX).atZone(zoneId).toInstant();
        }

    },

    LastThreeDay("LastThreeDay") {
        @Override
        protected Instant getStartDate() {
            return LocalDateTime.of(LocalDate.now().minusDays(2), LocalTime.MIN).atZone(zoneId).toInstant();
        }

    },

    LastWeek("LastWeek") {
        @Override
        protected Instant getStartDate() {
            return LocalDateTime.of(LocalDate.now().minusDays(6), LocalTime.MIN).atZone(zoneId).toInstant();
        }
    },

    LastHalfMonth("LastHalfMonth") {
        @Override
        protected Instant getStartDate() {
            return LocalDateTime.of(LocalDate.now().minusDays(15), LocalTime.MIN).atZone(zoneId).toInstant();
        }
    },

    LastMonth("LastMonth") {
        @Override
        protected Instant getStartDate() {
            return LocalDateTime.of(LocalDate.now().minusDays(30), LocalTime.MIN).atZone(zoneId).toInstant();
        }
    };

    private final String range;

    DateRange(String range) {
        this.range = range;
    }

    public String getRange() {
        return range;
    }

    /**
     * 抽象时间范围方法
     *
     * @return Range
     */
    public Range<Date> dateRange() {
        return Range.between(instant2Date(getStartDate()), instant2Date(getEndDate()), Comparator.comparingLong(Date::getTime));
    }

    protected abstract Instant getStartDate();

    protected Instant getEndDate() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(zoneId).toInstant();
    }

    /**
     * 将instant转换为date
     *
     * @param instant
     * @return Date
     */
    private Date instant2Date(Instant instant) {
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    private static final ZoneId zoneId = ZoneId.systemDefault();

    /**
     * 选择时间区域
     * @param range 时间区域字符串
     * @return Range<Date>
     */
    public static Range<Date> selectDateRange(String range) {
        return DateRange.valueOf(range).dateRange();
    }
    /**
     *
     * @Description：判断指定名称的枚举类型是否存在
     * @author DJZ-HXF
     * @date 2019/1/4 10:25
     * @param name
     * @return
     * @throws
     */
    public static boolean exists(String name){
        DateRange[] values = DateRange.values();
        for (int i = 0; i < values.length; i++) {
            if(values[i].name().equals(name)){
                return true;
            }
        }
        return false;
    }
}

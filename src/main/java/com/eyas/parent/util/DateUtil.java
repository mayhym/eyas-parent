package com.eyas.parent.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Created by yixuan on 2018/12/27.
 */
@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    }

    /**
     * 格式化日期
     *
     * @param date    当前日期
     * @param pattern 转化格式
     * @return 转化结果
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 格式化日期
     *
     * @param date 当前日期
     * @return 返回结果
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN.YYYY_MM_DD);
    }

    /**
     * 获取日期时间-YYYY_MM_DD_HH_MM_SS
     *
     * @return 返回结果
     */
    public static String getDateTime() {
        return format(new Date(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期-Date
     *
     * @return 返回结果
     */
    public static String getDate() {
        return format(new Date());
    }

    /**
     * 字符串转date类型
     *
     * @param date    the date
     * @param pattern the pattern
     * @return date date
     * @throws ParseException the parse exception
     * @see [相关类/方法]（可选）
     * @since [产品 /模块版本] （可选）
     */
    public static Date toDate(String date, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    /**
     * 字符串转date类型
     *
     * @param date date
     * @param pattern pattern
     * @return Date
     */
    public static Date toDate2(String date, String pattern){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

}

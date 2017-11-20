package com.kaka.base.date;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;

/**
 * joda 日期 工具类
 * @ClassName DateJodaUtil   
 * @author A10353 caowb 
 * @date 2016年12月13日  
 *   
 */
public class DateJodaUtil {
	/**
	 * 定义常用的日期时间格式（未出现的格式直接末尾添加）
	 * 0-----yyyy-MM-dd
	 * 1-----yyyy-MM-dd HH:mm:ss
	 * 2-----yyyyMMdd
	 * 3-----yyyyMMddHHmmss
	 * 4-----yyyy-MM-dd HH:mm
	 * 5-----HH:mm
	 * 6-----HH:mm:ss
	 * 7-----yyyy-MM
	 * 8-----yyyyMMddHHmm
	 * 9-----MMdd
	 * 10-----yyyyMM
	 * 11-----yyyy年MM月dd日
	 * 12-----yyyy.MM.dd
	 * 13-----yyyy.MM.dd HH:mm:ss
	 * 14-----yyyy年MM月
	 */
	public static final String[] FORMATS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMdd",
			"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm", "HH:mm", "HH:mm:ss", "yyyy-MM", "yyyyMMddHHmm",
			"MMdd", "yyyyMM", "yyyy年MM月dd日", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy年MM月" };

	/**
	 * 取得当前时间
	 * 
	 * @return 当前时间
	 */
	public static Date getNow() {
		return DateTime.now().toDate();
	}

	/**
	 * 获取指定格式的当前时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getNowStr(String format) {
		return DateTime.now().toString(format);
	}

	/**
	 * Timestamp转Date
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Date getTimestampToDate(Timestamp timestamp) {
		return getTimeToDate(timestamp.getTime());
	}
	
	/**
	 * 获取当前时间的毫秒值
	 * 
	 * @param timestamp
	 * @return
	 */
	public static long getMillis() {
		return DateTime.now().getMillis();
	}

	/**
	 * Sql Date转Date
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Date getSqlDateToDate(java.sql.Date date) {
		return getTimeToDate(date.getTime());
	}

	/**
	 * 指定Str to time
	 * @param dataStr
	 * @param format
	 * @return
	 */
	public static long getStrToTime(String str, String format) {
		return DateTime.parse(str, DateTimeFormat.forPattern(format)).getMillis();
	}

	/**
	 * 指定Str to Date
	 * @param dataStr
	 * @param format
	 * @return
	 */
	public static Date getStrToDate(String str, String format) {
		return DateTime.parse(str, DateTimeFormat.forPattern(format)).toDate();
	}

	/**
	 * Time转Date
	 * 
	 * @param timestamp
	 * @return
	 */
	public static Date getTimeToDate(long time) {
		return new DateTime(time).toDate();
	}

	/**
	 * 格式化日期成指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		return new DateTime(date).toString(format);
	}

	/**
	 * 格式化当前日期成指定格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(String format) {
		return new DateTime().toString(format);
	}

	/**
	 * 格式化日期成指定格式（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateByFull(Date date) {
		return new DateTime(date).toString(FORMATS[1]);
	}

	/**
	 * 格式化日期成指定格式（yyyy-MM-dd）
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateByShort(Date date) {

		return new DateTime(date).toString(FORMATS[0]);
	}

	/**
	 * 获取当前时间后几月
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date plusMonths(Date date, int num) {
		return new DateTime(date).plusMonths(num).toDate();
	}

	/**
	 * 指定时间的后几天
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date plusDays(Date date, int num) {
		return new DateTime(date).plusDays(num).toDate();
	}

	/**
	 * 
	 * @Title: minusDays 
	 * @Description: 指定时间前几天
	 * @param date
	 * @param num
	 * @return 参数说明
	 * @return Date    返回类型
	 */
	public static Date minusDays(Date date, int num) {
		return new DateTime(date).minusDays(num).toDate();
	}

	/**
	 * 指定时间往后推N月N天
	 * @param date
	 * @param months
	 * @param days
	 * @return
	 */
	public static Date plusMonthsAndDays(Date date, int months, int days) {
		return new DateTime(date).plusMonths(months).plusDays(days).toDate();
	}

	/**
	 * 两个日期相差年数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int yearsBetween(Date start, Date end) {
		return Years.yearsBetween(new DateTime(start), new DateTime(end)).getYears();
	}

	/**
	 * 两个日期相差月数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int monthsBetween(Date start, Date end) {
		return Months.monthsBetween(new DateTime(start), new DateTime(end)).getMonths();
	}

	/**
	 * 两个日期相差天数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int daysBetween(Date start, Date end) {
		return Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
	}

	/**
	 * 获取日期所在月的天数
	 * @Title: dayNumOfMonth 
	 * @param date
	 * @return
	 */
	public static int dayNumOfMonth(Date date) {
		return new DateTime(date).dayOfMonth().getMaximumValue();
	}

	public static void main(String[] args) {

//		Date end = getStrToDate("2017-3-3 9:18:00", "yyyy-MM-dd");
//
//		System.out.println(daysBetween(new Date(), end));
//
//		System.out.println(getTimeToDate(1215619200000L));
		
//		System.out.println(getTimestamp());

		// System.out.println(formatStrToTimestamp("2014年11月11日", FORMATS[11]));
		// new Date().getTime()
		/*
		 * System.out.println(formatDateByShort(null)); System.out.println(getNow());
		 * System.out.println(new Date()); System.out.println(plusMonthsAndDays(new Date(),1,13));
		 */
//		System.out.println(yearsBetween(
//				DateJodaUtil.getStrToDate("2017.01.01", DateJodaUtil.FORMATS[12]), getNow()));
	}

}

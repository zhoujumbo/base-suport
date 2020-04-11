package com.fortunetree.basic.support.commons.business.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static SimpleDateFormat FORMAT_YMDHMS_EASY = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static SimpleDateFormat FORMAT_YMDHMS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final DateFormat FORMAT_YM = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * 时间转换为字符串yyyyMMddHHmmss
	 *
	 * @return
	 */
	public static String ymdhmsFormatEasy() {
		return FORMAT_YMDHMS_EASY.format(new Date());
	}

	/**
	 * 时间转换为字符串yyyyMMddHHmmss
	 *
	 * @return
	 */
	public static String ymdhmsFormatEasy(Date date) {
		return FORMAT_YMDHMS_EASY.format(date);
	}

	/**
	 * 时间转换为字符串yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String ymdhmsFormat(Date date) {
		if(date == null){
			return null;
		}
		return FORMAT_YMDHMS.format(date);
	}

	/**
	 * 时间转换为字符串yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String ymdFormat(Date date) {
		if(date == null){
			return null;
		}
		return DEFAULT_DATEFORMAT.format(date);
	}

	public static Timestamp currentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * @disc 根据日期获取相差的月份
	 * @param date
	 *            :给定的日期
	 * @param days
	 *            :要增加的天数
	 * @return 返回Date对象
	 */
	public static Date getDayAdd(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return new Date(cal.getTime().getTime());
	}

	/**
	 * @disc 根据日期获取相差的月份
	 * @param hour
	 *            :给定的时间
	 * @return 返回Date对象
	 */
	public static String getHourAdd(int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return ymdhmsFormatEasy(new Date(cal.getTime().getTime()));
	}

	public static Date getDateAfterDay(Date somedate, int day) {
		if (somedate == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(somedate);
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTime().getTime());
	}

	public static Timestamp getDateAfterDay(int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, day);
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * 获取两个日期字符串之间的日期集合
	 * @param startTime:String
	 * @param endTime:String
	 * @return list:yyyy-MM-dd
	 */
	public static List<String> getBetweenDate(String startTime, String endTime) throws ParseException {
		// 声明保存日期集合
		List<String> list = new ArrayList<String>();
		try {
			// 转化成日期类型
			Date startDate = DEFAULT_DATEFORMAT.parse(startTime);
			Date endDate = DEFAULT_DATEFORMAT.parse(endTime);

			//用Calendar 进行日期比较判断
			Calendar calendar = Calendar.getInstance();
			while (startDate.getTime()<=endDate.getTime()){
				// 把日期添加到集合
				list.add(DEFAULT_DATEFORMAT.format(startDate));
				// 设置日期
				calendar.setTime(startDate);
				//把日期增加一天
				calendar.add(Calendar.DATE, 1);
				// 获取增加后的日期
				startDate=calendar.getTime();
			}
		} catch (ParseException e) {
			throw e;
		}
		return list;
	}

	/**
	 * 获取两个日期之间所有的月份集合
	 * @param startTime
	 * @param endTime
	 * @return：YYYY-MM
	 */
	public static List<String> getMonthBetweenDate(String startTime, String endTime){
		// 声明保存日期集合
		List<String> list = new ArrayList<String>();
		try {
			// 转化成日期类型
			Date startDate = FORMAT_YM.parse(startTime);
			Date endDate = FORMAT_YM.parse(endTime);

			//用Calendar 进行日期比较判断
			Calendar calendar = Calendar.getInstance();
			while (startDate.getTime()<=endDate.getTime()){
				// 把日期添加到集合
				list.add(FORMAT_YM.format(startDate));
				// 设置日期
				calendar.setTime(startDate);
				//把日期增加一天
				calendar.add(Calendar.MONTH, 1);
				// 获取增加后的日期
				startDate=calendar.getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

}

/*
 * 文 件 名:  DateUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月3日,  All rights reserved  
 */
package cn.leadeon.utils.convert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
/**
 * 处理日期工具类
 * 
 * @author liudongdong
 * @version [1.0, 2014年4月3日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class DateUtil {
	public static final int INTERVAL_DAY = 1;
	public static final int INTERVAL_WEEK = 2;
	public static final int INTERVAL_MONTH = 3;
	public static final int INTERVAL_YEAR = 4;
	public static final int INTERVAL_HOUR = 5;
	public static final int INTERVAL_MINUTE = 6;
	public static final int INTERVAL_SECOND = 7;
	public static final Date tempDate = new Date(new Long("-2177481952000")
			.longValue());

	public static boolean isToday(Date date) {
		Date now = new Date();
		boolean result = true;
		result &= date.getYear() == now.getYear();
		result &= date.getMonth() == now.getMonth();
		result &= date.getDate() == now.getDate();
		return result;
	}

	public static long DaysBetween(Date date1, Date date2) {
		if (date2 == null)
			date2 = new Date();
		long day = (date2.getTime() - date1.getTime()) / 86400000L;
		return day;
	}

	public static boolean compareDate(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);
			return (!(d1.after(d2)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Date dateFormat(String date, String dateFormat) {
		if (date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if (date != null)
			try {
				return format.parse(date);
			} catch (Exception ex) {
			}
		return null;
	}

	/**
	 * nginx时间处理，转换为可以更好识别的时间格式
	 * 
	 * @param nginxDate
	 *            ' 03/Dec/2013:11:48:44 +0800 '
	 * @return
	 */
	public static String dateFormatNginxDate(String nginxDate) {
		if (nginxDate == null || nginxDate.trim().equals(""))
			return null;
		nginxDate = nginxDate.trim().split(" ")[0];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",
				Locale.US);
		TimeZone tz = TimeZone.getTimeZone("GMT+8");
		sdf.setTimeZone(tz);
		String str = sdf.format(Calendar.getInstance().getTime());
		System.out.println(str);
		Date tempTime = null;
		try {
			tempTime = sdf.parse(nginxDate);
			return DateUtil.dateFormat(tempTime, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date dateFormat(String date) {
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateFormat(Date date, String dateFormat) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if (date != null)
			return format.format(date);

		return "";
	}

	public static String birthdayFormat(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = null;
		if (date.before(tempDate))
			format = new SimpleDateFormat("MM-dd");
		else
			format = new SimpleDateFormat("yyyy-MM-dd");

		if (date != null)
			return format.format(date);

		return "";
	}

	public static String dateFormat(Date date) {
		return dateFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static boolean isExpiredDay(Date date1) {
		long day = (new Date().getTime() - date1.getTime()) / 86400000L;

		return (day >= 1L);
	}

	public static Date getYesterday() {
		Date date = new Date();
		long time = date.getTime() / 1000L - 86400L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	/**
	 * 一周前
	 * 
	 * @return
	 */
	public static Date getWeekAgo() {
		Date date = new Date();
		long time = date.getTime() / 1000L - 604800L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	/**
	 * interval天前的数据
	 * 
	 * @param interval
	 * @return
	 */
	public static String getDaysAgo(int interval) {
		Date date = new Date();
		long time = date.getTime() / 1000L - (interval * 60 * 60 * 24);
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.format(date);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	public static Date getTomorrow() {
		Date date = new Date();
		long time = date.getTime() / 1000L + 86400L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	public static Date getBeforeDate(String range) {
		Calendar today = Calendar.getInstance();
		if ("week".equalsIgnoreCase(range))
			today.add(4, -1);
		else if ("month".equalsIgnoreCase(range))
			today.add(2, -1);
		else
			today.clear();
		return today.getTime();
	}

	public static Date getThisWeekStartTime() {
		Calendar today = Calendar.getInstance();
		today.set(7, today.getFirstDayOfWeek());
		Calendar weekFirstDay = Calendar.getInstance();
		weekFirstDay.clear();
		weekFirstDay.set(1, today.get(1));
		weekFirstDay.set(2, today.get(2));
		weekFirstDay.set(5, today.get(5));
		return weekFirstDay.getTime();
	}

	public static String getToday(String format) {
		String result = "";
		try {
			Date today = new Date();
			SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
			result = simpleFormat.format(today);
		} catch (Exception e) {
		}
		return result;
	}

	public static Date getStartDay(int year, int month) {
		Calendar today = Calendar.getInstance();
		today.clear();
		today.set(1, year);
		today.set(2, month - 1);
		today.set(5, 1);
		return today.getTime();
	}

	public static List<Integer> getBeforeYearList(int before) {
		Calendar today = Calendar.getInstance();
		int theYear = today.get(1);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = before; i >= 0; --i)
			list.add(Integer.valueOf(theYear - i));

		return list;
	}

	/**
	 * 一段时间往后 两天后
	 * 
	 * @param interval
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date dateAdd(int interval, Date date, int n) {
		long time = date.getTime() / 1000L;
		switch (interval) {
		case 1:
			time += n * 86400;
			break;
		case 2:
			time += n * 604800;
			break;
		case 3:
			time += n * 2678400;
			break;
		case 4:
			time += n * 31536000l;
			break;
		case 5:
			time += n * 3600;
			break;
		case 6:
			time += n * 60;
			break;
		case 7:
			time += n;
		}

		Date result = new Date();
		result.setTime(time * 1000L);
		return result;
	}

	/**
	 * 两个时间间隔
	 * 
	 * @param interval
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int dateDiff(int interval, Date begin, Date end) {
		long beginTime = begin.getTime() / 1000L;
		long endTime = end.getTime() / 1000L;
		long tmp = 0L;
		if (endTime == beginTime) {
			return 0;
		}

		if (endTime < beginTime) {
			tmp = beginTime;
			beginTime = endTime;
			endTime = tmp;
		}

		long intervalTime = endTime - beginTime;
		long result = 0L;
		switch (interval) {
		case 1:
			result = intervalTime / 86400L;
			break;
		case 2:
			result = intervalTime / 604800L;
			break;
		case 3:
			result = intervalTime / 2678400L;
			break;
		case 4:
			result = intervalTime / 31536000L;
			break;
		case 5:
			result = intervalTime / 3600L;
			break;
		case 6:
			result = intervalTime / 60L;
			break;
		case 7:
			result = intervalTime / 1L;
		}

		if (tmp > 0L)
			result = 0L - result;

		return (int) result;
	}

	public static int getTodayYear() {
		int yyyy = Integer.parseInt(dateFormat(new Date(), "yyyy"));
		return yyyy;
	}

	public static Date getNow() {
		return new Date();
	}

	public static String dateFormatRss(Date date) {
		if (date != null)
			return dateFormat(date, "E, d MMM yyyy H:mm:ss") + " GMT";

		return "";
	}

	public static boolean betweenStartDateAndEndDate(Date startDate,
			Date endDate) {
		boolean bool = false;
		Date curDate = new Date();
		if ((curDate.after(startDate))
				&& (curDate.before(dateAdd(1, endDate, 1)))) {
			bool = true;
		}
		return bool;
	}

	public static boolean nowDateBetweenStartDateAndEndDate(Date startDate,
			Date endDate) {
		boolean bool = false;
		Date curDate = new Date();
		if ((curDate.after(startDate)) && (curDate.before(endDate)))
			bool = true;

		return bool;
	}

	public static boolean nowDateAfterDate(Date date) {
		boolean bool = false;
		Date curDate = new Date();
		if (curDate.after(date))
			bool = true;

		return bool;
	}

	public static int getBetweenTodaysStartDateAndEndDate(Date startDate,
			Date endDate) {
		int betweentoday = 0;
		if (null == startDate || null == endDate)
			return betweentoday;

		if (endDate == null) {
			Calendar calendar = Calendar.getInstance();
			String year = new Integer(calendar.get(1)).toString();
			String month = new Integer(calendar.get(2) + 1).toString();

			String day = new Integer(calendar.get(5)).toString();

			String strtodaytime = year + "-" + month + "-" + day;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				endDate = formatter.parse(strtodaytime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (endDate.after(startDate))
			betweentoday = (int) ((endDate.getTime() - startDate.getTime()) / 86400000L);
		else
			betweentoday = (int) ((startDate.getTime() - endDate.getTime()) / 86400000L);

		return betweentoday;
	}

	public static String getTime(int format) {
		StringBuffer cTime = new StringBuffer(10);
		Calendar time = Calendar.getInstance();
		int miltime = time.get(14);
		int second = time.get(13);
		int minute = time.get(12);
		int hour = time.get(11);
		int day = time.get(5);
		int month = time.get(2) + 1;
		int year = time.get(1);
		if (format != 14)
			if (year >= 2000)
				year -= 2000;
			else
				year -= 1900;

		if (format >= 2)
			if (format == 14)
				cTime.append(year);
			else
				cTime.append(getFormatTime(year, 2));

		if (format >= 4)
			cTime.append(getFormatTime(month, 2));
		if (format >= 6)
			cTime.append(getFormatTime(day, 2));
		if (format >= 8)
			cTime.append(getFormatTime(hour, 2));
		if (format >= 10)
			cTime.append(getFormatTime(minute, 2));
		if (format >= 12)
			cTime.append(getFormatTime(second, 2));
		if (format >= 15)
			cTime.append(getFormatTime(miltime, 3));
		return cTime.toString();
	}

	private static String getFormatTime(int time, int format) {
		StringBuffer numm = new StringBuffer();
		int length = String.valueOf(time).length();
		if (format < length)
			return null;
		for (int i = 0; i < format - length; ++i)
			numm.append("0");

		numm.append(time);
		return numm.toString().trim();
	}

	public static int getUserAge(Date birthday) {
		if (birthday == null)
			return 0;
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthday))
			return 0;

		int yearNow = cal.get(1);
		cal.setTime(birthday);
		int yearBirth = cal.get(1);
		return (yearNow - yearBirth);
	}

	public static Date getDateByUnixTime(int unixTime) {
		return new Date(unixTime * 1000L);
	}

	public static int getUnixTimeByDate(Date date) {
		return (int) (date.getTime() / 1000L);
	}

	/*
	 * public static void main(String[] args) { Date date1 =
	 * dateFormat("2012-02-19", "yyyy-MM-dd"); // Date date2 =
	 * dateFormat("1900-12-31 00:00:00"); String data2 = dateFormat(date1,
	 * "yyyy-MM-dd"); System.out.println(date1.toLocaleString());
	 * System.out.println(data2); }
	 */

	public static Date getNextDay(Date date) {
		long time = date.getTime() / 1000L + 86400L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	public static Date nextDay(Date date) {
		Date newDate = (Date) date.clone();
		long time = newDate.getTime() / 1000L + 86400L;
		newDate.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newDate = format.parse(format.format(newDate));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return newDate;
	}

	public static Date getNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dateStr = dateFormat(date);
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getTomorrow(Date date1) {
		Calendar now = Calendar.getInstance();
		now.setTime(date1);

		now.add(5, 1);
		return now.getTime();
	}

	public static Date getWeekAgo(Date date) {
		Date newDate = (Date) date.clone();
		long time = newDate.getTime() / 1000L - 604800L;
		newDate.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newDate = format.parse(format.format(newDate));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return newDate;
	}

	public static Date getDatebyTime(Date date, int n) {
		String str = dateFormat(date, "yyyy-MM-dd");
		String[] strs = str.split("-");
		int month = Integer.parseInt(strs[1]);
		int monthnow = (month + n) % 12;
		int year = Integer.parseInt(strs[0]) + (month + n) / 12;
		str = String.valueOf(year) + "-" + String.valueOf(monthnow) + "-"
				+ strs[2];

		return dateFormat(str, "yyyy-MM-dd");
	}

	public static Date yesterday(Date date) {
		Date newDate = (Date) date.clone();
		long time = newDate.getTime() / 1000L - 86400L;
		newDate.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newDate = format.parse(format.format(newDate));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return newDate;
	}

	public static Date getYesterday(Date date) {
		long time = date.getTime() / 1000L - 86400L;
		date.setTime(time * 1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(format.format(date));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return date;
	}

	public static String getStringNowTime() {
		Date date = new Date();
		String dateStr = dateFormat(date);

		return dateStr;
	}

	public static long getSpecifyTimeSec(long time, int range) {
		Date date = new Date(
				(time * 1000L + 28800000L) / 86400000L * 86400000L - 28800000L);

		long zeroTime = date.getTime() / 1000L;
		long specifyTime = range * 24 * 3600;
		return (zeroTime + specifyTime);
	}

	public static String formatDateByUnixTime(long unixTime, String dateFormat) {
		return dateFormat(new Date(unixTime * 1000L), dateFormat);
	}

	/**
	 * 得到一段较前的时间
	 * 
	 * @return
	 */

	public static String getLongAgoTime() {
		String time;
		Calendar calendar = Calendar.getInstance();
		calendar.set(1988, 5, 2);
		time = DateUtil.dateFormat(calendar.getTime());
		return time;
	}

	/**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param pTime
	 *            修要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * @Exception 发生异常<br>
	 */
	final static String[] weeks = new String[] { "周日", "周一", "周二", "周三", "周四",
			"周五", "周六" };

	public static String dayForWeek(Date date) {
		// SimpleDateFormat format = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 0;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return weeks[dayForWeek];
	}

	/**
	 * 获取date的凌晨00:00:00的时间 , 传入 2013-10-10 14:12:11 返回2013-10-10 00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayStartTime(Date date) {

		if (date == null) {
			return null;
		}
		Date d = new Date(date.getYear(), date.getMonth(), date.getDate());

		return d;
	}

	/**
	 * 当天的最后一秒的时间 传入2013-10-10 14:12:11 返回2013-10-10 23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayEndTime(Date date) {
		if (date == null) {
			return null;
		}
		Date d = new Date(date.getYear(), date.getMonth(), date.getDate(), 23,
				59, 59);
		return d;
	}

	public static Integer getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到一个月的最后一天
	 * 
	 * @param month
	 * @return
	 */
	public static Integer getLastDayOfMonth(int month) {
		if (month < 0 || month > 12)
			return null;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		int MaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return MaxDay;
	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(dayStartTime(new Date()));
		// System.out.println(dayEndTime(new Date()));
		// System.out.println(DateUtil.getYesterday());
		// System.out.println(DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateFormatNginxDate(" 03/Dec/2013:11:48:44 +0800 "));
	}

}
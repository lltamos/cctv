/*
 * 文 件 名:  InputUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月3日,  All rights reserved  
 */
package cn.leadeon.utils.convert;

/**
 *输入数据类型转换
 * 
 * @author liudongdong
 * @version [1.0, 2014年4月3日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class InputUtil {
	public static Integer safeParseInt(String s, Integer defaultVal) {
		try {
			return Integer.valueOf(Integer.parseInt(s));
		} catch (NumberFormatException e) {
		}
		return defaultVal;
	}

	public static Long safeParseLong(String s, Long defaultVal) {
		try {
			return Long.valueOf(Long.parseLong(s));
		} catch (NumberFormatException e) {
		}
		return defaultVal;
	}

	public static Float safeParseFloat(String s, Float defaultVal) {
		try {
			return Float.valueOf(Float.parseFloat(s));
		} catch (NumberFormatException e) {
		}
		return defaultVal;
	}

	public static String safeParseString(String s, String defaultVal) {
		try {
			if (s == null)
				return defaultVal;

			if ((s.equals("null")) || (s.equals(" ")))
				return defaultVal;

			return s;
		} catch (NumberFormatException e) {
		}
		return defaultVal;
	}
}
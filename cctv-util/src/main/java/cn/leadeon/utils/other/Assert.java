/*
 * 文 件 名:  Assert.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;

import org.apache.commons.lang.StringUtils;

/**
 * This class provides object-suite variables.
 * 
 * @author liudongdong
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class Assert {
	

	/**
	 * Assert that an object is <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.isNull(value, &quot;The value must be null&quot;);
	 * </pre>
	 * 
	 * @param object
	 *            the object to check
	 * @return boolean true or false
	 */
	public static boolean isNull(Object object) {
		return (object == null) ? true : false;
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.isNotNull(value, &quot;The value must be not null&quot;);
	 * </pre>
	 * 
	 * @param object
	 *            the object to check
	 * @return boolean true or false
	 */
	public static boolean isNotNull(Object object) {
		return !isNull(object);
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.release(clazz);
	 * </pre>
	 * 
	 * @param object
	 *            the object to release
	 */
	public static void release(Object object) {
		if (isNotNull(object)) {
			object = null;
		}
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * 
	 * <pre class="code">
	 * Assert.toInteger(&quot;12&quot;);
	 * </pre>
	 * 
	 * @param value
	 *            the object to release
	 * @throws KmsException
	 *             if the object is not number.
	 */
	public static Integer toInteger(String value) {
		Integer id = null;
		try {
			id = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static Long toLong(String value) {
		Long id = null;
		try {
			id = Long.parseLong(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * @param message
	 * @return
	 */
	private static String isEmpty(String message) {
		if (StringUtils.isEmpty(message)) {
			message = "[Assertion failed] - this argument is required; it must not be null";
		}
		return message;
	}

}

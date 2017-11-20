/*
 * 文 件 名:  CheckUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常见数据格式校验
 * 
 * @author zhx
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class CheckUtil {
	/**
	 * 身份证校验位
	 */
	public static String[] CHECK_DIGIT = { "1", "0", "X", "9", "8", "7", "6",
			"5", "4", "3", "2" };

	/**
	 * 身份证加权因子
	 */
	public static int[] gene = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,
			4, 2, 1 };

	/**
	 * 获得一个字符串的长度，一个汉字按2个长度算
	 * 
	 * @param str
	 * @return
	 */
	public static int getLengthAsLetter(String str) {
		int len = 0;
		int strLength = str.length();
		int code;
		for (int i = 0; i < strLength; i++) {
			code = str.charAt(i);
			if (code > 0 && code < 257) {
				len++;
			} else {
				len += 2;
			}
		}
		return len;
	}

	/**
	 * 随机生成6位包括数字和字母的字符
	 * 
	 * @return
	 */
	public static String randomMath() {
		char[] character = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };

		StringBuffer randomStr = new StringBuffer();

		for (int n = 0; n < 6; n++) {
			randomStr.append(character[Math.abs(new Random()
					.nextInt(character.length)) % 62]);
		}

		return randomStr.toString();
	}

	/**
	 * 判断是否为email
	 * 
	 * @param email
	 * @return 是email返回true; 不是email返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.equals(""))
			return false;
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		Matcher isMail = pattern.matcher(email);
		return isMail.matches();

	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param num
	 * @return 是数字返回true; 不是数字返回false
	 */
	public static boolean isNum(String num) {
		if (num == null || num.equals(""))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(num);
		return isNum.matches();
	}

	/**
	 * 判断一个字符串是否为合法的字符
	 * 
	 * @param temp
	 * @return
	 */
	public static boolean isLegalityLetter(String temp) {
		if (temp == null)
			return false;
		if (temp.equals(""))
			return true;
		Pattern pattern = Pattern.compile("^\\w+$");
		Matcher isLegalityLetter = pattern.matcher(temp);
		return isLegalityLetter.matches();

	}

	/**
	 * 判断是否为用户名
	 * 
	 * @param lName
	 * @return
	 */
	public static boolean isLoginName(String lName) {
		if (lName == null || lName.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^[\\w@\\.-]{8,26}$");
		Matcher isLoginName = pattern.matcher(lName);
		return isLoginName.matches();
	}

	/**
	 * 判断是否为手机号
	 */
	public static boolean isMobile(String mo) {
		if (mo == null || mo.equals(""))
			return false;
		Pattern pattern = Pattern.compile("^1[35]\\d{9}$");
		Matcher isMobile = pattern.matcher(mo);
		return isMobile.matches();
	}

	/**
	 * 判断是否为身份证号^(\d{15}|(\d{17}[xX\d]))$
	 */
	public static boolean isIdentityCard(String card) {
		if (card == null || card.equals(""))
			return false;
		if (card.length() != 15 && card.length() != 18)
			return false;
		Pattern pattern = Pattern.compile("^(\\d{15}|(\\d{17}[xX\\d]))$");
		Matcher isIdentityCard = pattern.matcher(card);
		if (!isIdentityCard.matches())
			return false;
		if (card.length() == 18) {
			int yearPrefix = Integer.parseInt(card.substring(6, 8));
			if (yearPrefix < 19 || yearPrefix > 21)
				return false;// 出生日期必须大于1900年小于2100年
			int month = Integer.parseInt(card.substring(10, 12));
			if (month > 12 || month == 0)
				return false; // 验证月
			int day = Integer.parseInt(card.substring(12, 14));
			if (day > 31 || day == 0)
				return false; // 验证日
			String checkDigit = getCheckDigitFor18(card);
			if (checkDigit.equals("-1"))
				return false;
			if (checkDigit.equals(card.substring(17, 18).toUpperCase())) {
				return true;
			} else {
				return false;
			}
		} else if (card.length() == 15) {
			int month = Integer.parseInt(card.substring(8, 10));
			if (month > 12 || month == 0)
				return false; // 验证月
			int day = Integer.parseInt(card.substring(10, 12));
			if (day > 31 || day == 0)
				return false;
			return true;
		}
		return false;
	}

	private static String getCheckDigitFor18(String card) {
		if (card == null || card.equals(""))
			return "-1";
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(card.substring(i, i + 1)) * gene[i];
		}
		return CHECK_DIGIT[sum % 11];
	}

	/**
	 * 随机字符串生成
	 * 
	 * @return
	 */
	/*
	public static String getRandmStr(int length) {
		char[] tempCs = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
				'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's',
				'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b',
				'n', 'm' };
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(tempCs[Math.abs(random.nextInt()) % tempCs.length]);
		}
		return sb.toString();
	}
	 */
}

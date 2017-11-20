package cn.leadeon.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import cn.leadeon.constant.ComVariable;
import cn.leadeon.utils.other.UtilDateTime;

/**
 * 
 * 公共基础工具类 <功能详细描述>
 * 
 * @author liujie
 * @version [版本号, 2014-12-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseCommonTool {
	// private static final Logger logger = LogManager.getLogger(BaseCommonTool.class);
	// 纯数字
	private static Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");
	private static Pattern numericStringPattern = Pattern
			.compile("^[0-9\\-\\-]+$");
	private static Pattern floatNumericPattern = Pattern
			.compile("^[0-9\\-\\.]+$");
	private static Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");
	public static final String splitStrPattern = ",|，|;|；|、|\\.|。|-|_|\\(|\\)|\\[|\\]|\\{|\\}|\\\\|/| |　|\"";
	public static final Pattern chineseStrPattern = Pattern
			.compile("[\\u4e00-\\u9fa5]");

	public static final Pattern emailerPattern = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	public static final Pattern idCarePattern = Pattern
			.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");

	public static final Pattern fmsIcludePattern = Pattern
			.compile("<jsp:include\\s+page=\"[\\w\\d-_\\.\\/]{20,120}\"></jsp:include>");
	
	public static final Pattern cellNumPattern = Pattern
			.compile("^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8]|18[2])[0-9]{8}$");
	
	//是否正整数
	public static final Pattern intPattern = Pattern
	.compile("^[0-9]*[1-9][0-9]*$");

	/* 为空检查 */
	public static String replaceNull(String InStr) {
		String resultStr = "";
		if (InStr != null) {
			resultStr = InStr;
		}

		return resultStr;
	}

	/* 获取map元素值 */
	public static String getMapElem(Map<String, String> map, String key) {
		String value = null;

		if ((map != null) && (key != null)) {
			if ((value = map.get(key)) == null) {
				value = "";
			}
		}

		return value;
	}

	/* 获取xml ELEM 子元素值 */
	public static String getChildElemValue(Element eParent, String childName) {
		String value = "";
		Element eChild = null;

		if ((eParent != null) && (childName != null)) {
			if ((eChild = eParent.element(childName)) != null) {
				value = eChild.getStringValue();
			}
		}

		return value;
	}

	/* 判断号码是否为电话号码 */
	public static boolean isTelNum(String number) {
		boolean result = true;
		if (StringUtils.isEmpty(number)
				|| !(ComVariable.PHONE_LEN == number.length())
				|| !StringUtils.isNumeric(number)) {
			result = false;
		}
		return result;
	}

	/* 判断号码是否为密码或随机码 */
	public static boolean isPwd(String number) {
		boolean result = true;
		if (StringUtils.isEmpty(number)
				|| !(ComVariable.PHONE_LEN == number.length())
				|| !StringUtils.isNumeric(number)) {
			result = false;
		}
		return result;
	}

	/* yyyyMMddhhmmss -> yyyy-MM-dd hh:mm:ss */
	public static String toDate(String date) {
		return new String(date.substring(0, 4) + "-" + date.substring(4, 6)
				+ "-" + date.substring(6, 8) + " " + date.substring(8, 10)
				+ ":" + date.substring(10, 12) + ":" + date.substring(12, 14));
	}

	/**
	 * 日期格式转换 
	 * 
	 * @param d
	 * @return 日期
	 */
	public static String toDateString(String oldDate, String oldDatePattern,
			String newDatePattern) {
		DateFormat oldformat = new SimpleDateFormat(oldDatePattern);
		String newDate = "";
		try {
			newDate = UtilDateTime.toDateString(oldformat.parse(oldDate),
					newDatePattern);
		} catch (ParseException e) {
			// 转换异常，返回时间为空
			return newDate;
		}
		return newDate;
	}

	/* 存储String对象转MAP<String, String> */
	public static HashMap<String, String> objectToMap(Object object)
			throws Exception {
		HashMap<String, String> Map = null;
		if (object != null) {
			Map = new HashMap<String, String>();

			// 泛型编程，将类属性及取值放置与map中
			BeanInfo info = Introspector.getBeanInfo(object.getClass());

			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				// 内容为null的过滤掉
				if (reader == null || reader.invoke(object) == null) {
					continue;
				}
				// 默认继承Object类的属性，过滤掉
				if (pd.getName().equalsIgnoreCase("class")) {
					continue;
				}

				Map.put(pd.getName(), (String) reader.invoke(object));
			}
		}

		return Map;
	}

	/**
	 * 判断是否数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isNumeric(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = numericPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 判断是否邮箱表示
	 * 
	 * @param src
	 * @return
	 */

	public static boolean isEmails(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = emailerPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 判断是否数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isNumericString(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = numericStringPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 判断是不是中文表示
	 */
	public static boolean isChineseString(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = chineseStrPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 从一段字符串中提取出中文
	 */
	public static String getChineseString(String src) {
		StringBuffer result = new StringBuffer("result:");
		if ((src != null) && (src.length() > 0)) {
			Matcher m = numericStringPattern.matcher(src);
			while (m.find())
				result.append(m.group()).append(" "); 
		}

		return result.toString();
	}

	public static void main(String[] args) {
		// getChineseString("<p><img src='http://photo.weke.com/user_image/shopcase/2013-02-26/97821361858294849.jpg' title='安浓卡：安全农产品，安心农家乐！礼品卡福利卡专家-安浓卡商??jpg' /><br /></p><p>21世纪，是互联网的时代，是信息高速传递的时代！企业要保持与时代步伐发展一致，必须要时刻保持信息的高速传递性！无论从小企业到大企业，能将信息第一时间传递到广大民众面前，就能抓住发展机遇！</p><p>21世纪，又是一个信息泛滥的时代！街头巷尾，网上网下，随处都是无法捕捉的信息！那么又如何在这个信息泛滥的时代，让茫茫人海中的你，被民众所发现呢！就需要明智的选择信息发布平台，专业的信息管理策略！这都是需要真正的信息化人才，才能做到！而真正的信息化人才，目前又是可遇不可求的尴尬现状！</p><p>在这里就需要一个平衡点，来平衡这些不对称的供求现状！</p><p>酷科集中了大量专业的信息化管理人才！以&quot;使中国企业都能走向信息化的光辉道路!&quot;为使命！专业为企业提供各种信息化服务！</p><p>其中以网站建设，网络推广最为受到广大企业的青睐！</p><p>网站，实为互联网信息化服务的基础！任何网络信息的推广，展示与管理都离不开这个基础！如何寻找优秀的网站建设服务商，是当今许多企业都存在的头疼问题！</p><p>酷科建站的四大优势：</p><p><span class="Apple-tab-span" style="white-space:pre"></span>1，性价比！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>同样的价格，比质量！同样的质量，比价格！酷科以企业为中心，尽可能为企业减少花销！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>2，专业化管理！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>酷科严格执行专业化信息管理策略！所有信息严格归类存档！即使更换工作人员！企业得到的服务都是一样的专业，统一的完善！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>3，诚信售后！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>酷科坚信，合作的完成，才真正是服务的开始！酷科完善的售后服务，杜绝了合作完成后，企业再也找不到人的恶劣结局！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>4，终生顾问！</p><p><span class="Apple-tab-span" style="white-space:pre"></span>酷科本着&quot;使中国企业都能走向信息化的光辉道路&quot;为使命！以合作双赢为前提，以终身合作为目标！为企业提供终生顾问式的信息咨询服务！让企业感受到，有酷科，就放心！的顾问式服务！</p><p>酷科建站的四大优势，完善的解决了，企业无法正确选择网站建设服务商的尴尬！无论网络公司有多少营销人员，规模有多大，如果做不到这些，企业就无法得到最优质的建站服务！　</p><p><span class='Apple-tab-span' style='white-space:pre'></span></p><p><span class='Apple-tab-span' style='white-space:pre'></span></p><p><span class='Apple-tab-span' style='white-space:pre'></span></p><p><span class='Apple-tab-span' style='white-space:pre'></span></p><p><span class='Apple-tab-span'>"));
		// System.out.println(isUTF8Chinese("曲丶末莜"));

	}

	/**
	 * 判断是否为UTF8的中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUTF8Chinese(String str) {
		if (str == null) {
			return false;
		}
		char UTR8_MAX_VALUE = '\u9fa5';
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (str.charAt(i) > UTR8_MAX_VALUE) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否纯字母组合
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否纯字母组合的标志
	 */
	public static boolean isABC(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = abcPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 只保留字符串数组中的字母
	 * 
	 * @param str
	 *            目标字符串数组
	 */
	public static String stringFilter(String str) {
		if (!(isEmpty(str))) {
			StringBuffer tempStr = new StringBuffer("");
			for (int i = 0; i < str.length(); ++i)
				if (Character.isLetter(str.charAt(i)))
					tempStr.append(str.charAt(i));

			return tempStr.toString();
		}
		return str;
	}

	/**
	 * 判断是否浮点数字表示
	 * 
	 * @param src
	 *            源字符串
	 * @return 是否数字的标志
	 */
	public static boolean isFloatNumeric(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = floatNumericPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 把string array or list用给定的符号symbol连接成一个字符串 比如{a,b,c}----(axbxcx)
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	public static String joinString(List array, String symbol) {
		String result = "";
		if (array != null) {
			for (int i = 0; i < array.size(); ++i) {
				String temp = array.get(i).toString();
				if ((temp != null) && (temp.trim().length() > 0))
					result = result + temp + symbol;
			}
			if (result.length() > 1)
				result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * 
	 * @Description:字节高低位转换
	 * @param str
	 * @param len
	 * @param big
	 * @return byte[]
	 */
	public static byte[] byteConversion(byte[] str, int len, boolean is) {
		byte[] res = new byte[len];
		for (int i = 0; i < len; ++i) {
			res[i] = str[i];
		}

		if (!(is))
			for (int i = 0; i < len; ++i) {
				byte b = str[i];
				res[(len - i - 1)] = b;
			}

		return res;
	}

	/**
	 * 截取制定的字符串长度剩余的加...
	 */
	public static String subStringNotEncode(String subject, int size) {
		if ((subject != null) && (subject.length() > size))
			subject = subject.substring(0, size) + "...";

		return subject;
	}

	/**
	 * 截取字符串　超出的字符用symbol代替 　　
	 * 
	 * @param len
	 *            　字符串长度　长度计量单位为一个GBK汉字　　两个英文字母计算为一个单位长度
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String getLimitLengthString(String str, int len, String symbol) {
		int iLen = len * 2;
		int counterOfDoubleByte = 0;
		String strRet = "";
		try {
			if (str != null) {
				byte[] b = str.getBytes("GBK");
				if (b.length <= iLen) {
					return str;
				}
				for (int i = 0; i < iLen; i++) {
					if (b[i] < 0) {
						counterOfDoubleByte++;
					}
				}
				if (counterOfDoubleByte % 2 == 0) {
					strRet = new String(b, 0, iLen, "GBK") + symbol;
					return strRet;
				} else {
					strRet = new String(b, 0, iLen - 1, "GBK") + symbol;
					return strRet;
				}
			} else {
				return "";
			}
		} catch (Exception ex) {
			return str.substring(0, len);
		} finally {
			strRet = null;
		}
	}

	/**
	 * 截取字符串　超出的字符用symbol代替 　　
	 * 
	 * @param len
	 *            　字符串长度　长度计量单位为一个GBK汉字　　两个英文字母计算为一个单位长度
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String getLimitLengthString(String str, int len) {
		return getLimitLengthString(str, len, "...");
	}

	/**
	 * 
	 * 截取字符，不转码
	 * 
	 * @param subject
	 * @param size
	 * @return
	 */
	public static String subStrNotEncode(String subject, int size) {
		if (subject.length() > size)
			subject = subject.substring(0, size);

		return subject;
	}

	/**
	 * 把string array or list用给定的符号symbol连接成一个字符串
	 * 
	 * @param array
	 * @param symbol
	 * @return
	 */
	public static String joinString(String[] array, String symbol) {
		String result = "";
		if (array != null) {
			for (int i = 0; i < array.length; ++i) {
				String temp = array[i];
				if ((temp != null) && (temp.trim().length() > 0))
					result = result + temp + symbol;
			}
			if (result.length() > 1)
				result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * 取得字符串的实际长度（考虑了汉字的情况）
	 * 
	 * @param SrcStr
	 *            源字符串
	 * @return 字符串的实际长度
	 */
	public static int getStringLen(String SrcStr) {
		char[] theChars;
		int i;
		int return_value = 0;
		if (SrcStr != null) {
			theChars = SrcStr.toCharArray();
			for (i = 0; i < theChars.length; ++i)
				return_value += ((theChars[i] <= 255) ? 1 : 2);
		}

		return return_value;
	}

	/**
	 * 检查联系人信息是否填写，电话，手机，email必须填至少一个，email填了的话检查格式
	 * 
	 * @param phoneCity
	 * @param phoneNumber
	 * @param phoneExt
	 * @param mobileNumber
	 * @param email
	 * @return
	 */
	public static boolean checkContactInfo(String phoneCity,
			String phoneNumber, String phoneExt, String mobileNumber,
			String email) {
		String result = ((phoneCity == null) ? "" : phoneCity.trim())
				+ ((phoneNumber == null) ? "" : phoneNumber.trim())
				+ ((phoneExt == null) ? "" : phoneExt.trim())
				+ ((mobileNumber == null) ? "" : mobileNumber.trim())
				+ ((email == null) ? "" : email.trim());

		if (result.length() < 1)
			return false;

		return (isEmails(email));
	}

	/**
	 * 检查数据串中是否包含非法字符集(非法字符串是指包含了单引号双引号的字符串)
	 * 
	 * @param str
	 * @return [true]|[false] 包含|不包含
	 */
	public static boolean check(String str) {
		String sIllegal = "'\"";
		int len = sIllegal.length();
		if (null == str)
			return false;
		for (int i = 0; i < len; ++i)
			if (str.indexOf(sIllegal.charAt(i)) != -1)
				return true;

		return false;
	}

	/***************************************************************************
	 * getHideEmailPrefix - 隐藏邮件地址前缀。
	 * 
	 * @param email
	 *            - EMail邮箱地址 例如: linwenguo@koubei.com 等等...
	 * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
	 * @version 1.0 (2006.11.27) Wilson Lin
	 **************************************************************************/
	public static String getHideEmailPrefix(String email) {
		if (null != email) {
			int index = email.lastIndexOf(64);
			if (index > 0)
				email = repeat("*", index).concat(email.substring(index));
		}

		return email;
	}

	/***************************************************************************
	 * repeat - 通过源字符串重复生成N次组成新的字符串。
	 * 
	 * @param src
	 *            - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
	 * @param num
	 *            - 重复生成次数
	 * @return 返回已生成的重复字符串
	 * @version 1.0 (2006.10.10) Wilson Lin
	 **************************************************************************/
	public static String repeat(String src, int num) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < num; ++i)
			s.append(src);
		return s.toString();
	}

	/**
	 * 检查是否是email, when null or '' return true
	 * 
	 * @param email
	 * @return
	 */
	// public static boolean isEmail(String email) {
	// if ((email != null) && (email.trim().length() > 0)) {
	// return (TextUtils.verifyEmail(email));
	// }
	//
	// return false;
	// }

	/**
	 * 根据指定的字符把源字符串分割成一个数组
	 * 
	 * @param src
	 * @return
	 */
	public static List<String> parseString2ListByCustomerPattern(
			String pattern, String src) {
		if (src == null)
			return null;
		List list = new ArrayList();
		String[] result = src.split(pattern);
		for (int i = 0; i < result.length; ++i)
			list.add(result[i]);

		return list;
	}

	/**
	 * 根据指定的字符把源字符串分割成一个数组
	 * 
	 * @param src
	 * @return
	 */
	public static List<String> parseString2ListByPattern(String src) {
		String pattern = "，|,|、|。";
		return parseString2ListByCustomerPattern(pattern, src);
	}

	/**
	 * 格式化一个float
	 * 
	 * @param format
	 *            要格式化成的格式 such as #.00, #.#
	 */
	public static String formatFloat(float f, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(f);
	}

	/**
	 * 判断是否是空字符串 null和"" 都返回 true
	 * 
	 * @author Robin Chang
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return ((s == null) || ("".equals(s)));
	}
	
	/**
	 * 判断是否为空字符串null、""、" " 都返回true
	 * 
	 * @author Xuexiaohua at 2015-05-26
	 * @param s
	 * @return
	 */
	public static boolean isEmptyAndNull(String s)
	{
		return ((null == s) || ("".equals(s.trim())));
	}

	/**
	 * 自定义的分隔字符串函数 例如: 1,2,3 =>[1,2,3] 3个元素 ,2,3=>[,2,3] 3个元素 ,2,3,=>[,2,3,]
	 * 4个元素 ,,,=>[,,,] 4个元素
	 * 
	 * 5.22算法修改，为提高速度不用正则表达式 两个间隔符,,返回""元素
	 * 
	 * @param split
	 *            分割字符 默认,
	 * @param src
	 *            输入字符串
	 * @return 分隔后的list
	 * @author Robin
	 */
	public static List<String> splitToList(String split, String src) {
		if (null == src || "".equals(src))
			return null;
		// 默认,
		String sp = ",";
		if (split != null && split.length() == 1) {
			sp = split;
		}
		List<String> r = new ArrayList<String>();
		int lastIndex = -1;
		int index = src.indexOf(sp);
		if (-1 == index && src != null) {
			r.add(src);
			return r;
		}
		while (index >= 0) {
			if (index > lastIndex) {
				r.add(src.substring(lastIndex + 1, index));
			} else {
				r.add("");
			}

			lastIndex = index;
			index = src.indexOf(sp, index + 1);
			if (index == -1) {
				r.add(src.substring(lastIndex + 1, src.length()));
			}
		}
		return r;
	}

	/**
	 * 把 名=值 参数表转换成字符串 (a=1,b=2 =>a=1&b=2)
	 * 
	 * @param map
	 * @return
	 */
	public static String linkedHashMapToString(LinkedHashMap<String, String> map) {
		if ((map != null) && (map.size() > 0)) {
			String result = "";
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String name = (String) it.next();
				String value = (String) map.get(name);
				result = result + ((result.equals("")) ? "" : "&");
				result = result
						+ String.format("%s=%s", new Object[] { name, value });
			}
			return result;
		}
		return null;
	}

	/**
	 * 解析字符串返回 名称=值的参数表 (a=1&b=2 => a=1,b=2)
	 * 
	 * @see test.koubei.util.StringUtilTest#testParseStr()
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> toLinkedHashMap(String str) {
		if ((str != null) && (!(str.equals(""))) && (str.indexOf("=") > 0)) {
			LinkedHashMap result = new LinkedHashMap();

			String name = null;
			String value = null;
			int i = 0;
			while (i < str.length()) {
				char c = str.charAt(i);
				switch (c) {
				case '=':
					value = "";
					break;
				case '&':
					if ((name != null) && (value != null)
							&& (!(name.equals(""))))
						result.put(name, value);

					name = null;
					value = null;
					break;
				default:
					if (value != null)
						value = "" + c;
					else
						name = "" + c;
				}

				++i;
			}

			if ((name != null) && (value != null) && (!(name.equals("")))) {
				result.put(name, value);
			}

			return result;
		}

		return null;
	}

	/**
	 * 根据输入的多个解释和下标返回一个值
	 * 
	 * @param captions
	 *            例如:"无,爱干净,一般,比较乱"
	 * @param index
	 *            1
	 * @return 一般
	 */
	public static String getCaption(String captions, int index) {
		if ((index > 0) && (captions != null) && (!(captions.equals("")))) {
			String[] ss = captions.split(",");
			if ((ss != null) && (ss.length > 0) && (index < ss.length))
				return ss[index];
		}

		return null;
	}

	/**
	 * 数字转字符串,如果num<=0 则输出"";
	 * 
	 * @param num
	 * @return
	 */
	public static String numberToString(Object num) {
		if (num == null)
			return null;
		if ((num instanceof Integer) && (((Integer) num).intValue() > 0))
			return Integer.toString(((Integer) num).intValue());
		if ((num instanceof Long) && (((Long) num).longValue() > 0L))
			return Long.toString(((Long) num).longValue());
		if ((num instanceof Float) && (((Float) num).floatValue() > 0.0F))
			return Float.toString(((Float) num).floatValue());
		if ((num instanceof Double) && (((Double) num).doubleValue() > 0.0D))
			return Double.toString(((Double) num).doubleValue());

		return "";
	}

	/**
	 * 货币转字符串
	 * 
	 * @param money
	 * @param style
	 *            样式 [default]要格式化成的格式 such as #.00, #.#
	 * @return
	 */

	public static String moneyToString(Object money, String style) {
		if ((money != null) && (style != null)
				&& (((money instanceof Double) || (money instanceof Float)))) {
			Double num = (Double) money;

			if (style.equalsIgnoreCase("default")) {
				if (num.doubleValue() == 0.0D) {
					return "";
				}
				if (num.doubleValue() * 10.0D % 10.0D == 0.0D) {
					return Integer.toString(num.intValue());
				}

				return num.toString();
			}

			DecimalFormat df = new DecimalFormat(style);
			return df.format(num);
		}

		return null;
	}

	/**
	 * 在sou中是否存在finds 如果指定的finds字符串有一个在sou中找到,返回true;
	 * 
	 * @param sou
	 * @param find
	 * @return
	 */
	public static boolean strPos(String sou, String[] finds) {
		int i;
		if ((sou != null) && (finds != null) && (finds.length > 0))
			for (i = 0; i < finds.length; ++i)
				if (sou.indexOf(finds[i]) > -1)
					return true;

		return false;
	}

	public static boolean strPos(String sou, List<String> finds) {
		if ((sou != null) && (finds != null) && (finds.size() > 0))
			for (String s : finds)
				if (sou.indexOf(s) > -1)
					return true;

		return false;
	}

	public static boolean strPos(String sou, String finds) {
		List t = splitToList(",", finds);
		return strPos(sou, t);
	}

	/**
	 * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equals(String s1, String s2) {
		if ((isEmpty(s1)) && (isEmpty(s2)))
			return true;
		if ((!(isEmpty(s1))) && (!(isEmpty(s2))))
			return s1.equals(s2);

		return false;
	}

	public static int toInt(String s) {
		if ((s != null) && (!("".equals(s.trim()))))
			try {
				return Integer.parseInt(s);
			} catch (Exception e) {
				return 0;
			}

		return 0;
	}

	public static double toDouble(String s) {
		if ((s != null) && (!("".equals(s.trim()))))
			return Double.parseDouble(s);

		return 0.0D;
	}

	public static boolean isPhone(String phone) {
		int i;
		if ((null == phone) || ("".equals(phone)))
			return false;

		String[] strPhone = phone.split("-");
		try {
			for (i = 0; i < strPhone.length; ++i)
				Long.parseLong(strPhone[i]);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 把xml 转为object
	 * 
	 * @param xml
	 * @return
	 */
	public static Object xmlToObject(String xml) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(
					xml.getBytes("UTF8"));

			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
			return decoder.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long toLong(String s) {
		try {
			if ((s != null) && (!("".equals(s.trim()))))
				return Long.parseLong(s);
		} catch (Exception exception) {
		}
		return 0L;
	}

	public static String simpleEncrypt(String str) {
		if ((str != null) && (str.length() > 0)) {
			str = str.replaceAll("1", "b");

			str = str.replaceAll("3", "d");

			str = str.replaceAll("5", "f");
			str = str.replaceAll("6", "g");
			str = str.replaceAll("7", "h");
			str = str.replaceAll("8", "i");
			str = str.replaceAll("9", "j");
		}
		return str;
	}

	/**
	 * 过滤用户输入的URL地址（防治用户广告） 目前只针对以http或www开头的URL地址
	 * 本方法调用的正则表达式，不建议用在对性能严格的地方例如:循环及list页面等
	 * 
	 * @author fengliang
	 * @param str
	 *            需要处理的字符串
	 * @return 返回处理后的字符串
	 */
	public static String removeURL(String str) {
		if (str != null)
			str = str.toLowerCase()
					.replaceAll("(http|www|com|cn|org|\\.)+", "");

		return str;
	}

	/**
	 * 检查字符串是否属于手机号码
	 * 
	 * @author Peltason
	 * @date 2007-5-9
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		if ((str == null) || (str.equals("")))
			return false;
		if ((str.length() != 11) || (!(isNumeric(str)))) {
			return false;
		}

		return ((str.substring(0, 2).equals("13"))
				|| (str.substring(0, 2).equals("15")) || (str.substring(0, 2)
				.equals("18"))) || (str.substring(0, 2).equals("14"));
	}

	/**
	 * Wap页面的非法字符检查
	 * 
	 * @author hugh115
	 * @date 2007-06-29
	 * @param str
	 * @return
	 */
	public static String replaceWapStr(String str) {
		if (str != null) {
			str = str.replaceAll("<span class=\"keyword\">", "");
			str = str.replaceAll("</span>", "");
			str = str.replaceAll("<strong class=\"keyword\">", "");
			str = str.replaceAll("<strong>", "");
			str = str.replaceAll("</strong>", "");

			str = str.replace('$', '＄');

			str = str.replaceAll("&amp;", "＆");
			str = str.replace('&', '＆');

			str = str.replace('<', '＜');

			str = str.replace('>', '＞');

		}
		return str;
	}

	/**
	 * 字符串转float 如果异常返回0.00
	 * 
	 * @param s
	 *            输入的字符串
	 * @return 转换后的float
	 */
	public static Float toFloat(String s) {
		try {
			return Float.valueOf(Float.parseFloat(s));
		} catch (NumberFormatException e) {
		}
		return new Float(0.0F);
	}

	/**
	 * 页面中去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @author shazao
	 * @date 2007-08-17
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
		}
		return str;
	}

	/**
	 * 全角生成半角
	 * 
	 * @author bailong
	 * @date 2007-08-29
	 * @param str
	 * @return
	 */
	public static String Q2B(String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); ++i) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (UnsupportedEncodingException e) {
				System.out.println(e);
			}

			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (UnsupportedEncodingException ex) {
					System.out.println(ex);
				}
			} else {
				outStr = outStr + Tstr;
			}
		}
		return outStr;
	}

	/**
	 * 
	 * 转换编码
	 * 
	 * @param s
	 *            源字符串
	 * @param fencode
	 *            源编码格式
	 * @param bencode
	 *            目标编码格式
	 * @return 目标编码
	 */
	public static String changCoding(String s, String fencode, String bencode) {
		try {
			String str = new String(s.getBytes(fencode), bencode);
			return str;
		} catch (UnsupportedEncodingException e) {
		}
		return s;
	}

	/**
	 * *************************************************************************
	 * 
	 * @param str
	 * @return
	 ************************************************************************* 
	 */
	public static String removeHTMLLableExe(String str) {
		str = stringReplace(str, ">\\s*<", "><");
		str = stringReplace(str, "&nbsp;", " ");
		str = stringReplace(str, "<br ?/?>", "\n");
		str = stringReplace(str, "<([^<>]+)>", "");
		str = stringReplace(str, "\\s\\s\\s*", " ");
		str = stringReplace(str, "^\\s*", "");
		str = stringReplace(str, "\\s*$", "");
		str = stringReplace(str, " +", " ");
		return str;
	}

	/**
	 * 除去html标签
	 * 
	 * @param str
	 *            源字符串
	 * @return 目标字符串
	 */
	public static String removeHTMLLable(String str) {
		str = stringReplace(str, "\\s", "");
		str = stringReplace(str, "<br ?/?>", "\n");
		str = stringReplace(str, "<([^<>]+)>", "");
		str = stringReplace(str, "&nbsp;", " ");
		str = stringReplace(str, "&(\\S)(\\S?)(\\S?)(\\S?);", "");
		return str;
	}

	/**
	 * 去掉HTML标签之外的字符串
	 * 
	 * @param str
	 *            源字符串
	 * @return 目标字符串
	 */
	public static String removeOutHTMLLable(String str) {
		str = stringReplace(str, ">([^<>]+)<", "><");
		str = stringReplace(str, "^([^<>]+)<", "<");
		str = stringReplace(str, ">([^<>]+)$", ">");
		return str;
	}

	/**
	 * 
	 * 字符串替换
	 * 
	 * @param str
	 *            源字符串
	 * @param sr
	 *            正则表达式样式
	 * @param sd
	 *            替换文本
	 * @return 结果串
	 */
	public static String stringReplace(String str, String sr, String sd) {
		String regEx = sr;
		Pattern p = Pattern.compile(regEx, 2);
		Matcher m = p.matcher(str);
		str = m.replaceAll(sd);
		return str;
	}

	/**
	 * 
	 * 将html的省略写法替换成非省略写法
	 * 
	 * @param str
	 *            html字符串
	 * @param pt
	 *            标签如table
	 * @return 结果串
	 */
	public static String fomateToFullForm(String str, String pt) {
		String regEx = "<" + pt + "\\s+([\\S&&[^<>]]*)/>";
		Pattern p = Pattern.compile(regEx, 2);
		Matcher m = p.matcher(str);
		String[] sa = null;
		String sf = "";
		String sf2 = "";
		String sf3 = "";
		while (m.find()) {
			sa = p.split(str);
			if (sa == null)
				break;

			sf = str.substring(sa[0].length(),
					str.indexOf("/>", sa[0].length()));

			sf2 = sf + "></" + pt + ">";
			sf3 = str.substring(sa[0].length() + sf.length() + 2);
			str = sa[0] + sf2 + sf3;
			sa = null;
		}
		return str;
	}

	/**
	 * 
	 * 得到字符串的子串位置序列
	 * 
	 * @param str
	 *            字符串
	 * @param sub
	 *            子串
	 * @param b
	 *            true子串前端,false子串后端
	 * @return 字符串的子串位置序列
	 */
	public static int[] getSubStringPos(String str, String sub, boolean b) {
		int j;
		String[] sp = null;
		int l = sub.length();
		sp = splitString(str, sub);
		if (sp == null)
			return null;

		int[] ip = new int[sp.length - 1];
		for (int i = 0; i < sp.length - 1; ++i) {
			ip[i] = (sp[i].length() + l);
			if (i != 0)
				ip[i] += ip[(i - 1)];
		}

		if (b)
			for (j = 0; j < ip.length; ++j)
				ip[j] -= l;

		return ip;
	}

	/**
	 * 
	 * 根据正则表达式分割字符串
	 * 
	 * @param str
	 *            源字符串
	 * @param ms
	 *            正则表达式
	 * @return 目标字符串组
	 */
	public static String[] splitString(String str, String ms) {
		String regEx = ms;
		Pattern p = Pattern.compile(regEx, 2);
		String[] sp = p.split(str);
		return sp;
	}

	/**
	 * *************************************************************************
	 * 根据正则表达式提取字符串,相同的字符串只返回一个
	 * 
	 * @param str
	 *            源字符串
	 * @param pattern
	 *            正则表达式
	 * @return 目标字符串数据组
	 ************************************************************************* 
	 */

	// ★传入一个字符串，把符合pattern格式的字符串放入字符串数组
	// java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包
	public static String[] getStringArrayByPattern(String str, String pattern) {
		int i;
		Pattern p = Pattern.compile(pattern, 2);
		Matcher matcher = p.matcher(str);

		Set result = new HashSet();

		while (matcher.find())
			for (i = 0; i < matcher.groupCount(); ++i) {
				result.add(matcher.group(i));
			}

		String[] resultStr = null;
		if (result.size() > 0) {
			resultStr = new String[result.size()];
			return ((String[]) result.toArray(resultStr));
		}
		return resultStr;
	}

	/**
	 * 得到第一个b,e之间的字符串,并返回e后的子串
	 * 
	 * @param s
	 *            源字符串
	 * @param b
	 *            标志开始
	 * @param e
	 *            标志结束
	 * @return b,e之间的字符串
	 */

	/*
	 * String aaa="abcdefghijklmn"; String[] bbb=StringProcessor.midString(aaa,
	 * "b","l"); System.out.println("bbb[0]:"+bbb[0]);//cdefghijk
	 * System.out.println("bbb[1]:"+bbb[1]);//lmn
	 * ★这个方法是得到第二个参数和第三个参数之间的字符串,赋给元素0;然后把元素0代表的字符串之后的,赋给元素1
	 */

	/*
	 * String aaa="abcdefgllhijklmn5465"; String[]
	 * bbb=StringProcessor.midString(aaa, "b","l"); //ab cdefg llhijklmn5465 //
	 * 元素0 元素1
	 */
	public static String[] midString(String s, String b, String e) {
		int i = s.indexOf(b) + b.length();
		int j = s.indexOf(e, i);
		String[] sa = new String[2];
		if ((i < b.length()) || (j < i + 1) || (i > j)) {
			sa[1] = s;
			sa[0] = null;
			return sa;
		}
		sa[0] = s.substring(i, j);
		sa[1] = s.substring(j);
		return sa;
	}

	/**
	 * 带有前一次替代序列的正则表达式替代
	 * 
	 * @param s
	 * @param pf
	 * @param pb
	 * @param start
	 * @return
	 */
	public static String stringReplace(String s, String pf, String pb, int start) {
		Pattern pattern_hand = Pattern.compile(pf);
		Matcher matcher_hand = pattern_hand.matcher(s);
		int gc = matcher_hand.groupCount();
		int pos = start;
		String sf1 = "";
		String sf2 = "";
		String sf3 = "";
		int if1 = 0;
		String strr = "";
		while (matcher_hand.find(pos)) {
			sf1 = matcher_hand.group();
			if1 = s.indexOf(sf1, pos);
			if (if1 >= pos) {
				strr += s.substring(pos, if1);
				pos = if1 + sf1.length();
				sf2 = pb;
				for (int i = 1; i <= gc; i++) {
					sf3 = "\\" + i;
					sf2 = replaceAll(sf2, sf3, matcher_hand.group(i));
				}
				strr += sf2;
			} else {
				return s;
			}
		}
		strr = s.substring(0, start) + strr;
		return strr;
	}

	/**
	 * 存文本替换
	 * 
	 * @param s
	 *            源字符串
	 * @param sf
	 *            子字符串
	 * @param sb
	 *            替换字符串
	 * @return 替换后的字符串
	 */
	public static String replaceAll(String s, String sf, String sb) {
		int i = 0;
		int j = 0;
		int l = sf.length();
		boolean b = true;
		boolean o = true;
		String str = "";
		do {
			j = i;
			i = s.indexOf(sf, j);
			if (i > j) {
				str = str + s.substring(j, i);
				str = str + sb;
				i += l;
				o = false;
			} else {
				str = str + s.substring(j);
				b = false;
			}
		} while (b);
		if (o)
			str = s;

		return str;
	}

	/**
	 * 判断是否与给定字符串样式匹配
	 * 
	 * @param str
	 *            字符串
	 * @param pattern
	 *            正则表达式样式
	 * @return 是否匹配是true,否false
	 */
	public static boolean isMatch(String str, String pattern) {
		Pattern pattern_hand = Pattern.compile(pattern);
		Matcher matcher_hand = pattern_hand.matcher(str);
		boolean b = matcher_hand.matches();
		return b;
	}

	/**
	 * 截取字符串
	 * 
	 * @param s
	 *            源字符串
	 * @param jmp
	 *            跳过jmp
	 * @param sb
	 *            取在sb
	 * @param se
	 *            于se
	 * @return 之间的字符串
	 */
	public static String subStringExe(String s, String jmp, String sb, String se) {
		if (isEmpty(s))
			return "";

		int i = 0;
		if (jmp != null) {
			i = s.indexOf(jmp);
			if ((i >= 0) && (i < s.length()))
				s = s.substring(i + 1);
		}

		i = s.indexOf(sb);
		if ((i >= 0) && (i < s.length()))
			s = s.substring(i);

		if (se.equals(""))
			return s;

		i = s.indexOf(se);
		if ((i >= 0) && (i < s.length()))
			s = s.substring(0, i + 3);

		return s;
	}

	/**
	 * *************************************************************************
	 * 用要通过URL传输的内容进行编码
	 * 
	 * @author 刘黎明
	 * @param 源字符串
	 * @return 经过编码的内容
	 ************************************************************************* 
	 */
	public static String URLEncode(String src) {
		String return_value = "";
		try {
			if (src != null)
				return_value = URLEncoder.encode(src, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return_value = src;
		}

		return return_value;
	}

	/**
	 * *************************************************************************
	 * 
	 * @author 李锋 2007.4.18
	 * @param 传入
	 *            &#31119;test&#29031;&#27004;&#65288;&#21271;&#22823;&#38376;&#
	 *            24635;&#24215;&#65289;&#31119;
	 * @return 经过解码的内容
	 ************************************************************************* 
	 */
	public static String getGBK(String str) {
		return transfer(str);
	}

	public static String transfer(String str) {
		Pattern p = Pattern.compile("&#\\d+;");
		Matcher m = p.matcher(str);
		while (m.find()) {
			String old = m.group();
			str = str.replaceAll(old, getChar(old));
		}
		return str;
	}

	public static String getChar(String str) {
		String dest = str.substring(2, str.length() - 1);
		char ch = (char) Integer.parseInt(dest);
		return "" + ch;
	}

	/**
	 * yahoo首页中切割字符串.
	 * 
	 * @date 2007-09-17
	 * @param str
	 * @return
	 */
	public static String subYhooString(String subject, int size) {
		subject = subject.substring(1, size);
		return subject;
	}

	public static String subYhooStringDot(String subject, int size) {
		subject = subject.substring(1, size) + "...";
		return subject;
	}

	/**
	 * 泛型方法(通用)，把list转换成以“,”相隔的字符串 调用时注意类型初始化（申明类型） 如：List<Integer> intList =
	 * new ArrayList<Integer>(); 调用方法：StringUtil.listTtoString(intList);
	 * 效率：list中4条信息，1000000次调用时间为850ms左右
	 * 
	 * @author liujie
	 * @serialData 2008-01-09
	 * @param <T>
	 *            泛型
	 * @param list
	 *            list列表
	 * @return 以“,”相隔的字符串
	 */
	public static <T> String listTtoString(List<T> list) {
		if ((list == null) || (list.size() < 1))
			return "";
		Iterator i = list.iterator();
		if (!(i.hasNext()))
			return "";
		StringBuilder sb = new StringBuilder();
		while (true) {
			Object e = i.next();
			sb.append(e);
			if (!(i.hasNext()))
				return sb.toString();
			sb.append(",");
		}
	}

	/**
	 * 把整形数组转换成以“,”相隔的字符串
	 * 
	 * @author liujie
	 * @serialData 2008-01-08
	 * @param a
	 *            数组a
	 * @return 以“,”相隔的字符串
	 */
	public static String intArraytoString(int[] a) {
		if (a == null)
			return "";
		int iMax = a.length - 1;
		if (iMax == -1)
			return "";
		StringBuilder b = new StringBuilder();
		int i = 0;
		while (true) {
			b.append(a[i]);
			if (i == iMax)
				return b.toString();
			b.append(",");

			++i;
		}
	}

	/**
	 * 判断文字内容重复
	 */
	public static boolean isContentRepeat(String content) {
		int similarNum = 0;
		int forNum = 0;
		int subNum = 0;
		int thousandNum = 0;
		String startStr = "";
		String nextStr = "";
		boolean result = false;
		float endNum = (float) 0.0;
		if (content != null && content.length() > 0) {
			if (content.length() % 1000 > 0)
				thousandNum = (int) Math.floor(content.length() / 1000) + 1;
			else
				thousandNum = (int) Math.floor(content.length() / 1000);
			if (thousandNum < 3)
				subNum = 100 * thousandNum;
			else if (thousandNum < 6)
				subNum = 200 * thousandNum;
			else if (thousandNum < 9)
				subNum = 300 * thousandNum;
			else
				subNum = 3000;
			for (int j = 1; j < subNum; j++) {
				if (content.length() % j > 0)
					forNum = (int) Math.floor(content.length() / j) + 1;
				else
					forNum = (int) Math.floor(content.length() / j);
				if (result || j >= content.length())
					break;
				else {
					for (int m = 0; m < forNum; m++) {
						if (m * j > content.length()
								|| (m + 1) * j > content.length()
								|| (m + 2) * j > content.length())
							break;
						startStr = content.substring(m * j, (m + 1) * j);
						nextStr = content.substring((m + 1) * j, (m + 2) * j);
						if (startStr.equals(nextStr)) {
							similarNum = similarNum + 1;
							endNum = (float) similarNum / forNum;
							if (endNum > 0.4) {
								result = true;
								break;
							}
						} else
							similarNum = 0;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Ascii转为Char
	 * @author liujie
	 * @param asc
	 * @return
	 */
	public static String AsciiToChar(int asc) {
		String TempStr = "A";
		char tempchar = (char) asc;
		TempStr = String.valueOf(tempchar);
		return TempStr;
	}

	/**
	 * 判断是否是空字符串 null和"" null返回result,否则返回字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String isEmpty(String s, String result) {
		if ((s != null) && (!(s.equals(""))))
			return s;

		return result;
	}

	/**
	 * 将带有htmlcode代码的字符转换成<>&'"
	 * @author liujie
	 * @param str
	 * @return
	 */
	public static String htmlcodeToSpecialchars(String str) {
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&#039;", "'");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		return str;
	}

	/**
	 * 移除html标签
	 * @author liujie
	 * @param htmlstr
	 * @return
	 */
	public static String removeHtmlTag(String htmlstr) {
		Pattern pat = Pattern.compile("\\s*<.*?>\\s*", 42);

		Matcher m = pat.matcher(htmlstr);
		String rs = m.replaceAll("");
		rs = rs.replaceAll("&nbsp", " ");
		rs = rs.replaceAll("&lt;", "<");
		rs = rs.replaceAll("&gt;", ">");
		return rs;
	}

	/**
	 * 取从指定搜索项开始的字符串，返回的值不包含搜索项
	 * @author liujie
	 * @param captions
	 *            例如:"www.koubei.com"
	 * @param regex
	 *            分隔符，例如"."
	 * @return 结果字符串，如：koubei.com，如未找到返回空串
	 */
	public static String getStrAfterRegex(String captions, String regex) {
		if ((!(isEmpty(captions))) && (!(isEmpty(regex)))) {
			int pos = captions.indexOf(regex);
			if ((pos != -1) && (pos < captions.length() - 1))
				return captions.substring(pos + 1);
		}

		return "";
	}

	/**
	 * 把字节码转换成16进制
	 */
	public static String byte2hex(byte[] bytes) {
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			retString.append(Integer.toHexString(256 + (bytes[i] & 0xFF))
					.substring(1).toUpperCase());
		}

		return retString.toString();
	}

	/**
	 * 把16进制转换成字节码
	 * @author liujie
	 * @param hex
	 * @return
	 */
	public static byte[] hex2byte(String hex) {
		byte[] bts = new byte[hex.length() / 2];
		for (int i = 0; i < bts.length; ++i) {
			bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2),
					16);
		}

		return bts;
	}

	/**
	 * 转换数字为固定长度的字符串
	 * @author liujie
	 * @param length
	 *            希望返回的字符串长度
	 * @param data
	 *            传入的数值
	 * @return
	 */
	public static String getStringByInt(int length, String data) {
		StringBuffer s_data = new StringBuffer();
		int datalength = data.length();
		if ((length > 0) && (length >= datalength)) {
			for (int i = 0; i < length - datalength; ++i)
			s_data.append("0").append(data);
		}

		return s_data.toString();
	}

	/**
	 * 判断是否位数字,并可为空
	 * @author liujie
	 * @param src
	 * @return
	 */
	public static boolean isNumericAndCanNull(String src) {
		Pattern numericPattern = Pattern.compile("^[0-9]+$");
		if ((src == null) || (src.equals("")))
			return true;
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = numericPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	public static boolean isFloatAndCanNull(String src) {
		Pattern numericPattern = Pattern
				.compile("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");

		if ((src == null) || (src.equals("")))
			return true;
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = numericPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	public static boolean isNotEmpty(String str) {
		return ((str != null) && (!(str.equals(""))));
	}

	public static boolean isDate(String date) {
		String regEx = "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(date);
		boolean result = m.find();
		return result;
	}

	public static boolean isFormatDate(String date, String regEx) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(date);
		boolean result = m.find();
		return result;
	}

	/**
	 * 根据指定整型list 组装成为一个字符串
	 * @author liujie
	 * @param src
	 * @return
	 */
	public static String listToString(List<Integer> list) {
		String str = "";
		if ((list != null) && (list.size() > 0)) {
			for (Iterator i$ = list.iterator(); i$.hasNext();) {
				int id = ((Integer) i$.next()).intValue();
				str = str + id + ",";
			}
			if ((!("".equals(str))) && (str.length() > 0))
				str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 页面的非法字符检查
	 * 
	 * @author liujie
	 * @date 2007-11-29
	 * @param str
	 * @return
	 */
	public static String replaceStr(String str) {
		if ((str != null) && (str.length() > 0)) {
			str = str.replaceAll("~", ",");
			str = str.replaceAll(" ", ",");
			str = str.replaceAll("　", ",");
			str = str.replaceAll(" ", ",");
			str = str.replaceAll("`", ",");
			str = str.replaceAll("!", ",");
			str = str.replaceAll("@", ",");
			str = str.replaceAll("#", ",");
			str = str.replaceAll("\\$", ",");
			str = str.replaceAll("%", ",");
			str = str.replaceAll("\\^", ",");
			str = str.replaceAll("&", ",");
			str = str.replaceAll("\\*", ",");
			str = str.replaceAll("\\(", ",");
			str = str.replaceAll("\\)", ",");
			str = str.replaceAll("-", ",");
			str = str.replaceAll("_", ",");
			str = str.replaceAll("=", ",");
			str = str.replaceAll("\\+", ",");
			str = str.replaceAll("\\{", ",");
			str = str.replaceAll("\\[", ",");
			str = str.replaceAll("\\}", ",");
			str = str.replaceAll("\\]", ",");
			str = str.replaceAll("\\|", ",");
			str = str.replaceAll("\\\\", ",");
			str = str.replaceAll(";", ",");
			str = str.replaceAll(":", ",");
			str = str.replaceAll("'", ",");
			str = str.replaceAll("\\\"", ",");
			str = str.replaceAll("<", ",");
			str = str.replaceAll(">", ",");
			str = str.replaceAll("\\.", ",");
			str = str.replaceAll("\\?", ",");
			str = str.replaceAll("/", ",");
			str = str.replaceAll("～", ",");
			str = str.replaceAll("`", ",");
			str = str.replaceAll("！", ",");
			str = str.replaceAll("＠", ",");
			str = str.replaceAll("＃", ",");
			str = str.replaceAll("＄", ",");
			str = str.replaceAll("％", ",");
			str = str.replaceAll("︿", ",");
			str = str.replaceAll("＆", ",");
			str = str.replaceAll("×", ",");
			str = str.replaceAll("（", ",");
			str = str.replaceAll("）", ",");
			str = str.replaceAll("－", ",");
			str = str.replaceAll("＿", ",");
			str = str.replaceAll("＋", ",");
			str = str.replaceAll("＝", ",");
			str = str.replaceAll("｛", ",");
			str = str.replaceAll("［", ",");
			str = str.replaceAll("｝", ",");
			str = str.replaceAll("］", ",");
			str = str.replaceAll("｜", ",");
			str = str.replaceAll("＼", ",");
			str = str.replaceAll("：", ",");
			str = str.replaceAll("；", ",");
			str = str.replaceAll("＂", ",");
			str = str.replaceAll("＇", ",");
			str = str.replaceAll("＜", ",");
			str = str.replaceAll("，", ",");
			str = str.replaceAll("＞", ",");
			str = str.replaceAll("．", ",");
			str = str.replaceAll("？", ",");
			str = str.replaceAll("／", ",");
			str = str.replaceAll("·", ",");
			str = str.replaceAll("￥", ",");
			str = str.replaceAll("……", ",");
			str = str.replaceAll("（", ",");
			str = str.replaceAll("）", ",");
			str = str.replaceAll("——", ",");
			str = str.replaceAll("-", ",");
			str = str.replaceAll("【", ",");
			str = str.replaceAll("】", ",");
			str = str.replaceAll("、", ",");
			str = str.replaceAll("”", ",");
			str = str.replaceAll("’", ",");
			str = str.replaceAll("《", ",");
			str = str.replaceAll("》", ",");
			str = str.replaceAll("“", ",");
			str = str.replaceAll("。", ",");
		}
		return str;
	}

	/**
	 * 全角字符变半角字符
	 * 
	 * @author liujie
	 * @date 2008-04-03
	 * @param str
	 * @return
	 */
	public static String full2Half(String str) {
		if ((str == null) || ("".equals(str)))
			return "";
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);

			if ((c >= 65281) && (c < 65373))
				sb.append((char) (c - 65248));
			else
				sb.append(str.charAt(i));
		}

		return sb.toString();
	}

	/**
	 * 全角括号转为半角
	 * 
	 * @author liujie
	 * @date 2007-11-29
	 * @param str
	 * @return
	 */
	public static String replaceBracketStr(String str) {
		if ((str != null) && (str.length() > 0)) {
			str = str.replaceAll("（", "(");
			str = str.replaceAll("）", ")");
		}
		return str;
	}

	/**
	 * 分割字符，从开始到第一个split字符串为止
	 * 
	 * @param src
	 *            源字符串
	 * @param split
	 *            截止字符串
	 * @return
	 */
	public static String subStr(String src, String split) {
		if (!(isEmpty(src))) {
			int index = src.indexOf(split);
			if (index >= 0)
				return src.substring(0, index);
		}

		return src;
	}

	/**
	 * 取url里的keyword（可选择参数）参数，用于整站搜索整合
	 * 
	 * @author liujie
	 * @param params
	 * @param qString
	 * @return
	 */
	public static String getKeyWord(String params, String qString) {
		String keyWord = "";
		if (qString != null) {
			String param = params + "=";
			int i = qString.indexOf(param);
			if (i != -1) {
				int j = qString.indexOf("&", i + param.length());
				if (j > 0)
					keyWord = qString.substring(i + param.length(), j);
			}
		}

		return keyWord;
	}

	/**
	 * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
	 * 
	 * @param query
	 *            源参数字符串
	 * @param split1
	 *            键值对之间的分隔符（例：&）
	 * @param split2
	 *            key与value之间的分隔符（例：=）
	 * @param dupLink
	 *            重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null
	 *            null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
	 * @return map
	 * @author liujie
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseQuery(String query, char split1,
			char split2, String dupLink) {
		if ((!(isEmpty(query))) && (query.indexOf(split2) > 0)) {
			Map result = new HashMap();

			String name = null;
			String value = null;
			String tempValue = "";
			int len = query.length();
			for (int i = 0; i < len; ++i) {
				char c = query.charAt(i);
				if (c == split2) {
					value = "";
				} else if (c == split1) {
					if ((!(isEmpty(name))) && (value != null)) {
						if (dupLink != null) {
							tempValue = (String) result.get(name);
							if (tempValue != null)
								value = value + dupLink + tempValue;
						}

						result.put(name, value);
					}
					name = null;
					value = null;
				} else if (value != null) {
					value = value + c;
				} else {
					name = "" + c;
				}
			}

			if ((!(isEmpty(name))) && (value != null)) {
				if (dupLink != null) {
					tempValue = (String) result.get(name);
					if (tempValue != null)
						value = value + dupLink + tempValue;
				}

				result.put(name, value);
			}

			return result;
		}
		return null;
	}

	/**
	 * 将list 用传入的分隔符组装为String
	 * @author liujie
	 * @param list
	 * @param slipStr
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String listToStringSlipStr(List list, String slipStr) {
		int i;
		StringBuffer returnStr = new StringBuffer();
		if ((list != null) && (list.size() > 0))
			for (i = 0; i < list.size(); ++i)
				returnStr.append(list.get(i)).append(slipStr);

		if (returnStr.toString().length() > 0) {
			return returnStr.toString().substring(0,
					returnStr.toString().lastIndexOf(slipStr));
		}

		return "";
	}

	/**
	 * 获取从start开始用*替换len个长度后的字符串
	 * @author liujie
	 * @param str
	 *            要替换的字符串
	 * @param start
	 *            开始位置
	 * @param len
	 *            长度
	 * @return 替换后的字符串
	 */
	public static String getMaskStr(String str, int start, int len) {
		if (isEmpty(str))
			return str;

		if (str.length() < start) {
			return str;
		}

		String ret = str.substring(0, start);

		int strLen = str.length();
		if (strLen < start + len) {
			len = strLen - start;
		}

		for (int i = 0; i < len; ++i) {
			ret = ret + "*";
		}

		if (strLen > start + len) {
			ret = ret + str.substring(start + len);
		}

		return ret;
	}

	/**
	 * 根据传入的分割符号,把传入的字符串分割为List字符串
	 * @author liujie
	 * @param slipStr
	 *            分隔的字符串
	 * @param src
	 *            字符串
	 * @return 列表
	 */
	public static List<String> stringToStringListBySlipStr(String slipStr,
			String src) {
		if (src == null)
			return null;
		List list = new ArrayList();
		String[] result = src.split(slipStr);
		for (int i = 0; i < result.length; ++i)
			list.add(result[i]);

		return list;
	}

	/**
	 * 截取字符串
	 * @author liujie
	 * @param str
	 *            原始字符串
	 * @param len
	 *            要截取的长度
	 * @param tail
	 *            结束加上的后缀
	 * @return 截取后的字符串
	 */
	public static String formatStr(String str) {
		if ((null == str) || (str.equals("null"))) {
			return "";
		}

		return str.trim();
	}

	public static String getSubStrLastSymbol(String src, String symbol) {
		return src.substring(0, src.lastIndexOf(symbol));
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 *            原始字符串
	 * @param len
	 *            要截取的长度
	 * @param tail
	 *            结束加上的后缀
	 * @author liujie
	 * @return 截取后的字符串
	 */
	public static String getHtmlSubString(String str, int len, String tail) {
		if (str == null || str.length() <= len) {
			return str;
		}
		int length = str.length();
		char c = ' ';
		String tag = null;
		String name = null;
		int size = 0;
		StringBuffer result =new StringBuffer();
		boolean isTag = false;
		List<String> tags = new ArrayList<String>();
		int i = 0;
		for (int end = 0, spanEnd = 0; i < length && len > 0; i++) {
			c = str.charAt(i);
			if (c == '<') {
				end = str.indexOf('>', i);
			}

			if (end > 0) {
				// 截取标签
				tag = str.substring(i, end + 1);
				int n = tag.length();
				if (tag.endsWith("/>")) {
					isTag = true;
				} else if (tag.startsWith("</")) { // 结束符
					name = tag.substring(2, end - i);
					size = tags.size() - 1;
					// 堆栈取出html开始标签
					if (size >= 0 && name.equals(tags.get(size))) {
						isTag = true;
						tags.remove(size);
					}
				} else { // 开始符
					spanEnd = tag.indexOf(' ', 0);
					spanEnd = spanEnd > 0 ? spanEnd : n;
					name = tag.substring(1, spanEnd);
					if (name.trim().length() > 0) {
						// 如果有结束符则为html标签
						spanEnd = str.indexOf("</" + name + ">", end);
						if (spanEnd > 0) {
							isTag = true;
							tags.add(name);
						}
					}
				}
				// 非html标签字符
				if (!isTag) {
					if (n >= len) {
						result.append(tag.substring(0, len));
						break;
					} else {
						len -= n;
					}
				}

				result.append(tag);
				isTag = false;
				i = end;
				end = 0;
			} else { // 非html标签字符
				len--;
				result.append(c);
			}
		}
		// 添加未结束的html标签
		for (String endTag : tags) {
			result.append("</").append(endTag).append(">");
		}
		if (i < length) {
			result.append(tail);
		}
		return result.toString();
	}

	/*
	 * 格式化数字 1 ---> 001 10---->010
	 */
	public static String zeroFormat(Integer source) {
		String stringSource = String.valueOf(source);
		if (stringSource.length() >= 3)
			return source + "";
		return String.format("%03d", source);
	}

	public static String zeroFormat(Integer source, Integer zeroNum) {
		if (zeroNum == null)
			zeroNum = 0;
		String stringSource = String.valueOf(source);
		if (stringSource.length() >= zeroNum)
			return source + "";
		return String.format("%0" + zeroNum + "d", source);
	}

	public static String toUpperFirstLetter(String source) {
		if (source == null || "".equals(source))
			return "";
		return source.substring(0, 1).toUpperCase() + source.substring(1);

	}

	/**
	 * 验证身份证
	 * @author liujie
	 * @param source
	 * @return
	 */
	public static boolean isIDCard(String source) {
		boolean return_value = false;
		if ((source != null) && (source.length() > 0)) {
			Matcher m = idCarePattern.matcher(source);
			if (m.find())
				return_value = true;
		}
		return return_value;
	}

	public static String toWindowSeperate(String path) {
		if (path == null)
			return null;
		if (path.startsWith("/"))
			path = path.substring(1);
		return replaceAll(path, "/", "\\");
	}

	public static String toLinuxSeperate(String path) {
		if (path == null)
			return null;
		return replaceAll(path, "\\", "/");
	}
	
	/**
	 * 非中国移动号检验
	 * @param cellNum
	 * @author liujie
	 * @return
	 */
	public static boolean chinaMobile(String cellNum){
		boolean return_value = false;
		if (!StringUtils.isEmpty(cellNum)) {
			Matcher m = cellNumPattern.matcher(cellNum);
			if (m.find())
				return_value = true;
		}
		return return_value;
	}
	/**
	 * <是否整数>
	 * <功能详细描述>
	 * @param cellNum
	 * @return
	 * @author liujie
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isInt(String number){
		boolean return_value = false;
		if (!StringUtils.isEmpty(number)) {
			Matcher m = intPattern.matcher(number);
			if (m.find())
				return_value = true;
		}
		return return_value;
	}
	
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
	
	public static String getDate (String oTime) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
        String sfstr = "";
        try {
         sfstr = sf2.format(sf1.parse(oTime));
         System.out.println(sfstr);
	     } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
        return sfstr;
	}
	
	public static String getDate(String oTime, String sourceFormat, String destFormat) {
		DateFormat sformcat = new SimpleDateFormat(sourceFormat);
		DateFormat dformat = new SimpleDateFormat(destFormat);
		
        String sDate = null;
        try {
			if((oTime != null) && (sourceFormat != null) && (destFormat != null)){
				sDate = dformat.format(sformcat.parse(oTime));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		}
        
        return sDate;
	}

	/**
	 * add by xuexiaohua at 2015-06-14 对办理，退订中的BunessType为1、2时作拼接兼容处理
	 * @param length 多大长度给予补充添加字段
	 * @param oldStr 原字符串
	 * @param add 补充添加的字段值
	 * @param flag 字段前加还是后加标示  L代表在oldStr前加  R代表在oldStr后加
	 * @return
	 */
    public static String getNewString(int length,String oldStr,String add,String flag){
    	
    	String newStr = "";
    
    	if(!StringUtils.isEmpty(oldStr) && oldStr.length() == length){
    		if("L".equals(flag)){
    			newStr = add + oldStr;
    		}else if("R".equals(flag)){
    			newStr = oldStr + add;
    		}else{
    			newStr = oldStr;
    		}
    	}else{
    		newStr = oldStr;
    	}
    	return newStr;
    }
    
}

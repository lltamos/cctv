/*
 * 文 件 名:  PhoneAnalyzer.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月3日,  All rights reserved  
 */
package cn.leadeon.utils.convert;

import java.util.Arrays;
import java.util.List;
/**
 * 电话号码分析工具类
 * 
 * @author liudongdong
 * @version [1.0, 2014年4月3日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class PhoneAnalyzer {
	
	private String parseTmp;
	private int parseTmpBegin;
	private int parseTmpCount;
	private String parseList;

	public PhoneAnalyzer() {
		this.parseTmp = "";

		this.parseTmpBegin = -1;

		this.parseTmpCount = 0;

		this.parseList = "";
	}

	private void resetTmp() {
		this.parseTmp = "";
		this.parseTmpBegin = -1;
		this.parseTmpCount = 0;
	}

	private String replaceResult(String sou, String find, String replace) {
		int i;
		String strReplace = replace;

		if (replace.length() == 1) {
			strReplace = "";
			for (i = 0; i < find.length(); ++i)
				strReplace = strReplace + replace;
		}

		int pos = sou.indexOf(find);
		while (pos > -1) {
			String s1 = sou.substring(0, pos);
			String s2 = sou.substring(pos + find.length(), sou.length());
			sou = s1 + strReplace + s2;
			pos = sou.indexOf(find);
		}

		return sou;
	}

	private String parsePhone(String content, int minWidth, String replace) {
		if ((content == null) || (content.trim().equals("")) || (minWidth < 0))
			return "";

		char[] c = content.toCharArray();
		String result = content;
		resetTmp();
		this.parseList = "";

		for (int i = 0; i < c.length; ++i) {
			int style = getCharStyle(c[i]);
			switch (style) {
			case -1:
				if (this.parseTmpBegin > -1) {
					this.parseTmpCount += 1;
					if (this.parseTmpCount > 3)
						resetTmp();
				}
				break;
			case -2:
				resetTmp();
				break;
			case -3:
				break;
			case -4:
				break;
			default:
				this.parseTmp += style;

				if (this.parseTmpBegin == -1) {
					this.parseTmpBegin = i;
					this.parseTmpCount = 0;
				}

				if (this.parseTmp.length() >= minWidth) {
					this.parseList += ((this.parseList.equals("")) ? this.parseTmp
							: new StringBuilder().append(",").append(
									this.parseTmp).toString());

					if ((replace != null) && (!(replace.equals("")))
							&& (this.parseTmpBegin > -1)) {
						String find = "";
						for (int j = this.parseTmpBegin; j <= i; ++j)
							find = find + c[j];
						result = replaceResult(result, find, replace);
					}

					resetTmp();
				}
			}
		}

		return result;
	}

	private int getCharStyle(char chr) {
		int result = -1;

		switch (chr) {
		case '\n':
			result = -2;
			break;
		case '\r':
			result = -2;
			break;
		case ' ':
			result = -3;
			break;
		case '　':
			result = -3;
			break;
		case '(':
			result = -4;
			break;
		case ')':
			result = -4;
			break;
		case '-':
			result = -4;
			break;
		case '_':
			result = -4;
			break;
		case '/':
			result = -4;
			break;
		case '\\':
			result = -4;
			break;
		case '.':
			result = -4;
			break;
		case '~':
			result = -4;
			break;
		case '、':
			result = -4;
			break;
		case '。':
			result = -4;
			break;
		case '）':
			result = -4;
			break;
		case '（':
			result = -4;
			break;
		case '\'':
			result = -4;
			break;
		case '`':
			result = -4;
			break;
		case '^':
			result = -4;
			break;
		case '0':
			result = 0;
			break;
		case '1':
			result = 1;
			break;
		case '2':
			result = 2;
			break;
		case '3':
			result = 3;
			break;
		case '4':
			result = 4;
			break;
		case '5':
			result = 5;
			break;
		case '6':
			result = 6;
			break;
		case '7':
			result = 7;
			break;
		case '8':
			result = 8;
			break;
		case '9':
			result = 9;
			break;
		case '一':
			result = 1;
			break;
		case '二':
			result = 2;
			break;
		case '三':
			result = 3;
			break;
		case '四':
			result = 4;
			break;
		case '五':
			result = 5;
			break;
		case '六':
			result = 6;
			break;
		case '七':
			result = 7;
			break;
		case '八':
			result = 8;
			break;
		case '九':
			result = 9;
			break;
		case '零':
			result = 0;
			break;
		case '壹':
			result = 1;
			break;
		case '贰':
			result = 2;
			break;
		case '叁':
			result = 3;
			break;
		case '肆':
			result = 4;
			break;
		case '伍':
			result = 5;
			break;
		case '陆':
			result = 6;
			break;
		case '柒':
			result = 7;
			break;
		case '捌':
			result = 8;
			break;
		case '玖':
			result = 9;
			break;
		case '０':
			result = 0;
			break;
		case '１':
			result = 1;
			break;
		case '２':
			result = 2;
			break;
		case '３':
			result = 3;
			break;
		case '４':
			result = 4;
			break;
		case '５':
			result = 5;
			break;
		case '６':
			result = 6;
			break;
		case '７':
			result = 7;
			break;
		case '８':
			result = 8;
			break;
		case '９':
			result = 9;
			break;
		case '⑴':
			result = 1;
			break;
		case '⑵':
			result = 2;
			break;
		case '⑶':
			result = 3;
			break;
		case '⑷':
			result = 4;
			break;
		case '⑸':
			result = 5;
			break;
		case '⑹':
			result = 6;
			break;
		case '⑺':
			result = 7;
			break;
		case '⑻':
			result = 8;
			break;
		case '⑼':
			result = 9;
			break;
		case '①':
			result = 1;
			break;
		case '②':
			result = 2;
			break;
		case '③':
			result = 3;
			break;
		case '④':
			result = 4;
			break;
		case '⑤':
			result = 5;
			break;
		case '⑥':
			result = 6;
			break;
		case '⑦':
			result = 7;
			break;
		case '⑧':
			result = 8;
			break;
		case '⑨':
			result = 9;
			break;
		case '㈠':
			result = 1;
			break;
		case '㈡':
			result = 2;
			break;
		case '㈢':
			result = 3;
			break;
		case '㈣':
			result = 4;
			break;
		case '㈤':
			result = 5;
			break;
		case '㈥':
			result = 6;
			break;
		case '㈦':
			result = 7;
			break;
		case '㈧':
			result = 8;
			break;
		case '㈨':
			result = 9;
			break;
		case '⒈':
			result = 1;
			break;
		case '⒉':
			result = 2;
			break;
		case '⒊':
			result = 3;
			break;
		case '⒋':
			result = 4;
			break;
		case '⒌':
			result = 5;
			break;
		case '⒍':
			result = 6;
			break;
		case '⒎':
			result = 7;
			break;
		case '⒏':
			result = 8;
			break;
		case '⒐':
			result = 9;
			break;
		case '玲':
			result = 0;
			break;
		case '菱':
			result = 0;
			break;
		case '龄':
			result = 0;
			break;
		case '铃':
			result = 0;
			break;
		case '伶':
			result = 0;
			break;
		case '羚':
			result = 0;
			break;
		case '凌':
			result = 0;
			break;
		case '灵':
			result = 0;
			break;
		case '陵':
			result = 0;
			break;
		case '岭':
			result = 0;
			break;
		case '领':
			result = 0;
			break;
		case '另':
			result = 0;
			break;
		case '令':
			result = 0;
			break;
		case '医':
			result = 1;
			break;
		case '揖':
			result = 1;
			break;
		case '铱':
			result = 1;
			break;
		case '依':
			result = 1;
			break;
		case '伊':
			result = 1;
			break;
		case '衣':
			result = 1;
			break;
		case '颐':
			result = 1;
			break;
		case '夷':
			result = 1;
			break;
		case '遗':
			result = 1;
			break;
		case '移':
			result = 1;
			break;
		case '仪':
			result = 1;
			break;
		case '胰':
			result = 1;
			break;
		case '疑':
			result = 1;
			break;
		case '沂':
			result = 1;
			break;
		case '宜':
			result = 1;
			break;
		case '姨':
			result = 1;
			break;
		case '彝':
			result = 1;
			break;
		case '椅':
			result = 1;
			break;
		case '蚁':
			result = 1;
			break;
		case '倚':
			result = 1;
			break;
		case '已':
			result = 1;
			break;
		case '乙':
			result = 1;
			break;
		case '矣':
			result = 1;
			break;
		case '以':
			result = 1;
			break;
		case '艺':
			result = 1;
			break;
		case '抑':
			result = 1;
			break;
		case '易':
			result = 1;
			break;
		case '邑':
			result = 1;
			break;
		case '屹':
			result = 1;
			break;
		case '亿':
			result = 1;
			break;
		case '役':
			result = 1;
			break;
		case '臆':
			result = 1;
			break;
		case '逸':
			result = 1;
			break;
		case '肄':
			result = 1;
			break;
		case '疫':
			result = 1;
			break;
		case '亦':
			result = 1;
			break;
		case '裔':
			result = 1;
			break;
		case '意':
			result = 1;
			break;
		case '毅':
			result = 1;
			break;
		case '忆':
			result = 1;
			break;
		case '义':
			result = 1;
			break;
		case '益':
			result = 1;
			break;
		case '溢':
			result = 1;
			break;
		case '诣':
			result = 1;
			break;
		case '议':
			result = 1;
			break;
		case '谊':
			result = 1;
			break;
		case '译':
			result = 1;
			break;
		case '异':
			result = 1;
			break;
		case '翼':
			result = 1;
			break;
		case '翌':
			result = 1;
			break;
		case '绎':
			result = 1;
			break;
		case '尔':
			result = 2;
			break;
		case '饵':
			result = 2;
			break;
		case '洱':
			result = 2;
			break;
		case '伞':
			result = 3;
			break;
		case '散':
			result = 3;
			break;
		case '斯':
			result = 4;
			break;
		case '撕':
			result = 4;
			break;
		case '嘶':
			result = 4;
			break;
		case '思':
			result = 4;
			break;
		case '私':
			result = 4;
			break;
		case '司':
			result = 4;
			break;
		case '丝':
			result = 4;
			break;
		case '死':
			result = 4;
			break;
		case '寺':
			result = 4;
			break;
		case '嗣':
			result = 4;
			break;
		case '伺':
			result = 4;
			break;
		case '似':
			result = 4;
			break;
		case '饲':
			result = 4;
			break;
		case '巳':
			result = 4;
			break;
		case '巫':
			result = 5;
			break;
		case '呜':
			result = 5;
			break;
		case '钨':
			result = 5;
			break;
		case '乌':
			result = 5;
			break;
		case '污':
			result = 5;
			break;
		case '诬':
			result = 5;
			break;
		case '屋':
			result = 5;
			break;
		case '无':
			result = 5;
			break;
		case '芜':
			result = 5;
			break;
		case '梧':
			result = 5;
			break;
		case '吾':
			result = 5;
			break;
		case '吴':
			result = 5;
			break;
		case '毋':
			result = 5;
			break;
		case '武':
			result = 5;
			break;
		case '捂':
			result = 5;
			break;
		case '午':
			result = 5;
			break;
		case '舞':
			result = 5;
			break;
		case '侮':
			result = 5;
			break;
		case '坞':
			result = 5;
			break;
		case '戊':
			result = 5;
			break;
		case '雾':
			result = 5;
			break;
		case '晤':
			result = 5;
			break;
		case '物':
			result = 5;
			break;
		case '勿':
			result = 5;
			break;
		case '务':
			result = 5;
			break;
		case '悟':
			result = 5;
			break;
		case '误':
			result = 5;
			break;
		case '琉':
			result = 6;
			break;
		case '榴':
			result = 6;
			break;
		case '硫':
			result = 6;
			break;
		case '馏':
			result = 6;
			break;
		case '留':
			result = 6;
			break;
		case '刘':
			result = 6;
			break;
		case '瘤':
			result = 6;
			break;
		case '流':
			result = 6;
			break;
		case '柳':
			result = 6;
			break;
		case '期':
			result = 7;
			break;
		case '欺':
			result = 7;
			break;
		case '栖':
			result = 7;
			break;
		case '戚':
			result = 7;
			break;
		case '妻':
			result = 7;
			break;
		case '凄':
			result = 7;
			break;
		case '漆':
			result = 7;
			break;
		case '沏':
			result = 7;
			break;
		case '其':
			result = 7;
			break;
		case '棋':
			result = 7;
			break;
		case '奇':
			result = 7;
			break;
		case '歧':
			result = 7;
			break;
		case '畦':
			result = 7;
			break;
		case '崎':
			result = 7;
			break;
		case '脐':
			result = 7;
			break;
		case '齐':
			result = 7;
			break;
		case '旗':
			result = 7;
			break;
		case '祈':
			result = 7;
			break;
		case '祁':
			result = 7;
			break;
		case '骑':
			result = 7;
			break;
		case '起':
			result = 7;
			break;
		case '岂':
			result = 7;
			break;
		case '乞':
			result = 7;
			break;
		case '企':
			result = 7;
			break;
		case '启':
			result = 7;
			break;
		case '契':
			result = 7;
			break;
		case '砌':
			result = 7;
			break;
		case '器':
			result = 7;
			break;
		case '气':
			result = 7;
			break;
		case '迄':
			result = 7;
			break;
		case '弃':
			result = 7;
			break;
		case '汽':
			result = 7;
			break;
		case '泣':
			result = 7;
			break;
		case '讫':
			result = 7;
			break;
		case '芭':
			result = 8;
			break;
		case '扒':
			result = 8;
			break;
		case '叭':
			result = 8;
			break;
		case '吧':
			result = 8;
			break;
		case '笆':
			result = 8;
			break;
		case '疤':
			result = 8;
			break;
		case '巴':
			result = 8;
			break;
		case '拔':
			result = 8;
			break;
		case '跋':
			result = 8;
			break;
		case '靶':
			result = 8;
			break;
		case '把':
			result = 8;
			break;
		case '耙':
			result = 8;
			break;
		case '坝':
			result = 8;
			break;
		case '霸':
			result = 8;
			break;
		case '罢':
			result = 8;
			break;
		case '爸':
			result = 8;
			break;
		case '揪':
			result = 9;
			break;
		case '究':
			result = 9;
			break;
		case '纠':
			result = 9;
			break;
		case '韭':
			result = 9;
			break;
		case '久':
			result = 9;
			break;
		case '灸':
			result = 9;
			break;
		case '酒':
			result = 9;
			break;
		case '厩':
			result = 9;
			break;
		case '救':
			result = 9;
			break;
		case '旧':
			result = 9;
			break;
		case '臼':
			result = 9;
			break;
		case '舅':
			result = 9;
			break;
		case '咎':
			result = 9;
			break;
		case '就':
			result = 9;
			break;
		case '疚':
			result = 9;
			break;
		default:
			result = -1;
		}

		return result;
	}

	public static String replacePhone(String content, String strReplace) {
		if ((content == null) || ("".equals(content.trim())))
			return content;

		PhoneAnalyzer pa = new PhoneAnalyzer();
		return pa.parsePhone(content, 6, strReplace);
	}

	public static String[] parsePhone(String content) {
		if ((content == null) || ("".equals(content.trim())))
			return null;

		PhoneAnalyzer pa = new PhoneAnalyzer();
		pa.parsePhone(content, 6, null);
		return pa.parseList.split(",");
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> parsePhoneToList(String content) {
		return (List<T>) Arrays.asList(parsePhone(content));
	}

	public static void main(String[] args) {
		String[] arr = parsePhone("18220853619");
		for(String a : arr){
			System.out.print(a);
		}
	}
}
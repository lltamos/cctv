/*
 * 文 件 名:  IPUtil.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.other;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;



/**
 * 解析ip地址来源
 * 邮 箱:hizhangyaoming@yahoo.com
 * @author  zhangyaomin
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class IPUtil {
	public static final Pattern ipStrPattern = Pattern
			.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("x-forwarded-for");
		}
		if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		if (null != ip) {
			ip = ip.trim().replace(";", ",").replace(" ", ",");
			String[] ipList = ip.split(",");
			if (ipList != null && ipList.length > 0) {
				ip = ipList[0];
			}
		}
		return ip;
	}

	public static long ipToLong(String strIP) {
		long[] ip = new long[4];
		int position1 = strIP.indexOf(".");
		int position2 = strIP.indexOf(".", position1 + 1);
		int position3 = strIP.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIP.substring(0, position1));
		ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIP.substring(position3 + 1));

		return ((ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3]);
	}

	public static boolean isLocal(String strIp) {
		if ("127.0.0.1".equals(strIp))
			return true;
		long l = ipToLong(strIp);
		if (l >= 3232235520L)
			return (l <= 3232301055L);
		return ((l >= 167772160L) && (l <= 184549375L));
	}

	public static boolean isIPAddress(String src) {
		boolean return_value = false;
		if ((src != null) && (src.length() > 0)) {
			Matcher m = ipStrPattern.matcher(src);
			if (m.find())
				return_value = true;
		}

		return return_value;
	}

	/**
	 * 
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getAddresses(String content, String encodingString)
			throws UnsupportedEncodingException {
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		String returnStr = getResult(urlStr, content, encodingString);
		if (returnStr != null) {
			String[] temp = returnStr.split(",");
			if (temp.length < 3) {
				return "0";// 无效IP，局域网测试
			}
			String country = decodeUnicode((temp[1].split(":"))[2].replaceAll(
					"\"", ""));
			String area = decodeUnicode((temp[3].split(":"))[1].replaceAll(
					"\"", ""));
			String region = decodeUnicode((temp[5].split(":"))[1].replaceAll(
					"\"", ""));
			String city = decodeUnicode((temp[7].split(":"))[1].replaceAll(
					"\"", ""));
			String county = decodeUnicode((temp[9].split(":"))[1].replaceAll(
					"\"", ""));
			String isp = decodeUnicode((temp[11].split(":"))[1].replaceAll(
					"\"", ""));
			return country + area + region + city + county + isp;
		}
		return null;
	}

	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 */
	public static String getResult(String urlStr, String content,
			String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	/**
	 * unicode 转换成 中文
	 * 
	 * @author fanhui 2007-3-15
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}

	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}
	
	public static String getServerIp(){
		String ipAddr = "";
		
		Properties p = System.getProperties();
		String os = p.getProperty("os.name");
		//如果是windows操作系统
		if(null != os && os.startsWith("Windows")){
			try {
				InetAddress addr = InetAddress.getLocalHost();
				ipAddr = addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}else{
			Enumeration<NetworkInterface> allNetInterfaces;  //定义网络接口枚举类  
	        try {  
	            allNetInterfaces = NetworkInterface.getNetworkInterfaces();  //获得网络接口  
	  
	            InetAddress ip = null; //声明一个InetAddress类型ip地址  
	            while (allNetInterfaces.hasMoreElements()) //遍历所有的网络接口  
	            {  
	                NetworkInterface netInterface = allNetInterfaces.nextElement();  
	                Enumeration<InetAddress> addresses = netInterface.getInetAddresses(); //同样再定义网络地址枚举类  
	                while (addresses.hasMoreElements())  
	                {  
	                    ip = addresses.nextElement();  
	                    if( ip.isSiteLocalAddress()   
	    			            && !ip.isLoopbackAddress()
	    			            && ip instanceof Inet4Address
	    			            && ip.getHostAddress().indexOf(":")==-1)   
	    			    {
	                    	ipAddr = ip.getHostAddress();
	    			        return ipAddr;   
	    			    }
	                }
	            }
	        } catch (SocketException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
		}
		return ipAddr;
	}

	/**
	 * 
	 * @param block(取第几段)
	 * @return
	 */
	public static String getServerIpBlock(int block){
		String serverIp = getServerIp();
		if(StringUtils.isNotBlank(serverIp)){
			String[] ipBlock = serverIp.split("\\.");
			return ipBlock[block - 1];
		}else{
			return null;
		}
	}
	
	// 测试
	public static void main(String[] args) {
		String ip = "124.115.17.160";
		String address = "";
		try {
			address = IPUtil.getAddresses("ip=" + ip, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(address);
	}
}
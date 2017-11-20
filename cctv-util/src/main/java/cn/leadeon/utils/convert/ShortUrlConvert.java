/*
 * 文 件 名:  ShortUrlConvert.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月3日,  All rights reserved  
 */
package cn.leadeon.utils.convert;
import java.security.MessageDigest;
 /**
  *  
  * 长短连接地址转换
  * 
  * @author  liudongdong
  * @version  [1.0, 2014年5月5日]
  * @since [中国移动手机营业厅BSS系统]
  */
public class ShortUrlConvert{  
    
	/**
	 * 
	 * 长短连接Url转换
	 * @param url
	 * @param md5Key /自定义生成MD5加密字符串的混合KEY  
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public static String[] ShortText(String url,String md5Key){  
            
        String[] chars = new String[]{      //要使用生成URL的字符  
            "a","b","c","d","e","f","g","h",  
            "i","j","k","l","m","n","o","p",  
            "q","r","s","t","u","v","w","x",  
            "y","z", "0","1","2","3","4","5",  
            "6","7","8","9","A","B","C","D",  
            "E","F","G","H","I","J","K","L",  
            "M","N","O","P","Q","R","S","T",  
            "U","V", "W","X","Y","Z"  
        };  
        String hex = MD5Encode(md5Key + url);  
        int hexLen = hex.length();  
        int subHexLen = hexLen/8;  
        String[] ShortStr = new String[4];  
  
        for(int i = 0;i < subHexLen;i++){  
            String outChars = "";  
            int j = i+1;  
            String subHex = hex.substring(i*8,j*8);  
            long idx = Long.valueOf("3FFFFFFF",16) & Long.valueOf(subHex,16);  
              
            for(int k = 0;k < 6;k++){  
                int index = (int)(Long.valueOf("0000003D",16) & idx);//这里取5位没有要求吗？不是取连续的5位  
                outChars += chars[index];  
                idx = idx >> 5;  
            }  
            ShortStr[i] = outChars;  
        }  
        return ShortStr;  
    }  
  
    private final static String[] hexDigits = {  
        "0","1","2","3","4","5","6","7",  
        "8","9","a","b","c","d","e","f"  
    };  
    public static String byteArrayToHexString(byte[] b){  
        StringBuffer resultSb = new StringBuffer();  
        for(int i = 0;i < b.length;i++){  
            resultSb.append(byteToHexString(b[i]));  
        }  
        return resultSb.toString();  
    }  
      
    private static String byteToHexString(byte b){  
        int n = b;  
        if(n < 0)  
            n = 256 + n;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
  
    public static String MD5Encode(String origin){  
        String resultString = null;  
        try{  
            resultString = new String(origin);  
            MessageDigest md = MessageDigest.getInstance("MD5");  
  
           String tempResult= resultString.trim();  
  
            resultString = byteArrayToHexString(md.digest(tempResult.getBytes("UTF-8")));  
        }catch(Exception ex){}  
        return resultString;  
    }  
  
   
    
  /*  public static void main(String[] args){  
    	*//**
    	 * 转短连接测试
    	 *//*
        String url = "http://www.sunchis.com";  
        String md5Key="liudongdong";
        for(String string:ShortText(url,md5Key)){  
            System.out.println("结果："+string); 
        }  
    } */
} 
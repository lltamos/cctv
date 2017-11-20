package cn.leadeon.utils.payment;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;




	/**
	DES加密介绍
	DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
	后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
	24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
	。
	注意：DES加密和解密过程中，密钥长度都必须是8的倍数
	*/
	public class Des {
	public Des() {
	}
	//测试
	public static void main(String args[]) {
	//待加密内容
	String str = "13627571605";
	//密码，长度要是8的倍数
	String password = "88888888";
	
	String str1 = encrypt(str, password);
	//直接将如上内容解密	
//		String st = "[B@7689a5";
		System.out.println("加密后:"+str1);
		String userId;
		try {
			userId = decrypt(str1, password);
			System.out.println("解密后:"+userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	* 加密
	* @param datasource byte[]
	* @param password String
	* @return byte[]
	*/
	public static String encrypt(String datasource, String password) { 
	try{
	SecureRandom random = new SecureRandom();
	DESKeySpec desKey = new DESKeySpec(password.getBytes());
	//创建一个密匙工厂，然后用它把DESKeySpec转换成
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	SecretKey securekey = keyFactory.generateSecret(desKey);
	//Cipher对象实际完成加密操作
	Cipher cipher = Cipher.getInstance("DES");
	//用密匙初始化Cipher对象
	cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
	//现在，获取数据并加密
	//正式执行加密操作
//	return cipher.doFinal(datasource);
	byte[] buf = cipher.doFinal(datasource.getBytes());
	return Base64Utils.encode(buf);
	}catch(Throwable e){
	e.printStackTrace();
	}
	return null;
	}
	/**
	* 解密
	* @param src byte[]
	* @param password String
	* @return byte[]
	* @throws Exception
	*/
	public static String decrypt(String src, String password) throws Exception {
	// DES算法要求有一个可信任的随机数源
	SecureRandom random = new SecureRandom();
	// 创建一个DESKeySpec对象
	DESKeySpec desKey = new DESKeySpec(password.getBytes());
	// 创建一个密匙工厂
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	// 将DESKeySpec对象转换成SecretKey对象
	SecretKey securekey = keyFactory.generateSecret(desKey);
	// Cipher对象实际完成解密操作
	Cipher cipher = Cipher.getInstance("DES");
	// 用密匙初始化Cipher对象
	cipher.init(Cipher.DECRYPT_MODE, securekey, random);
	// 真正开始解密操作
//	return cipher.doFinal(src);
	byte[] buf = cipher.doFinal(Base64Utils.decode(src.toCharArray()));  
    
    return new String(buf);
	}
	 static class Base64Utils {  
		  
	        static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();  
	        static private byte[] codes = new byte[256];  
	        static {  
	            for (int i = 0; i < 256; i++)  
	                codes[i] = -1;  
	            for (int i = 'A'; i <= 'Z'; i++)  
	                codes[i] = (byte) (i - 'A');  
	            for (int i = 'a'; i <= 'z'; i++)  
	                codes[i] = (byte) (26 + i - 'a');  
	            for (int i = '0'; i <= '9'; i++)  
	                codes[i] = (byte) (52 + i - '0');  
	            codes['+'] = 62;  
	            codes['/'] = 63;  
	        }  
	          
	        /** 
	         * 将原始数据编码为base64编码 
	         */  
	        static public String encode(byte[] data) {  
	            char[] out = new char[((data.length + 2) / 3) * 4];  
	            for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {  
	                boolean quad = false;  
	                boolean trip = false;  
	                int val = (0xFF & (int) data[i]);  
	                val <<= 8;  
	                if ((i + 1) < data.length) {  
	                    val |= (0xFF & (int) data[i + 1]);  
	                    trip = true;  
	                }  
	                val <<= 8;  
	                if ((i + 2) < data.length) {  
	                    val |= (0xFF & (int) data[i + 2]);  
	                    quad = true;  
	                }  
	                out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];  
	                val >>= 6;  
	                out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];  
	                val >>= 6;  
	                out[index + 1] = alphabet[val & 0x3F];  
	                val >>= 6;  
	                out[index + 0] = alphabet[val & 0x3F];  
	            }  
	              
	            return new String(out);  
	        }  
	  
	        /** 
	         * 将base64编码的数据解码成原始数据 
	         */  
	        static public byte[] decode(char[] data) {  
	            int len = ((data.length + 3) / 4) * 3;  
	            if (data.length > 0 && data[data.length - 1] == '=')  
	                --len;  
	            if (data.length > 1 && data[data.length - 2] == '=')  
	                --len;  
	            byte[] out = new byte[len];  
	            int shift = 0;  
	            int accum = 0;  
	            int index = 0;  
	            for (int ix = 0; ix < data.length; ix++) {  
	                int value = codes[data[ix] & 0xFF];  
	                if (value >= 0) {  
	                    accum <<= 6;  
	                    shift += 6;  
	                    accum |= value;  
	                    if (shift >= 8) {  
	                        shift -= 8;  
	                        out[index++] = (byte) ((accum >> shift) & 0xff);  
	                    }  
	                }  
	            }  
	            if (index != out.length)  
	                throw new Error("miscalculated data length!");  
	            return out;  
	        }  
	    }  
	}



package cn.leadeon.utils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <AES128位对称加解密>
 * <功能详细描述>
 * 
 * @author  yunhaibin
 * @version  [版本号, 2016-4-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AESCipher {
    
	   
	 /*
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String secretkey = "L4.22+%c$*On2016";
    
     private byte[] ivByte = new byte[]{
             112, (byte) 150, (byte) 156, 39, 8, (byte) 166, 46, (byte) 177, (byte) 153, (byte) 238, 13, 98, 79, 42, 40, 110
     };
    private static AESCipher instance = null;
    
    private AESCipher() {}
    
    public static AESCipher getInstance() {
        if (instance == null) {
            instance = new AESCipher();
        }
        
        return instance;
    }
    
    /**
     * 加密
     * @param content 需要加密的内容
     * @return
     */
    public String encrypt(String content) {

        try {
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = secretkey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivByte);//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
            return this.parseByte2HexStr(encrypted); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**解密
     * @param content  待解密内容
     * @return
     */
    public String decrypt(String encContent) {

        try {
            
            byte[] content = this.parseHexStr2Byte(encContent);
            
            byte[] raw = secretkey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            // byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(content);
            String originalString = new String(original,"UTF-8");
            return originalString;
        } catch (Exception e) {
        	return null;
        }
    }
    
    
    /**
     * 将二进制转换成16进制字符串
     * 
     * @param buf
     * @return
     */
    private String parseByte2HexStr(byte buf[]) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    
    
    /**
     * 将16进制字符串转换为二进制
     * 
     * @param hexStr
     * @return
     */
    private byte[] parseHexStr2Byte(String hexStr) {

        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    public static void main(String[] args) {
    	 
        AESCipher util = AESCipher.getInstance();
        String enc = util.encrypt("13720531615");//"7428360E1BCBC3805B3CBDA91986EF4E";
        System.out.println("原数据:" + "13720531615");
        System.out.println("加密：" + enc);
	}
    

}
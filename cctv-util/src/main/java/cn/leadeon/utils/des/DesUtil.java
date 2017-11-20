package cn.leadeon.utils.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;

/**
 * Created by Bjcathay on 2017/11/2.
 */
public class DesUtil {
    /**
     * 加密
     *
     * @param srcStr
     * @param charset
     * @param sKey
     * @return
     */
    public static String encrypt(String srcStr, Charset charset, String sKey) {
        byte[] src = srcStr.getBytes(charset);
        byte[] buf = Des.encrypt(src, sKey);
        return Des.parseByte2HexStr(buf);
    }

    /**
     * 解密
     *
     * @param hexStr
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr, Charset charset, String sKey) throws Exception {
        byte[] src = Des.parseHexStr2Byte(hexStr);
        byte[] buf = Des.decrypt(src, sKey);
        return new String(buf, charset);
    }


    private static class Des {


        /**
         * 加密
         *
         * @param data
         * @param sKey
         * @return
         */
        public static byte[] encrypt(byte[] data, String sKey) {
            try {
                byte[] key = sKey.getBytes();
                // 初始化向量
                IvParameterSpec iv = new IvParameterSpec(key);
                DESKeySpec desKey = new DESKeySpec(key);
                // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey securekey = keyFactory.generateSecret(desKey);
                // Cipher对象实际完成加密操作
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                // 用密匙初始化Cipher对象
                cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
                // 现在，获取数据并加密
                // 正式执行加密操作
                return cipher.doFinal(data);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 解密
         *
         * @param src
         * @param sKey
         * @return
         * @throws Exception
         */
        public static byte[] decrypt(byte[] src, String sKey) throws Exception {
            byte[] key = sKey.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
            // 真正开始解密操作
            return cipher.doFinal(src);
        }

        /**
         * 将二进制转换成16进制
         *
         * @param buf
         * @return
         */
        public static String parseByte2HexStr(byte buf[]) {
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
         * 将16进制转换为二进制
         *
         * @param hexStr
         * @return
         */
        public static byte[] parseHexStr2Byte(String hexStr) {
            if (hexStr.length() < 1) return null;
            byte[] result = new byte[hexStr.length() / 2];
            for (int i = 0; i < hexStr.length() / 2; i++) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }
            return result;
        }
    }


    private static final String SKEY = "abcdefgh";
    private static final Charset CHARSET = Charset.forName("gb2312");

    public static void main(String[] args) {
        // 待加密内容
        String str = "%22user_id%22:56494680,%22object_id%22:%22cctv1%22,%22object_title%22:%22CCTV-1%20%E7%BB%BC%E5%90%88%E9%A2%91%E9%81%93%E9%AB%98%E6%B8%85%E7%9B%B4%E6%92%AD%22,%22collect_type%22:%223%22,%22source%22:%221%22,%22product%22:%229%22,%22callback%22:%22cb%22";

        String encryptResult = DesUtil.encrypt(str, CHARSET, SKEY);
        System.out.println(encryptResult);
        // 直接将如上内容解密
        String decryResult = "";
        try {
            decryResult = DesUtil.decrypt(encryptResult, CHARSET, SKEY);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(decryResult);
    }
}

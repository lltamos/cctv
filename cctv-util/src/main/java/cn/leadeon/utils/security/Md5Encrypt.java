package cn.leadeon.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 */
public class Md5Encrypt {
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String md5Encode(String originStr) throws Exception {
        String md5String = "";
        StringBuffer buffer = new StringBuffer();
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(originStr.getBytes("UTF-8"));

        for (byte b : bytes) {
            buffer.append(Integer.toHexString((b & 0xf0) >>> 4));
            buffer.append(Integer.toHexString(b & 0x0f));
        }
        md5String = buffer.toString();
        return md5String;
    }
	
	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param text
	 *            明文
	 * 
	 * @return 密文
	 */
	public static String md5(String text) {
		MessageDigest msgDigest = null;

		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}

		try {
			msgDigest.update(text.getBytes("UTF-8"));

		} catch (UnsupportedEncodingException e) {

			throw new IllegalStateException(
					"System doesn't support your  EncodingException.");

		}

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));

		return md5Str;
	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;

		char[] out = new char[l << 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}

		return out;
	}

	public static void main(String[] args) {
		System.out.println(md5("activityId=0324flow=100openAccount=1partner=60000000014transactionId=032420160113112913bada79e1d2d4cduser=15002925697key-60000000014-rfmAfKlfd0"));
	}

}
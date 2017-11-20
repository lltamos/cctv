package cn.leadeon.utils.payment;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DigestUtil {

	public static String cryptMd5(String source, String key) {
		byte[] k_ipad = new byte[64];
		byte[] k_opad = new byte[64];
		byte[] keyb;
		byte[] value;
		try {
			keyb = key.getBytes("UTF-8");
			value = source.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			keyb = key.getBytes();
			value = source.getBytes();
		}
		Arrays.fill(k_ipad, keyb.length, 64, new Integer(54).byteValue());
		Arrays.fill(k_opad, keyb.length, 64, new Integer(92).byteValue());
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5C);
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte[] dg = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return toHex(dg);
	}

	public static String toHex(byte[] input) {
		if (input == null) {
			return null;
		}
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xFF;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}

	public static String MD5Sign(String signData, String signkey) {
		String value = cryptMd5(signData, "");
		String value2 = cryptMd5(value, signkey);
		return value2;
	}
	
	public static void main(String[] args){
		System.out.println(MD5Sign("10http://111.20.119.234:8220/http://111.20.119.234:8220/TG/v1/payResult/notifyReq1234567894561254182951075681000049^49900^|123456789456125410000", "test1"));
	}
}

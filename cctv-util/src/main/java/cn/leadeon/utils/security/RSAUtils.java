/*
 * 文 件 名:  RSAUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2016-1-7,  All rights reserved  
 */
package cn.leadeon.utils.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * <RSA加解密工具类> <功能详细描述>
 * 
 * @author yunhaibin
 * @version [版本号, 2016-1-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class RSAUtils {

	/**
	 * ALGORITHM RSA非对称加密
	 */
	private static final String ALGORITHM = "RSA";
	
	/**
	 * PKCS8_KEY 私钥信息（******请勿外泄******）
	 */
	private static final String PKCS8_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKMSMHndMOr1TIzcFndXar2L7iWTgRb2TyLw1bXXDw/W/0CJXWz1VKY/dwe2pabqXHsRTi1hHi9nVyaWHQ5c8om3AzHj3cr8CW6IjmhuJS5S2+AdPzlw9Nf3xErxrwGTGSV5jO6xzIkjm6iIXfPtXHne9NcbxXiVncrMcFOaLCMNAgMBAAECgYAD0cY+5HZj2nD7j6AbFXoTjHZ8fNL2NbiuydNHmgzpQxDhcxY0Gh7sceoYzSdeHHkkDMi2+WJam+IHrlZp4rVviKN7W6AjV3ZkhLLlII0tRiLRReUDMYftzzywTZbo5YMG9JRt39GL9wE8nnsaO7b0jHTIWT3SDrvMSwHXKRJ25QJBAO0PZY0ny0h2LUPAv48PlDN5ZGtcq6ZfcrMYOflO2HbOogkW9308VcHe1/FXNJ6PtoIjnUnyxit3cWtnnAOKMj8CQQCwGX1vpP5EYWRxbiBf83E14lTiXnQEbyWy/oeq9NfSFrw1W7TJlxMaKEfhnw+OpLFy8XIvhcOABX1vWr4bcr+zAkEA0J1RB7QyT3U4Bjy0FqhmChpUxapKn+G1JWg3dG7vTTYwIAGnD/2tliuOKyNL+hGMUeAXhcDwpcW5+QO3puHRrwJANqmC5T/q6WDt48PSatZPQvGhda7qBmJV6mzVwfxEbeM+wrVXteeeN1VfkqpkEtwOdOZ7kkLMP5X0rLcXIcAJvwJBAMm0spPwD+bKVQhLg9ruxQliRhf3yWXdviSAvCXTZWMmdsH3rucgcaDeuCYuM4ymxQVThbV/tJjKeKi3bvrviqk=";
	
	/**
	 * PRIVATE_KEY 初始化pkcs8私钥
	 */
	private static final PrivateKey PRIVATE_KEY = initPrivateKey(PKCS8_KEY);
	
	/** 
	 * <初始化pkcs8密钥>
	 * <功能详细描述>
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @see [类、类#方法、类#成员]
	 */
	private static final PrivateKey initPrivateKey(String pkcs8key) {
		byte[] privKeyByte = Base64.decode(pkcs8key);
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privKeyByte);
		KeyFactory kf = null;
		PrivateKey privKey = null;
		try {
			kf = KeyFactory.getInstance(ALGORITHM);
			privKey = kf.generatePrivate(privKeySpec);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return privKey;
	}

	/** 
	 * <RSA解密>
	 * <功能详细描述>
	 * @param text 密文
	 * @return 解密后明文
	 * @see [类、类#方法、类#成员]
	 */
	public static final String decrypt(String text) {
		byte[] encryptByte = Base64.decode(text);
		byte[] dectyptedText = null;
		String result = "";
		try {
			// 初始化RSA Cipher对象
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 使用私钥解密文本
			cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
			dectyptedText = cipher.doFinal(encryptByte);
			result = new String(dectyptedText);
			// 截断解密后明文
			result = result.substring(7, result.length()-14);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "XsDbrggtWKRU2e%2BNgZa7ShpkXRjxcqP2TGl%2Bs45FIXoPmfVo13Dt1ejiHxCYq7pW%2FobnB4mms8OuAEqK%2BoKOdohX1t68rV8KKO4qubHhSzSqXEXtErSwF0CuYauE2bKLCe%2FPGarMapRBC67PCw74mbw7LMUH0WlIjorhkihcAlc%3D";
		str=URLDecoder.decode(str, "utf-8");
		System.out.println(RSAUtils.decrypt(str));
	}

//	/**
//	 * <RSA加密> <功能详细描述>
//	 * 
//	 * @param text
//	 * @param key
//	 * @return
//	 * @see [类、类#方法、类#成员]
//	 */
//	public static byte[] encrypt(String text, PublicKey key) {
//		byte[] cipherText = null;
//		try {
//			// get an RSA cipher object and print the provider
//			final Cipher cipher = Cipher.getInstance(ALGORITHM);
//			// encrypt the plain text using the public key
//			cipher.init(Cipher.ENCRYPT_MODE, key);
//			cipherText = cipher.doFinal(text.getBytes());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cipherText;
//	}
}

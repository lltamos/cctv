/*
 * 文 件 名:  AES128.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-4-23,  All rights reserved  
 */
package cn.leadeon.utils.security;

import java.security.Key;
import javax.crypto.Cipher;

/**
 * <AES128加密>
 * <功能详细描述>
 * 
 * @author  fei.dong
 * @version  [1.0, 2014-4-23]
 * @since [中国移动手机营业厅BSS系统]
 */
public class AES128 {
    private Cipher encryptCipher = null;
    
    private Cipher decryptCipher = null;
    
    private static String byteArr2HexStr(byte[] arrB)
        throws Exception
    {
        int iLen = arrB.length;
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++)
        {
            int intTmp = arrB[i];
            while (intTmp < 0)
            {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16)
            {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        String result = new String(sb.toString().getBytes());
        return result;
    }
    
    private static byte[] hexStr2ByteArr(String strIn)
        throws Exception
    {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2)
        {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte)Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    
    public AES128(String strKey)
        throws Exception
    {
        Key key = new javax.crypto.spec.SecretKeySpec(strKey.getBytes(), "AES");
        
        encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        
        decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }
    
    private byte[] encrypt(byte[] arrB)
        throws Exception
    {
        return encryptCipher.doFinal(arrB);
    }
    
    public String encrypt(String strIn)
        throws Exception
    {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }
    
    private byte[] decrypt(byte[] arrB)
        throws Exception
    {
        return decryptCipher.doFinal(arrB);
    }
    
    public String decrypt(String strIn)
        throws Exception
    {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }	
}

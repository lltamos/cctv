package cn.leadeon.utils.security;

public class PointSlqMD5 {

    

    public static String generateCheckCode(String source) {


          String result = null;

          char hexDigits[] = { // Used to convert 16-byte hexadecimal characters.  

          '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',

                      'e', 'f' };

          try {

                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");

                md.update(source.getBytes("UTF-8"));

                byte tmp[] = md.digest(); // MD5 calculation is a 128-bit long integer, that is with 16-byte byte.  

                char str[] = new char[16 * 2]; // Each byte expressed in hexadecimal using 2 characters, so that 32 bytes as hexadecimal. 

                int k = 0; // The index of character in convert result.  

                for (int i = 0; i < 16; i++) { // Convert each byte to hexadecimal of MD5.  

                      byte byte0 = tmp[i];

                      str[k++] = hexDigits[byte0 >>> 4 & 0xf];

                      str[k++] = hexDigits[byte0 & 0xf];

                }

                result = new String(str); // Convert the result from byte to string.  



          } catch (Exception e) {

                e.printStackTrace();

          }

          return result;  

    }
}

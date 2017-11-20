package cn.leadeon.utils.crc32;

/**
 * CRC32算法工具类
 * @author lixuming
 *
 */
public class Crc32Util {
	
	private Crc32Util(){}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for(int i=0;i<10;i++){
//			System.out.println(Crc32Util.crc32("13571490362"));
//		}
		
		
//		集团段冉： 13501361810  
//	        彩讯刘学谦：13810232862
		
//		String cellNum = "13810232862";
//		System.out.println(cellNum+"910061:"+(Crc32Util.crc32(cellNum+"910061")%1024));
		
		System.out.println(Crc32Util.crc32("13571490362")%32);
		
	}
	
	
	public static final int crc32(String str) {
        if (str != null && !"".equals(str)) {
            return crc32(str.getBytes());
        }

        return 0;
    }


    private static final int crc32(byte[] array) {
        if (array != null) {
            return crc32(array, 0, array.length);
        }

        return 0;
    }


    public static final int crc32(byte[] array, int offset, int length) {
        PureJavaCrc32 crc32 = new PureJavaCrc32();
        crc32.update(array, offset, length);
        return (int) (crc32.getValue() & 0x7FFFFFFF);
    }

}

/*
 * 文 件 名:  FileUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cn.leadeon.utils.convert.DataTypeConvertUtils;

/**
 * 文件操作工具类
 * @author sunwm
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class FileUtils {
	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 获取文件名称[不含后缀名]
	 * 
	 * @param
	 * @return String
	 */
	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
	}
	
	/**
	 * 获取文件名称[不含后缀名]
	 * 不去掉文件目录的空格
	 * @param
	 * @return String
	 */
	public static String getFilePrefix2(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}
	
	/**
	 * 文件复制
	 *方法摘要：这里一句话描述方法的用途
	 *@param
	 *@return void
	 */
	public static void copyFile(String inputFile,String outputFile) throws FileNotFoundException{
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;  
		byte[] buf = new byte[10240];
        try {  
        	while((temp = fis.read(buf))!=-1){   
        		fos.write(buf, 0, temp);   
            }   
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally{
            try {
            	fis.close();
            	fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } 
	}
	/**
	 * 判断文件是否为图片<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isPicture(String filename) {
		// 文件名称为空的场合
		if (DataTypeConvertUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		//String tmpName = getExtend(filename);
		String tmpName = filename;
		// 声明图片后缀名数组
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" },
				{ "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
				{ "jpeg", "5" }, { "jpg", "6" }, { "png", "7" },
				{ "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
		// 遍历名称数组
		for (int i = 0; i < imgeArray.length; i++) {
			// 判断单个类型文件的场合
			if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断文件是否为DWG<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isDwg(String filename) {
		// 文件名称为空的场合
		if (DataTypeConvertUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		String tmpName = getExtend(filename);
		// 声明图片后缀名数组
		if (tmpName.equals("dwg")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 删除指定的文件
	 * 
	 * @param strFileName
	 *            指定绝对路径的文件名
	 * @return 如果删除成功true否则false
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
		
			return false;
		}

		
		return fileDelete.delete();
	}
	
	/**
	 * 
	 * @param file
	 * @param lines
	 * @return
	 * @throws IOException
	 */
	public static List<String> readNLine(File file, final int lines) throws IOException{
		List<String> list = new ArrayList<String>();
		Charset cs = Charset.forName("utf-8");
		CharsetDecoder decoder = cs.newDecoder();
        Reader reader1 = new InputStreamReader(new FileInputStream(file), decoder);
        
        try (BufferedReader reader = new BufferedReader(reader1)) {
			for(int i=0 ; i< lines ; i++){
				String line = reader.readLine();
				if (line == null){
					break;
				}
				list.add(line);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param file
	 * @param skipLines
	 * @return
	 * @throws IOException
	 */
	public static List<String> readAllLineSkipN(File file, final int skipLines) throws IOException{
		List<String> list = new ArrayList<String>();
		Charset cs = Charset.forName("utf-8");
		CharsetDecoder decoder = cs.newDecoder();
        Reader reader1 = new InputStreamReader(new FileInputStream(file), decoder);
        
        try (BufferedReader reader = new BufferedReader(reader1)) {
        	long i=0;
        	for(;;){
				String line = reader.readLine();
				if (line == null){
					break;
				}
				i++;
				if(i <= skipLines){
					continue;
				}
				list.add(line);
			}
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param filePath
	 * @param line
	 * @return
	 * @throws IOException
	 */
	public static List<String> readNLine(String filePath, final int lines) throws IOException{
		List<String> list = new ArrayList<String>();
		boolean isExist = true;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), Charset.forName("utf-8"))) {
			for(int i=0 ; i< lines ; i++){
				if(!isExist){
					break;
				}
				String line = reader.readLine();
				if (line == null){
					isExist = false;
					break;
				}
				list.add(line);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<String> readAllLines(String filePath) throws IOException{
		List<String> list = new ArrayList<String>();
		boolean isExist = true;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), Charset.forName("utf-8"))) {
			for(;;){
				if(!isExist){
					break;
				}
				String line = reader.readLine();
				if (line == null){
					isExist = false;
					break;
				}
				list.add(line);
			}
		}
		return list;
	}
	
	/**
	 * 按行读取文件（按指定大小分集合）
	 * @param filePath
	 * @param line_size
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> readAllLines(String filePath, final int line_size) throws IOException{
		List<List<String>> list = new ArrayList<List<String>>();
		boolean isExist = true;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), Charset.forName("utf-8"))) {
			for(;;){
				if(!isExist){
					break;
				}
				
				List<String> result = new ArrayList<String>(line_size);
				for(int i=0; i<line_size; i++){
					String line = reader.readLine();
					if (line == null){
						isExist = false;
						break;
					}
					result.add(line);
				}
				list.add(result);
			}
		}
		return list;
	}
	
	public static String readByNio(File file) {
		String string = null;
		if(null == file){
			return null;
		}
		FileInputStream fin = null;
		FileChannel fc = null;
		try {
			fin = new FileInputStream(file);
			fc = fin.getChannel();
			int size = (int) fc.size();
			ByteBuffer buffer=ByteBuffer.allocate(size);
			fc.read(buffer);
//			Buffer bf = buffer.flip();
			byte[] bt=buffer.array();
			string = new String(bt,"utf-8");
			buffer.clear();
			buffer = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(null!=fc)fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(null!=fin)fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return string;
	}
}
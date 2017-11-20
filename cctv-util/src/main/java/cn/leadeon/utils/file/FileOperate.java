/*
 * 文 件 名:  FileOperate.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014年4月22日,  All rights reserved  
 */
package cn.leadeon.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import cn.leadeon.utils.other.UnicodeReader;

/**
 * 文件io相关操作
 * @author sunwm
 * @version [1.0, 2014年4月16日]
 * @since [中国移动手机营业厅BSS系统]
 */
public class FileOperate {
	static String message;
	static FileInputStream fis;// 文件输入流:读取文件中内容
	static FileChannel in;// 文件通道:双向,流从中而过
	static FileChannel out;// 文件通道:双向,流从中而过
	static FileOutputStream fos;// 文件输出流:向文件中写入内容
	static ByteBuffer buffer = ByteBuffer.allocate(1024 * 3);// 设置缓存区的大小

	public static String readTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			InputStreamReader isr;
			FileInputStream fs = new FileInputStream(filePathAndName);

			if (encoding.equals(""))
				isr = new InputStreamReader(fs);
			else
				isr = new InputStreamReader(fs, encoding);

			BufferedReader br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null)
					str.append(data);
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	/**
	 * 读取源文件 并且带格式
	 * 
	 * @param filePathAndName
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readFormatTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			InputStreamReader isr;
			FileInputStream fs = new FileInputStream(filePathAndName);

			if (encoding.equals(""))
				isr = new InputStreamReader(fs);
			else
				isr = new InputStreamReader(fs, encoding);

			BufferedReader br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null)
					str.append(data).append("\n");
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	/**
	 * 通过ＮＩＯ读到文件内容（曹爷的方法）
	 * 
	 * @param filePathAndName
	 * @param encoding
	 * @return
	 */
	public static String readTxtNio(String filePathAndName, String encoding) {
		StringBuffer stringBuffer = new StringBuffer();
		Charset charset = Charset.forName(encoding);
		try {
			fis = new FileInputStream(filePathAndName);
			in = fis.getChannel();
			try {
				while (in.read(buffer) != -1) {
					buffer.flip();
					CharBuffer charBuffer = charset.decode(buffer);
					stringBuffer.append(charBuffer.toString());
					buffer.clear();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return stringBuffer.toString();
	}

	/**
	 * 通过ＮＩＯ写文件内容（曹爷的方法）
	 * 
	 * @param content
	 * @param path
	 * @throws IOException
	 */
	public static void saveContentNio(String content, String path)
			throws IOException {
		File f = new File(path);
		FileWriter fileWriter = new FileWriter(f);
		fileWriter.write("");
		fileWriter.write(content);
		fileWriter.close();
	}

	public static String readTxtNoBom(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		String st = "";
		try {
			BufferedReader reader = null;
			CharArrayWriter writer = null;
			UnicodeReader r = null;
			if (encoding.equals("")) {
				r = new UnicodeReader(new FileInputStream(filePathAndName),
						null);
			} else {
				r = new UnicodeReader(new FileInputStream(filePathAndName),
						encoding);
			}
			char[] buffer = new char[16 * 1024]; // 16k buffer
			int read;
			try {
				reader = new BufferedReader(r);
				writer = new CharArrayWriter();
				while ((read = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, read);
				}
				writer.flush();
				st = new String(writer.toCharArray());
			} catch (IOException ex) {
				throw ex;
			} finally {
				try {
					writer.close();
					reader.close();
					r.close();
				} catch (Exception ex) {
				}
			}

		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	public static String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			File myFilePath = new File(txt);
			txt = folderPath;
			if (!(myFilePath.exists()))
				myFilePath.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
			message = "创建目录操作出错";
		}
		return txt;
	}

	public static String createFolders(String folderPath, String paths) {
		StringTokenizer st;
		int i;
		String txts = folderPath;
		try {
			txts = folderPath;
			st = new StringTokenizer(paths, "|");
			for (i = 0; st.hasMoreTokens(); ++i) {
				String txt = st.nextToken().trim();
				if (txts.lastIndexOf("/") != -1)
					txts = createFolder(txts + txt);
				else
					txts = createFolder(txts + txt + "/");
			}
		} catch (Exception e) {
			message = "创建目录操作出错";
		}
		return txts;
	}

	public static void createFile(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!(myFilePath.exists()))
				myFilePath.createNewFile();

			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
			resultFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			message = "创建文件操作出错";
		}
	}

	public static void appendWrite(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!(myFilePath.exists()))
				myFilePath.createNewFile();
			FileWriter fileWriter = new FileWriter(myFilePath);
			fileWriter.write(fileContent);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			message = "创建文件操作出错";
		}
	}

	public static void createFile(String filePathAndName, String fileContent,
			String encoding) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!(myFilePath.exists()))
				myFilePath.createNewFile();

			PrintWriter myFile = new PrintWriter(myFilePath, encoding);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
		} catch (Exception e) {
			message = "创建文件操作出错";
		}
	}

	public static boolean delFile(String filePathAndName) {
		boolean bea = false;
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
				bea = true;
			} else {
				bea = false;
				message = filePathAndName + "删除文件操作出错";
			}
		} catch (Exception e) {
			message = e.toString();
		}
		return bea;
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete();
		} catch (Exception e) {
			message = "删除文件夹操作出错";
		}
	}

	public static boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		if (!(file.exists()))
			return bea;

		if (!(file.isDirectory()))
			return bea;

		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; ++i) {
			if (path.endsWith(File.separator))
				temp = new File(path + tempList[i]);
			else
				temp = new File(path + File.separator + tempList[i]);

			if (temp.isFile())
				temp.delete();

			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
				bea = true;
			}
		}
		return bea;
	}

	public static void copyFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPathFile);
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "复制单个文件操作出错";
		}
	}

	public static void copyFile(String oldPathFile, String newPathFile,
			String filename) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			newPathFile = newPathFile + filename;
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPathFile);
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "复制单个文件操作出错";
		}
	}

	/**
	 * 复制整个文件夹的内容
	 * 
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件处1�7
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件处1�7
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "复制整个文件夹内容操作出错";
		}
	}

	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	public static String getMessage() {
		return message;
	}

	public static String getPathById(int infoId) {
		return getPathById(infoId + "");
	}

	public static String getPathById(String infoId) {
		StringBuffer path = new StringBuffer();
		while (infoId.length() > 1) {
			path.append(infoId.substring(0, 2)).append("/");
			infoId = infoId.substring(2);
		}
		if (infoId.length() > 0)
			path.append(infoId).append("/");
		return path.toString();
	}

	/**
	 * 得到FloadPathName下的第一级文件夹名
	 * 
	 * @param FloadPathName
	 * @return
	 */
	public static List<String> getFilesByFirstFolder(String FloadPathName) {
		LinkedList<String> tmpList = new LinkedList<String>();
		File dir = new File(FloadPathName);
		File[] file = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory())
				tmpList.add(file[i].getName());
		}
		return tmpList;

	}

	/**
	 * 得到FloadPathName下的第一级文件名
	 * 
	 * @param FloadPathName
	 * @return
	 */
	public static List<String> getFilesByFirstFile(String FloadPathName) {
		LinkedList<String> tmpList = new LinkedList<String>();
		File dir = new File(FloadPathName);
		File[] file = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile())
				tmpList.add(file[i].getName());
		}
		return tmpList;

	}

	/**
	 * 得到目录下的所有文件返回List 列表
	 * 
	 * @param FloadPathName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List getFiles(String FloadPathName) {
		List fileList = new LinkedList();
		LinkedList tmpList = new LinkedList();
		File dir = new File(FloadPathName);
		File[] file = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) {
				tmpList.add(file[i]);
			} else {
				fileList.add(file[i].getAbsolutePath());
			}
		}
		File tmp;
		while (!tmpList.isEmpty()) {
			tmp = (File) tmpList.removeFirst();
			if (tmp.isDirectory()) {
				file = tmp.listFiles();
				if (file == null)
					continue;
				for (int i = 0; i < file.length; i++) {
					if (file[i].isDirectory())
						tmpList.add(file[i]);
					else
						fileList.add(file[i].getAbsolutePath());
				}
			} else {
				fileList.add(tmp.getAbsolutePath());
			}
		}
		return fileList;

	}

	public static void deleteFiles(String FloadPathName) {
		List fileList = new LinkedList();
		LinkedList tmpList = new LinkedList();
		File dir = new File(FloadPathName);
		File[] file = dir.listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) {
				tmpList.add(file[i]);
			} else {
				if (file[i].getAbsolutePath().endsWith("java")) {
					delFile(file[i].getAbsolutePath());
				}
			}
		}
		File tmp;
		while (!tmpList.isEmpty()) {
			tmp = (File) tmpList.removeFirst();
			if (tmp.isDirectory()) {
				file = tmp.listFiles();
				if (file == null)
					continue;
				for (int i = 0; i < file.length; i++) {
					if (file[i].isDirectory())
						tmpList.add(file[i]);
					else {
						if (file[i].getAbsolutePath().endsWith("java")) {
							delFile(file[i].getAbsolutePath());
						}
					}
					fileList.add(file[i].getAbsolutePath());
				}
			} else {
				if (tmp.getAbsolutePath().endsWith("java")) {
					delFile(tmp.getAbsolutePath());
				}
			}
		}

	}

	/**
	 * 远程读取NGINX的内容
	 * 
	 * @param urlPath
	 * @param writePath
	 * @return
	 * @throws Exception
	 */
	public String read(String urlPath, String writePath) throws Exception {

		try {
			URL url = new URL(urlPath);
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setConnectTimeout(5000);
			urlCon.setReadTimeout(5000);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon
					.getInputStream()));
			BufferedWriter out = new BufferedWriter(new FileWriter(writePath));
			String inputLine = " ";
			while ((inputLine = in.readLine()) != null) {
				out.write(inputLine);
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		// List files = getFiles("E:\\app\\content\\cms\\css.jsp");
		// System.out.println(files.size());
		// List<String> list = FileOperate
		// .getFilesByFirstFolder("/usr/local/apache-tomcat-6.0.32/webappcds");
		// for (String string : list) {
		// if (string.startsWith("weike") && !string.endsWith("war"))
		// System.out.println(string);
		// }
		// createFile("E:\\tmp\\123.txt", "135416546516");
		// String ss = readTxtNoBom("/ect/ss", "UTF-8");
		// saveContentNio("我是草叶", "/home/zhangym/index.html");
		// FileOperate
		// .appendWrite(
		// "/usr/local/workspace/kaifa_workspace/weike-purchase-service/src/main/java/com/weike/service/account/pojo/AccountPojo2",
		// "123456");
		// FileOperate
		// .deleteFiles("/usr/local/workspace/kaifa_workspace/hessian/com/caucho");

	}
}
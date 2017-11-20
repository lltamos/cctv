/*
 * 文 件 名:  NativeUtils.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2015-1-8,  All rights reserved  
 */
package cn.leadeon.utils.security;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015-1-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 加载本地库so
 * 
 * @author  Administrator
 * @version  [版本号, 2015-1-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class NativeUtils {
 
    /**
     * Private constructor - this class will never be instanced
     */
    private NativeUtils() {
    }
 
    /**
     * Loads library from current JAR archive
     * 
     * The file from JAR is copied into system temporary directory and then loaded. The temporary file is deleted after exiting.
     * Method uses String as filename because the pathname is "abstract", not system-dependent.
     * 
     * @param filename The filename inside JAR as absolute path (beginning with '/'), e.g. /package/File.ext
     * @throws IOException If temporary file creation or read/write operation fails
     * @throws IllegalArgumentException If source file (param path) does not exist
     * @throws IllegalArgumentException If the path is not absolute or if the filename is shorter than three characters (restriction of {@see File#createTempFile(java.lang.String, java.lang.String)}).
     */
    public static void loadLibraryFromJar(String libName) throws IOException {
    	String systemType = System.getProperty("os.name");
    	Boolean osx = systemType.toLowerCase().indexOf("win") != -1;
    	String libExtension = osx ? ".dll" : ".so";
    	String libFullName = "/" + libName + libExtension;
 
        // Prepare temporary file
        File temp = File.createTempFile(libName, libExtension);
        temp.deleteOnExit();
 
        if (!temp.exists()) {
            throw new FileNotFoundException("File " + temp.getAbsolutePath() + " does not exist.");
        }
 
        // Prepare buffer for data copying
        byte[] buffer = new byte[1024];
        int readBytes;
 
        // Open and check input stream
        InputStream is = NativeUtils.class.getResourceAsStream(libFullName);
        if (is == null) {
            throw new FileNotFoundException("File " + libFullName + " was not found inside JAR.");
        }
 
        // Open output stream and copy data between source file in JAR and the temporary file
        OutputStream os = new FileOutputStream(temp);
        try {
            while ((readBytes = is.read(buffer)) != -1) {
                os.write(buffer, 0, readBytes);
            }
        } finally {
            // If read/write fails, close streams safely before throwing an exception
            os.close();
            is.close();
        }
 
        // Finally, load the library
        System.load(temp.getAbsolutePath());
        System.out.println("Start init load libjni.so");
    }
}
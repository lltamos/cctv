/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *                佛祖保佑             永无BUG
 */
package cn.leadeon.mfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.leadeon.mfile.event.EventListener;

/**
 * @author billy
 * @description TODO
 * @date 2017年8月7日 下午5:45:35
 */
public class MfileConfig extends EventListener{

	private static final Logger LOGGER = LoggerFactory.getLogger(MfileConfig.class);
	
	private static final String location_prefix = "classpath:";
	private static final long sleep_time = 60 * 1000L;
	
	//配置文件路径
	private String locations;
	
	private String[] fileLocations;
	
	private String[] filenames;
	
	
	protected Thread subThread = null;
	protected Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
		public void uncaughtException(Thread t, Throwable e) {
			LOGGER.error("check file has an error", e);
		}
	};
	
	protected MfileConfig(String locations){
		this.locations = locations;
	}
	
	protected void init(){
		if(null == locations){
			LOGGER.warn("Mfile locations is null!");
			return;
		}
		LOGGER.info("Mfile start init! "+locations);
		
		//转换路径，classpath:a.properties将转换为绝对路径
		parseLocation();
		
		try {
			for(int i=0; i<fileLocations.length; i++){
				loadFile(fileLocations[i], filenames[i]);
			}
		} catch (IOException e) {
			LOGGER.error("Mfile load error! "+this.toString(), e);
			return;
		}
		
		//启动子线程，轮询文件是否发生变化，变化后要重载
		subThread = new Thread(
			new Runnable() {
				public void run() {
					checkFile();
				}
			}
		);
		subThread.setUncaughtExceptionHandler(handler);
		subThread.start();
		
		LOGGER.info("Mfile start successful! ");
		LOGGER.info(this.toString());
	}
	
	protected void stop(){
		
	}
	
	private void checkFile(){
		List<File> fileList = new ArrayList<File>();
		Map<String,Long> fileLastModifieds = new HashMap<String,Long>();
		
		File file = null;
		for(int i=0 ; i<fileLocations.length; i++){
			file = new File(fileLocations[i]);
			fileList.add(file);
			fileLastModifieds.put(filenames[i], file.lastModified());
		}
		
				
		while(true){
			try {
				Thread.sleep(sleep_time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.debug("Mfile Check if the properties file changes");
			
			for(int j=0; j<fileList.size(); j++){
				//当前文件最后修改时间，不等于 初始化的文件最后修改时间。则重载文件
				if(fileList.get(j).lastModified() != fileLastModifieds.get(filenames[j]).longValue()){
					LOGGER.info("Mfile ["+filenames[j]+"] has changed!"+fileLocations[j]);
					try {
						loadFile(fileLocations[j], filenames[j]);
						fileLastModifieds.put(filenames[j], fileList.get(j).lastModified());
					} catch (IOException e) {
						LOGGER.error("Mfile properties changed, reload error!"+fileLocations[j], e);
					}
				}
			}
			
		}
	}
	
	
	private void loadFile(String fileLocation, String fileName) throws IOException{
		Map<String, String> props = new HashMap<String, String>();
		readFile(fileLocation, props);
		MfileProperties.load(fileName, props);
		//通知所有监听
		fireEvent(fileName, props);
	}
	
	private void readFile(String filename, Map<String, String> props) throws IOException{
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename), Charset.forName("utf-8"))) {
			for(;;){
				String line = reader.readLine();
				if (null == line){
					break;
				}
				
				if(line.startsWith("#") || "".equals(line)){
					continue;
				}
				String key = line.substring(0,line.indexOf("="));
				String value = line.substring(line.indexOf("=")+1, line.length());
				props.put(key, value);
			}
		} catch (IOException e) {
			LOGGER.error("read properties file error", e);
			throw e;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MfileConfig [locations=" + Arrays.toString(fileLocations) 
			+ ", listeners=" + getMfileListeners()
			+ "]";
	}

	private void parseLocation(){
		fileLocations = locations.split(",");
		
		filenames = new String[fileLocations.length];
		for(int i=0; i<fileLocations.length; i++){
			if(fileLocations[i].startsWith(location_prefix)){
				fileLocations[i] = convertLocation(fileLocations[i]);
			}
			filenames[i] = getFileName(fileLocations[i]);
		}
	}
	
	private String convertLocation(String fileLocations){
//		return fileLocations.replaceFirst(location_prefix, getClassPath());
		getClassPath();
		
		if(!classPath.endsWith(File.separator)){
			return classPath + File.separator + fileLocations.replace(location_prefix, "");
		}else{
			return classPath + fileLocations.replace(location_prefix, "");
		}
	}
	
	private String classPath;
	private synchronized String getClassPath(){
		if(null == classPath || "".equals(classPath)){
			classPath = getClassLoader().getResource("").getPath();
			//移除通配符/,/D:/git-local/pear-util/target/classes/
			Properties p = System.getProperties();
			String os = p.getProperty("os.name");
			//如果是windows操作系统
			if(null != os && os.startsWith("Windows")){
				classPath = classPath.substring(1);
			}
			//如果为window系统下,则把路径中的路径分隔符替换为window系统的文件路径分隔符 
			classPath = classPath.replace("/", java.io.File.separator);
		}
		return classPath;
	}
	
	
	public static ClassLoader getClassLoader(){
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
		}
		if (cl == null) {
			cl = MfileProperties.class.getClassLoader();
		}
		return cl;
	}
	
	
	private String getFileName(String location){
		location = location.trim();
		String fileName = location.substring(location.lastIndexOf(File.separator)+1, location.lastIndexOf("."));
		return fileName;
	}
	
	/**
	 * 根据key获取第一个符合的文件配置
	 * @param key
	 * @return
	 * 
	 * @return String
	 */
	public String getValue(String key){
		return MfileProperties.getValue(key);
	}

	/**
	 * 根据文件名、key获取配置
	 * @param filename
	 * @param key
	 * @return
	 * 
	 * @return String
	 */
	public String getValue(String filename,String key){
		return MfileProperties.getValue(filename, key);
	}
	
}

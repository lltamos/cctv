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

import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author billy
 * @description TODO
 * @date 2017年8月7日 下午4:30:56
 */
public class MfileProperties {

	private static final Map<String, Map<String, String>> _props = new ConcurrentHashMap<String, Map<String, String>>();
	
	//私有构造方法
	private MfileProperties(){}
	
	/**
	 * 根据key获取第一个符合的文件配置
	 * @param key
	 * @return
	 * 
	 * @return String
	 */
	public static String getValue(String key){
		for(String keyset : _props.keySet()){
			if(_props.get(keyset).containsKey(key)){
				return _props.get(keyset).get(key);
			}
		}
		return null;
	}

	/**
	 * 根据文件名、key获取配置
	 * @param filename
	 * @param key
	 * @return
	 * 
	 * @return String
	 */
	public static String getValue(String filename,String key){
		return _props.get(filename).get(key);
	}
	
	
	/**
	 * 载入数据
	 * @param filename
	 * @param props
	 * 
	 * @return void
	 */
	protected static void load(String filename, Map<String,String> props){
		_props.put(filename, props);
	}
}

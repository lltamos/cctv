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

import java.util.List;

import cn.leadeon.mfile.event.MfileListener;

/**
 * @author billy
 * @description TODO
 * @date 2017年8月7日 下午4:28:16
 */
public class MfileConfigFactoryBean {

	public static void main(String[] args){
		MfileConfigFactoryBean configFactory = new MfileConfigFactoryBean("classpath:biz.properties");
		
		System.out.println(getMfileConfig().toString());
		
		System.out.println(MfileProperties.getValue("dubbo.registry.file"));
		System.out.println(MfileProperties.getValue("dubbo.registry.address"));
		System.out.println(MfileProperties.getValue("cete"));
		System.out.println(MfileProperties.getValue("dsds"));
		System.out.println(MfileProperties.getValue("abc"));
		
		
		try {
			Thread.sleep(70*1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("reload==========================================");
		System.out.println(MfileProperties.getValue("dubbo.registry.file"));
		System.out.println(MfileProperties.getValue("dubbo.registry.address"));
		System.out.println(MfileProperties.getValue("cete"));
		System.out.println(MfileProperties.getValue("dsds"));
		System.out.println(MfileProperties.getValue("abc"));
	}
	
	
	private static MfileConfig config;
	
	public MfileConfigFactoryBean(String locations){
		synchronized (this) {
			if(null == config){
				config = new MfileConfig(locations);
			}
			
			config.init();
		}
	}
	
	public MfileConfigFactoryBean(String locations, List<MfileListener> listeners){
		synchronized (this) {
			if(null == config){
				config = new MfileConfig(locations);
			}
			
			if(null != listeners) {
				for(MfileListener listener : listeners) {
					config.addConfigurationListener(listener);
				}
			}
			config.init();
		}
		
		
	}
	
	/*public MfileConfigFactoryBean(String[] locations){
		
	}*/
	
	public static MfileConfig getMfileConfig(){
		return config;
	}
	

}

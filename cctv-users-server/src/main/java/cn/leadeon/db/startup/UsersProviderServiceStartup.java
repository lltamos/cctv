package cn.leadeon.db.startup;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * main 函数启动类
 * @author billy
 * @description TODO
 * @date 2017年10月24日 下午6:19:15
 */
public class UsersProviderServiceStartup {

	public static void main(String[] args) {
		System.out.println("------------users服务启动中..---------------");
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		context.registerShutdownHook();
		System.out.println("------------users启动完成..----------------");
		context.start();
		synchronized (UsersProviderServiceStartup.class) {
			while (true) {
				try {
					UsersProviderServiceStartup.class.wait();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}

	}
}

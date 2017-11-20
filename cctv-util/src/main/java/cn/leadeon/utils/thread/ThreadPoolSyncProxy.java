/**
 * 
 */
package cn.leadeon.utils.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author billy
 * @date 2016年12月12日
 */
public class ThreadPoolSyncProxy {

	private List<Thread> threads;
	
	private Integer poolSize;
	
	private ThreadPoolSyncCallback callback;	//所有线程执行完成后的回调方法
	
	protected Integer complete;	//设定的完成数
	
	protected ThreadPoolSyncStatus threadPoolStatus;	//线程执行状态
	
	private ExecutorService executorService;
	
	public ThreadPoolSyncProxy(List<Thread> threads, Integer poolSize, ThreadPoolSyncCallback callback){
		this.threads = threads;
		this.complete = threads.size();
		this.poolSize = poolSize;
		this.callback = callback;
	}
	
	public void submit(){
		if(null == executorService){
			executorService = Executors.newFixedThreadPool(poolSize);
		}
		
		threadPoolStatus = new ThreadPoolSyncStatus(complete, callback);
		
		List<Future> futureList = new ArrayList<Future>();
		
		Future future = null;
		for(int i=0; i<threads.size(); i++){
			future = executorService.submit(threads.get(i));
			futureList.add(future);
		}
		
		List<Future> succFuture = new ArrayList<Future>();
		while(true){
			for(int i=0; i<futureList.size(); i++){
				future = futureList.get(i);
				//成功future集合中不包含当前future，并且当前future处理完成，则继续处理
				if(!succFuture.contains(future) && future.isDone()){
					succFuture.add(future);
					threadPoolStatus.sendToCallback();
				}
			}
			
			//全部回调成功，跳出循环
			if(succFuture.size() == futureList.size()){
				break;
			}
		}
		
	}
	
	/**
	 * 使用方法示例
	 * @param args
	 */
	public static void main(String[] args){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread1 running");
				try {
					Thread.sleep(10*1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread1 completed");
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread2 running");
				try {
					Thread.sleep(13*1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("thread2 completed");
			}
		});
		
		List<Thread> list = new ArrayList<Thread>();
		list.add(t1);
		list.add(t2);
		
		final long startTime = System.currentTimeMillis();
		
		//poolSize是指定线程池的线程数目
		ThreadPoolSyncProxy proxy = new ThreadPoolSyncProxy(list, 2, new ThreadPoolSyncCallback() {
			@Override
			public void syncCallback() {
				System.out.println("全部完成,"+ (System.currentTimeMillis() - startTime)/1000);
			}
		});
		System.out.println("开始");
		proxy.submit();
		
	}
	
	
}

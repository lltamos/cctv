/**
 * 
 */
package cn.leadeon.utils.thread;

/**
 * @author billy
 * @date 2016年12月12日
 */
public class ThreadPoolSyncStatus {
	private Integer complete;	//设定的完成数
	private Integer status;		//线程回调的计数
	
	private ThreadPoolSyncCallback callback;	//所有线程执行完成后的回调方法
	
	public ThreadPoolSyncStatus(Integer complete,ThreadPoolSyncCallback callback){
		this.complete = complete;
		this.status = 0;
		this.callback = callback;
	}
	
	public void sendToCallback(){
		status ++;
		if(status.intValue() == complete.intValue()){
			if(null != callback){
				callback.syncCallback();
			}else{
				System.err.println("线程全部完成后，回调对象为空，无法完成回调");
			}
		}
	}
	
	public Integer getComplete() {
		return complete;
	}

	public Integer getStatus() {
		return status;
	}
	
}

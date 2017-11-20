package cn.leadeon.comm.base;

import java.io.Serializable;


/**   
*    
* 项目名称：leadeon-cmcc-comm
   
* 类名称：ResultData   

* 创建人：gavin

* 创建时间：2014-12-4 下午10:36:04    

* 类描述：   服务返回数据的包装类

* @version  1.0-SNAPSHOT
*    
*/ 
@SuppressWarnings("serial")
public class ResultData<T extends Object> implements Serializable{

	private String resultCode;
	
	
	private String desc;
	
	
	private Exception exception;
	
	
	private T resultData;

	
	
	public ResultData(){}

	public ResultData(String resultCode) {
		super();
		this.resultCode = resultCode;
	}
	

	public ResultData(String resultCode,T resultData) {
		super();
		this.resultCode = resultCode;
		this.resultData = resultData;
	}
	
	public ResultData(String resultCode, String desc,
			Exception exception, T resultData) {
		super();
		this.resultCode = resultCode;
		this.desc = desc;
		this.exception = exception;
		this.resultData = resultData;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
	    if(null!=exception)
	        this.exception = new RuntimeException("-- 服务方异常:"+exception.toString());
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

    /**
     * 重载方法
     * @return
     */
    @Override
    public String toString() {
        return "ResultData [resultCode=" + resultCode + ", desc=" + desc + ", exception=" + exception + ", resultData="
            + resultData + "]";
    }
	
	
	
}

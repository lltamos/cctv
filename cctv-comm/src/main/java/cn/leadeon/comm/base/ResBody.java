package cn.leadeon.comm.base;

import java.io.Serializable;

/**
 * 
 * <响应请求内容实体> <功能详细描述>
 * 
 * @author liujie
 * @version [版本号, 2014-12-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ResBody<T extends Object> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4440227766339332555L;

	private String code;
    
    private String error;
    
    private T content;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the content
	 */
	public T getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(T content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResBody [code=" + code + ", error=" + error + ", content=" + content + "]";
	}

}

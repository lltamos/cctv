/**
 * 
 */
package cn.leadeon.serializer;

import org.apache.commons.lang.SerializationException;

/**
 * 序列化接口
 * @author billy
 * @date 2016年11月22日
 */
public interface Serializer<T> {
	
	public T deserialize(byte[] bytes) throws SerializationException ;

    public byte[] serialize(T t) throws SerializationException ;
	
}

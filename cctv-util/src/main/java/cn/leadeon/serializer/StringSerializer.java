/**
 * 
 */
package cn.leadeon.serializer;

import java.nio.charset.Charset;

import org.apache.commons.lang.SerializationException;

/**
 * String方式序列化
 * @author billy
 * @date 2016年11月23日
 */
public class StringSerializer implements Serializer<String> {

	private final Charset charset = Charset.forName("UTF-8");
	
	/* (non-Javadoc)
	 * @see cn.leadeon.serializer.Serializer#deserialize(byte[])
	 */
	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
		 return (bytes == null ? null : new String(bytes, charset));
	}

	/* (non-Javadoc)
	 * @see cn.leadeon.serializer.Serializer#serialize(java.lang.Object)
	 */
	@Override
	public byte[] serialize(String t) throws SerializationException {
		return (t == null ? null : t.getBytes(charset));
	}

}

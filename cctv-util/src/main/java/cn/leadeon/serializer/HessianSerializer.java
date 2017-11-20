/**
 * 
 */
package cn.leadeon.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.SerializationException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.SerializerFactory;


/**
 * hessian 序列化方式
 * @author billy
 * @date 2016年11月22日
 */
public class HessianSerializer implements Serializer<Object> {
	private static SerializerFactory _serializerFactory;
    static {
        _serializerFactory = new SerializerFactory();
    }

    public static HessianOutput createHessianOutput(OutputStream out) {
        HessianOutput hout = new HessianOutput(out);
        hout.setSerializerFactory(_serializerFactory);
        return hout;
    }

    public static HessianInput createHessianInput(InputStream in) {
        HessianInput hin = new HessianInput(in);
        hin.setSerializerFactory(_serializerFactory);
        return hin;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            HessianInput hin = createHessianInput(input);
            return hin.readObject();
        } catch (IOException e) {
            throw new SerializationException("hessian反序列化对象出错", e);
        }
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            createHessianOutput(bout).writeObject(t);
            return bout.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("hessian序列化对象出错" + t.getClass(), e);
        }
    }
}

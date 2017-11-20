package cn.leadeon.rocketmq.consumer.callback;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.common.message.MessageExt;

import cn.leadeon.serializer.Serializer;
import cn.leadeon.serializer.StringSerializer;

public abstract class IMqConsumerCallBack {

	private Serializer serializer = new StringSerializer();
	
	public void setSerializer(Serializer serializer){
		this.serializer = serializer;
	}
	
	public Serializer getSerializer(){
		return this.serializer;
	}
	
	public abstract ConsumeConcurrentlyStatus callBack(List<MessageExt> msgs, ConsumeConcurrentlyContext context);
	
}

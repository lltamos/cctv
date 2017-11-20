package cn.leadeon.rocketmq.consumer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.leadeon.rocketmq.comm.MqConstant;
import cn.leadeon.rocketmq.consumer.callback.IMqConsumerCallBack;
import cn.leadeon.serializer.Serializer;
import cn.leadeon.serializer.StringSerializer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;


public class Consumer {

	protected final static Logger logger_consumer = LoggerFactory.getLogger("ROCKERMQ_CONSUMER");
	
	protected final static Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private String consumerGroupName;
	
	private String nameSrvAddr;
	
	private IMqConsumerCallBack callBack;	//消费消息回调方法
	
	private String tags;
	
	private DefaultMQPushConsumer consumer;
	
	private Serializer serializer = new StringSerializer();
	
	private String topic;
	
	private static final String receive_format = "{} RocketMq Consumer Receive New Messages, [{}], subscribe:[{}], Thread:[{}], Topic:[{}], Tag:[{}], Key:[{}], queueId:[{}], queueOffest:[{}], commitLogOffset:[{}], start consumption time:[{}], body:[{}]";
	
	private static final String consumption_format = "{} RocketMq Consumer Message consumption complete, [{}], Topic:[{}], Tag:[{}], Key:[{}], queueId:[{}], queueOffest:[{}], commitLogOffset:[{}], consumeStatu:[{}], time used:[{} ms]";
	
	public Consumer(String consumerGroupName, String nameSrvAddr, String tags, IMqConsumerCallBack callBack){
		/*this.consumerGroupName = consumerGroupName;
		this.nameSrvAddr = nameSrvAddr;
		this.tags = tags;
		this.callBack = callBack;
		start();*/
		this(MqConstant.TOPIC, consumerGroupName, nameSrvAddr, tags, callBack, new StringSerializer());
	}
	
	public Consumer(String consumerGroupName, String nameSrvAddr, String tags, IMqConsumerCallBack callBack, Serializer serializer){
		/*this.consumerGroupName = consumerGroupName;
		this.nameSrvAddr = nameSrvAddr;
		this.tags = tags;
		this.callBack = callBack;
		this.serializer = serializer;
		start();*/
		this(MqConstant.TOPIC, consumerGroupName, nameSrvAddr, tags, callBack, serializer);
	}
	
	public Consumer(String topic, String consumerGroupName, String nameSrvAddr, String tags, IMqConsumerCallBack callBack, Serializer serializer){
		this.consumerGroupName = consumerGroupName;
		this.nameSrvAddr = nameSrvAddr;
		this.tags = tags;
		this.callBack = callBack;
		this.serializer = serializer;
		this.topic = topic;
		start();
	}
	
	protected synchronized void start(){
		if(null != consumer){
			return ;
		}
		try{
			/**
	         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
	         * 注意：ConsumerGroupName需要由应用来保证唯一
	         */
	        consumer = new DefaultMQPushConsumer(consumerGroupName);
	        consumer.setNamesrvAddr(nameSrvAddr);
	
	        /**
	         * 订阅指定topic下tags分别等于TagA或TagC或TagD
	         * 注意：一个consumer对象可以订阅多个topic
	         */
	        consumer.subscribe(topic, tags);
	
	        /**
	         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
	         * 如果非第一次启动，那么按照上次消费的位置继续消费
	         */
	        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
	        
	        /**
	         * 设置consumer批量接收消息数目
	         * 默认为1
	         */
	        consumer.setConsumeMessageBatchMaxSize(1);
	        
	        
	        /**
	         * 消费模式：集群消费
	         * 默认为集群消费
	         */
	        consumer.setMessageModel(MessageModel.CLUSTERING);
	        
//	        consumer.setConsumeThreadMin(1);
//	        consumer.setConsumeThreadMax(1);
//	        consumer.setConsumeTimestamp();
	        
	        consumer.registerMessageListener(new MessageListenerConcurrently() {

	        	/**
	             * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
	             */
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(
						List<MessageExt> msgs,
						ConsumeConcurrentlyContext context) {
					long currTime = System.currentTimeMillis();
					MessageExt msg = msgs.get(0);
					try{
//						String bodyStr = new String(msg.getBody(),"UTF-8");
						
						Object bodyStr = serializer.deserialize(msg.getBody());
						
						logger_consumer.info(receive_format,new Object[]{ 
								sdf.format(new Date())
								,"<"+msg.getBornHostString()+" "+ msg.getStoreHost() +">"
								, "{"+topic +","+ tags+"}"
		                		, Thread.currentThread().getName()
		                		, msg.getTopic(), msg.getTags(), msg.getKeys(), msg.getQueueId(), msg.getQueueOffset(), msg.getCommitLogOffset()
		                		, sdf.format(new Date(currTime))
		                		, bodyStr.toString() });
		                
		                //logger.info("context:"+context);
					}catch(Exception e){
						//logger.error("receive message error", e);
						logger_consumer.error(sdf.format(new Date()) + " RocketMq Consumer receive message error, topic:["+msg.getTopic()+"],tag:["+msg.getTags()+"],key:["+msg.getKeys()+"]", e);
					}
	                
	                //调用各业务自己实现的回调方法
					callBack.setSerializer(serializer);
					ConsumeConcurrentlyStatus status = callBack.callBack(msgs, context);
					
//					consumption_format = "Message consumption complete, Topic:[{}], Tag:[{}], Key:[{}], queueId:[{}], time used:[{} ms]"
					logger_consumer.info(consumption_format, new Object[]{sdf.format(new Date())
							,"<"+msg.getBornHostString()+" "+ msg.getStoreHost() +">"
							,msg.getTopic()
							, msg.getTags(), msg.getKeys(), msg.getQueueId(), msg.getQueueOffset(), msg.getCommitLogOffset()
							, status
							, System.currentTimeMillis()-currTime });
					
	                return status;
				}
	        });
	        
	        consumer.start();
	        
	        logger.info("rocketmq consumer has started ! consumerGroupName is : "+consumerGroupName
	        		+ " ,nameSrvAddr : " + nameSrvAddr
	        		+ " ,subscribe : " + "{"+topic +","+ tags+"}"
	        		+ " ,messageModel : " + consumer.getMessageModel()
	        		+ " ,serializer : " + serializer.getClass()
	        		+ " ,callBack : " + callBack.getClass());
	        
		}catch(Exception e){
			logger.error("rocketmq consumer start fail...");
		}
	}

}

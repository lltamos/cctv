package cn.leadeon.rocketmq.producer;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.leadeon.rocketmq.comm.MessageDelayLevel;
import cn.leadeon.rocketmq.comm.MqConstant;
import cn.leadeon.serializer.HessianSerializer;
import cn.leadeon.serializer.Serializer;
import cn.leadeon.serializer.StringSerializer;

import com.alibaba.rocketmq.client.QueryResult;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class Producer {
	
	protected final static Logger logger_send = LoggerFactory.getLogger("ROCKERMQ_PRODUCER");

	protected final static Logger logger = LoggerFactory.getLogger(Producer.class);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private String producerGroupName;
	
	private String nameSrvAddr;
	
	private DefaultMQProducer producer;
	
	//mq消息序列化方式
	private Serializer serializer = new StringSerializer();
	
	private String topic;
	
	private static final String sendResult_format = "{} Send New Messages,broker:[{}],queueId:[{}], Topic:[{}], Tag:[{}], key:[{}], sendResult:[{}]";
	
	private int maxMessageSize = 1024 * 256;
	private int defaultTopicQueueNums = 8;
	private int sendMsgTimeout = 6000;
	
	public Producer(String producerGroupName,String nameSrvAddr){
		/*this.producerGroupName = producerGroupName;
		this.nameSrvAddr = nameSrvAddr;
		this.serializer = new StringSerializer();
		startClient();*/
		this(MqConstant.TOPIC,producerGroupName,nameSrvAddr,new StringSerializer());
	}
	
	public Producer(String producerGroupName,String nameSrvAddr, Serializer serializer){
		/*this.producerGroupName = producerGroupName;
		this.nameSrvAddr = nameSrvAddr;
		this.serializer = serializer;
		startClient();*/
		this(MqConstant.TOPIC,producerGroupName,nameSrvAddr,serializer);
	}
	
	public Producer(String topic, String producerGroupName, String nameSrvAddr, Serializer serializer){
		this.producerGroupName = producerGroupName;
		this.nameSrvAddr = nameSrvAddr;
		this.serializer = serializer;
		this.topic = topic;
		startClient();
	}
	
	private synchronized void startClient(){
		if(null != producer){
			return;
		}
		/**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(nameSrvAddr);
        
        /**
         * 发送消息超时时间，单位毫秒
         */
        producer.setSendMsgTimeout(sendMsgTimeout);
        
        /**
         * 在发送消息时，自动创建服务器不存在的topic，需要指定 Key。
         */
        producer.setCreateTopicKey(topic);
        
        /**
         * 在发送消息时，自动创建服务器不存在的topic，默认创建的队列数
         */
        producer.setDefaultTopicQueueNums(defaultTopicQueueNums);
        
        /**
         * 客户端限制的消息大小，超过报错，同时服务端也会限制
         */
        producer.setMaxMessageSize(maxMessageSize);
        
        
        
        /**
         * 设置失败重试次数
         */
        producer.setRetryTimesWhenSendFailed(2);
        
        /**
         * 设置send方法总耗时
         */
        producer.setSendMsgTimeout(10000);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        try {
			producer.start();
			
			logger.info("rocketmq producer has started ! producerGroupName is : "+producerGroupName);
			
		} catch (MQClientException e1) {
			logger.error("rocketmq producer start fail...");
		}
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                	logger.info("shutdown rocketmq producer...");
                	producer.shutdown();
                } catch (Throwable e) {
                	logger.error("rocketmq producer shutdown fail...");
                } finally {
                	logger.info("rocketmq producer shutdown...");
                }
            }
        });
	}
	
	/**
	 * 发送普通消息
	 * @param tag
	 * @param key
	 * @param body
	 */
	public void sendMsg(String tag, String key, Object body){
		sendDelayMsg(tag, key, body, MessageDelayLevel.DELAYLEVEL_NO);
	}
	
	/**
	 * 发送延时消息
	 * @param tag
	 * @param key
	 * @param body
	 * @param delayLevel
	 */
	public void sendDelayMsg(String tag, String key, Object body, MessageDelayLevel delayLevel){
		try {
			Message msg = new Message(topic,// topic
					tag,// tag
					key,// key
					serializer.serialize(body));
			msg.setDelayTimeLevel(delayLevel.getLevel());
			
			send(tag, key, msg, null, null);
		} catch (Exception e) {
			logger_send.error(sdf.format(new Date()) + " send msg fail, topic:["+topic+"],tag:["+tag+"],key:["+key+"]", e);
		}
	}
	
	public void sendMsg(String tag, String key, Object body, MessageQueueSelector selector, Object arg){
		try {
			Message msg = new Message(topic,// topic
					tag,// tag
					key,// key
					serializer.serialize(body));
			
			send(tag, key, msg, selector, arg);
		} catch (Exception e) {
			logger_send.error(sdf.format(new Date()) + " send msg fail, topic:["+topic+"],tag:["+tag+"],key:["+key+"]", e);
		}
	}
	
	private void send(String tag, String key, Message msg, MessageQueueSelector selector, Object arg) throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
		try {
			SendResult sendResult = null;
			if(null == selector){
				sendResult = producer.send(msg);
			}else{
				sendResult = producer.send(msg, selector, arg);
			}
			
			switch(sendResult.getSendStatus()){
				case SEND_OK:
					break;
				case FLUSH_DISK_TIMEOUT:
					break;
				case FLUSH_SLAVE_TIMEOUT:
					break;
				case SLAVE_NOT_AVAILABLE:
					break;
				default:
					break;
			}
			
			logger_send.info(sendResult_format,new Object[]{sdf.format(new Date()),sendResult.getMessageQueue().getBrokerName(),sendResult.getMessageQueue().getQueueId(),topic,tag,key,sendResult.toString()});
		} catch (Exception e) {
			//发送消息异常，
			logger_send.error(sdf.format(new Date()) + " send msg fail, topic:["+topic+"],tag:["+tag+"],key:["+key+"]", e);
		}
	}
	
	
}

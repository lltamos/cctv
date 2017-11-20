package cn.leadeon.rocketmq.comm;

/**
 * mq 常量配置（topic、tag）
 * @author lixuming
 *
 */
public class MqConstant {

	public static final String TOPIC = "topic-pear";
	
//	public static final String consume_group_refund = "PearRefundConsumerGroup";
//	
//	public static final String TAG_REFUND = "tag-refund";
//	
//	public static final String consume_group_test = "PearTestConsumerGroup";
//	
//	public static final String TAG_TEST = "test";
	
	
	public enum TagGroup{
		
		TAG_REFUND("tag-refund"),
		TAG_ORDERTMOUT("tag-ordertmout"),
		TAG_FLOWGIVE("tag-flowgive"),
		TAG_POINTPAY("tag-pointpay"),
		TAG_ORDERSUCCSENDMSG("tag-orderSuccSendMsg"),
		//---------------虚拟流量tag----------------
		TAG_VFLOW_REFUND("tag-vflow-refund"),
		TAG_VFLOW_ORDERTMOUT("tag-vflow-ordertmout"),
		TAG_VFLOW_FLOWGIVE("tag-vflow-flowgive"),
		TAG_VFLOW_ORDERSUCCSENDMSG("tag-vflow-orderSuccSendMsg"),
		//---------------虚拟流量tag----------------
		TAG_TEST("test");
		
		private String tag;
		
		TagGroup(String tag) {
			this.tag = tag;
		}
		
		public String getTag() {
			return tag;
		}
	}
	
}

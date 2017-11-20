package cn.leadeon.rocketmq.comm;

/**
 * mq 常量配置（topic、tag）
 * @author lixuming
 *
 */
public class VflowMqConstant {

	public static final String TOPIC = "topic-vflow";
	
	
	
	public enum TagGroup{
		
		TAG_VERIFYCODEMSG("tag-verifyCodeMsg"),
		TAG_VFLOW_REFUND("tag-vflow-refund"),
		TAG_VFLOW_ORDERTMOUT("tag-vflow-ordertmout"),
		TAG_VFLOW_FLOWGIVE("tag-vflow-flowgive"),
		TAG_VFLOW_ORDERSUCCSENDMSG("tag-vflow-orderSuccSendMsg"),
		TAG_TRANSDETAIL4MONGO("tag-transDetail4Mongo"),
		TAG_VFLOW_REDPACKETTMOUT("tag-vflow-redPacketTmout"),
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

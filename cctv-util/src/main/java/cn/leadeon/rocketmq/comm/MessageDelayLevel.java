package cn.leadeon.rocketmq.comm;

/**
 * 延时消息延时级别
 * @author lixuming
 *
 */
public enum MessageDelayLevel {

	//"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
	DELAYLEVEL_NO(0),
	DELAYLEVEL_1s (1),
	DELAYLEVEL_5s (2),
	DELAYLEVEL_10s (3),
	DELAYLEVEL_30s (4),
	DELAYLEVEL_1m (5),
	DELAYLEVEL_2m (6),
	DELAYLEVEL_3m (7),
	DELAYLEVEL_4m (8),
	DELAYLEVEL_5m (9),
	DELAYLEVEL_6m (10),
	DELAYLEVEL_7m (11),
	DELAYLEVEL_8m (12),
	DELAYLEVEL_9m (13),
	DELAYLEVEL_10m (14),
	DELAYLEVEL_20m (15),
	DELAYLEVEL_30m (16),
	DELAYLEVEL_1h (17),
	DELAYLEVEL_2h (18),
	DELAYLEVEL_3h (19),
	DELAYLEVEL_4h (20),
	DELAYLEVEL_5h (21),
	DELAYLEVEL_24h (22);
	
	private int level;
	
	MessageDelayLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return this.level;
	}
}

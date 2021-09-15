package com.haojing.dicing.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionCode {

	public static final int NOT_START = 1001;
	public static final int FINISHED = 1002;
	public static final int SHOULD_WAITING = 1003;

	public static final int SET_DICES_ERROR = 2001;
	public static final int DICES_ERROR = 2002;

	public static final int MSG_MSG_FORMAT_ERROR = 3001;
	public static final int MSG_MSG_UNKNOWN = 3002;

	public static final Map<Integer,String> MESSAGE = new HashMap<Integer,String>();
	
	static {
		MESSAGE.put(NOT_START, "活动还未开始！");
		MESSAGE.put(FINISHED, "博饼活动已经结束！");
		MESSAGE.put(SHOULD_WAITING, "您不是当前博饼人，请排队！");
		MESSAGE.put(SET_DICES_ERROR, "设置骰子出错！");
		MESSAGE.put(DICES_ERROR, "出错了~~~请重试！");
		MESSAGE.put(MSG_MSG_FORMAT_ERROR, "消息格式出错！");
		MESSAGE.put(MSG_MSG_UNKNOWN, "未知的消息！");
	}
}

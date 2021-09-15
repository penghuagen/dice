package com.haojing.dicing.vo;

/**
 * Created by yeweiping on 2020/9/17.
 */
public class Message {
    public static final String MSG_TYPE_INIT = "INIT";
    public static final String MSG_TYPE_ERROR = "ERROR";
    public static final String MSG_TYPE_START = "START";
    public static final String MSG_TYPE_DICING = "DICING";
    public static final String MSG_TYPE_DICING_RESULT = "DICING_RESULT";
    public static final String MSG_TYPE_FINISHED = "FINISHED";
    public static final String MSG_TYPE_HEARTBEAT = "HEARTBEAT";
    public static final String MSG_TYPE_BARRAGE_R = "BARRAGE_R";
    public static final String MSG_TYPE_BARRAGE_B = "BARRAGE_B";
    private String msgType;
    private String content;

    public Message() {
    }

    public Message(String msgType, String content) {
        this.msgType = msgType;
        this.content = content;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

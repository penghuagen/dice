package com.haojing.dicing.vo;

/**
 * Created by yeweiping on 2020/9/17.
 */
public class Error {
    private int code;
    private String msg;

    public Error(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

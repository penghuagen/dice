package com.haojing.dicing.exception;

/**
 * Created by yeweiping on 2020/9/15.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Throwable cause;

    public ServiceException(int code, String msg) {
        this(code, msg, null);
    }

    public ServiceException(int code) {
        this(code, null);
    }

    public ServiceException(int code, String msg, Throwable cause) {
        this.code = code;
        this.msg = msg;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        if (msg != null) {
            return msg;
        } else {
            return ExceptionCode.MESSAGE.get(code);
        }
    }

    public int getCode() {
        return code;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}

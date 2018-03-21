package com.xisheng.enums;

/**
 * Created by fanlinglong on 2018/3/20.
 */
public enum ResponseCodeEnum {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR");

    private int code;

    private String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

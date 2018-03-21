package com.xisheng.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xisheng.enums.ResponseCodeEnum;
import lombok.Getter;

/**
 * Created by fanlinglong on 2018/3/20.
 * 响应封装类
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Getter
public class ServerResponse<T> {

    /** 状态*/
    private int status;
    /** 消息*/
    private String msg;
    /** 对象*/
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCodeEnum.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> creteBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCodeEnum.ERROR.getCode(), ResponseCodeEnum.ERROR.getMsg());
    }

    public static <T> ServerResponse<T> createByError(String errorMessage){
        return new ServerResponse<T>(ResponseCodeEnum.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}

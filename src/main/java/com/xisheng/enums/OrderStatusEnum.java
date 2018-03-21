package com.xisheng.enums;

import lombok.Getter;

/**
 * Created by fanlinglong on 2018/3/21.
 * 订单状态枚举类
 */
@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

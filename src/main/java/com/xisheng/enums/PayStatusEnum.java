package com.xisheng.enums;

import lombok.Getter;

/**
 * Created by fanlinglong on 2018/3/21.
 * 订单支付状态枚举类
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

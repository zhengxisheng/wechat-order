package com.xisheng.enums;

import lombok.Getter;

/**
 * Created by fanlinglong on 2018/3/20.
 * 产品状态枚举类
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

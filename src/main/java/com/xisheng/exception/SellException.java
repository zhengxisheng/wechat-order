package com.xisheng.exception;

import com.xisheng.enums.ResultEnum;

/**
 * Created by fanlinglong on 2018/3/23.
 * 异常处理类
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}

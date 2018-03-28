package com.xisheng.utils;

import com.xisheng.enums.CodeEnum;

/**
 * Created by fanlinglong on 2018/3/28.
 * 枚举code->message工具类
 */
public class EnumUtil {

    public static <T extends CodeEnum> String getByCode(Integer code, Class<T> enumClass){

        for (T each : enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each.getMessage();
            }
        }
        return null;
    }
}

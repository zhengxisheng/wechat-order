package com.xisheng.utils;

import java.util.Random;

/**
 * Created by fanlinglong on 2018/3/21.
 * 订单orderId生成工具类
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        //随机产生一个6位数
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}

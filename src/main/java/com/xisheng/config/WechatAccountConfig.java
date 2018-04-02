package com.xisheng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by fanlinglong on 2018/3/30.
 * 微信支付配置文件
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 公众平台ID
     */
    private String mpAppId;

    /**
     * 公众平台密钥
     */
    private String mpAppSecret;
    /**
     * 开放平台ID
     */
    private String openAppId;
    /**
     * 开放平台密钥
     */
    private String openAppSecret;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户密钥
     */
    private String mchKey;
    /**
     * 商户证书路径
     */
    private String keyPath;
    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}

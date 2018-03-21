package com.xisheng.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by fanlinglong on 2018/3/20.
 * 商品明细VO实体
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    /** 商品名称*/
    private String productName;

    @JsonProperty("price")
    /** 商品价格*/
    private BigDecimal productPrice;

    @JsonProperty("description")
    /** 商品描述*/
    private String productDescription;

    @JsonProperty("icon")
    /** 商品图标*/
    private String productIcon;

}

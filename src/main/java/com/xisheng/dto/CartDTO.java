package com.xisheng.dto;

import lombok.Data;

/**
 * Created by fanlinglong on 2018/3/21.
 * 购物车实体类
 */
@Data
public class CartDTO {

    /** 商品Id*/
    private String productId;
    /** 商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

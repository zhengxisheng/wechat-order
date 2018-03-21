package com.xisheng.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanlinglong on 2018/3/21.
 * 订单子表实体类
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;
    /** 订单Id*/
    private String orderId;
    /** 商品Id*/
    private String productId;
    /** 商品名字*/
    private String productName;
    /** 商品价格*/
    private BigDecimal productPrice;
    /** 商品数量*/
    private Integer productQuantity;
    /** 商品小图*/
    private String productIcon;
    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date updateTime;

}

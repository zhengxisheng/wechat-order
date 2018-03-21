package com.xisheng.dto;

import com.xisheng.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2018/3/21.
 * 订单实体类
 */
@Data
public class OrderDTO {

    private String orderId;

    /** 买家名字*/
    private String buyerName;
    /** 买家电话*/
    private String buyerPhone;
    /** 买家地址*/
    private String buyerAddress;
    /** 买家微信openId*/
    private String buyerOpenid;
    /** 订单总金额*/
    private BigDecimal orderAmount;
    /** 订单状态 默认为0新下单*/
    private Integer orderStatus;
    /** 付款状态 默认为0未支付*/
    private Integer payStatus;
    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}

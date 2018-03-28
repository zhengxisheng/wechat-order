package com.xisheng.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xisheng.enums.OrderStatusEnum;
import com.xisheng.enums.PayStatusEnum;
import com.xisheng.pojo.OrderDetail;
import com.xisheng.utils.EnumUtil;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 修改时间*/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    public String getOrderStatus() {
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    public String getPayStatus() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}

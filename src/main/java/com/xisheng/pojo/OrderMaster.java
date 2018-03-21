package com.xisheng.pojo;

import com.xisheng.enums.OrderStatusEnum;
import com.xisheng.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanlinglong on 2018/3/21.
 * 订单主表实体类
 */
@Entity
@Data
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 付款状态 默认为0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date updateTime;
}

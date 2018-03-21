package com.xisheng.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by fanlinglong on 2018/3/20.
 * 商品信息实体
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    /** 商品名称*/
    private String productName;

    /** 商品价格*/
    private BigDecimal productPrice;

    /** 商品库存*/
    private Integer productStock;

    /** 商品描述*/
    private String productDescription;

    /** 商品小图*/
    private String productIcon;

    /** 商品状态 0正常 1下架*/
    private Integer productStatus;

    /** 商品类目*/
    private Integer categoryType;

    /** 创建时间*/
    private Date createTime;

    /** 修改时间*/
    private Date updateTime;


}

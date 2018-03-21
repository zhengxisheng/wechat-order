package com.xisheng.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/20.
 * 商品VO实体类
 */
@Data
public class ProductVO {

    /** 类目名称*/
    @JsonProperty("name")
    private String categoryName;
    /** 类目Id*/
    @JsonProperty("type")
    private Integer categoryType;

    /** 商品明细*/
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}

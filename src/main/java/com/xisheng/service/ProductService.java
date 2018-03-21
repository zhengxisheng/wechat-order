package com.xisheng.service;

import com.xisheng.dto.CartDTO;
import com.xisheng.pojo.ProductInfo;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/20.
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据商品Id查找商品信息
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 扣减库存
     * @param cartDTOList 购物车信息
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}

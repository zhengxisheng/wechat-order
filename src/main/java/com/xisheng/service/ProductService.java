package com.xisheng.service;

import com.xisheng.dto.CartDTO;
import com.xisheng.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 增加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 查询所有商品列表
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 商品上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 商品下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

    /**
     * 保存
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);
}

package com.xisheng.service.impl;

import com.xisheng.dao.ProductInfoDao;
import com.xisheng.dto.CartDTO;
import com.xisheng.enums.ProductStatusEnum;
import com.xisheng.pojo.ProductInfo;
import com.xisheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by fanlinglong on 2018/3/20.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo productInfo = productInfoDao.findOne(cartDTO.getProductId());
            if (productInfo == null){

            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0){

            }
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            productInfoDao.save(productInfo);
        }
    }
}

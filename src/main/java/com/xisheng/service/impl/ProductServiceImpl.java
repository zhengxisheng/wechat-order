package com.xisheng.service.impl;

import com.xisheng.dao.ProductInfoDao;
import com.xisheng.enums.ProductStatusEnum;
import com.xisheng.pojo.ProductInfo;
import com.xisheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

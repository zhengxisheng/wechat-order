package com.xisheng.dao;

import com.xisheng.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/20.
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);
}

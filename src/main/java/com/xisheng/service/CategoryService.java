package com.xisheng.service;

import com.xisheng.pojo.ProductCategory;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/21.
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

    List<ProductCategory> findAll();
}

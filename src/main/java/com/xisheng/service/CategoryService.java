package com.xisheng.service;

import com.xisheng.pojo.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/21.
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

    List<ProductCategory> findAll();

    Page<ProductCategory> findAll(Pageable pageable);

    ProductCategory findOne(Integer categoryId);

    ProductCategory save(ProductCategory productCategory);
}

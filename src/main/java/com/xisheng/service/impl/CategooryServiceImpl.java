package com.xisheng.service.impl;

import com.xisheng.dao.ProductCategoryDao;
import com.xisheng.pojo.ProductCategory;
import com.xisheng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/21.
 * 类目
 */
@Service
public class CategooryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList) {
        return productCategoryDao.findByCategoryTypeIn(categoryList);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryDao.findAll(pageable);
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.findOne(categoryId);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}

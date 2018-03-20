package com.xisheng.dao;

import com.xisheng.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by fanlinglong on 2018/3/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("热菜");
        productCategory.setCategoryType(2);
        productCategory.setUpdateTime(new Date());
        ProductCategory result = productCategoryDao.save(productCategory);
        Assert.assertNotNull(result);
    }

}
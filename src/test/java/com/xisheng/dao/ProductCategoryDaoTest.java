package com.xisheng.dao;

import com.xisheng.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    @Test
    public void fingByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
    @Test
    public void testDouble(){
        Double a1 = 0.01;
        BigDecimal a2 = new BigDecimal("0.01");
        if (a1.doubleValue() == a2.doubleValue()){
            System.out.println("a1==a2");
        }
        else {
            System.out.println("a1!=a2");
        }
    }

}
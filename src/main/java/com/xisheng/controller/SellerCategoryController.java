package com.xisheng.controller;

import com.xisheng.exception.SellException;
import com.xisheng.form.CategoryForm;
import com.xisheng.pojo.ProductCategory;
import com.xisheng.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * Created by fanlinglong on 2018/4/13.
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {


    @Autowired
    private CategoryService categoryService;

    /**
     * 类目列表
     * @param page 页码
     * @param size 每页条数
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductCategory> productCategoryPage = categoryService.findAll(pageRequest);
        map.put("productCategoryPage",productCategoryPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/category/list",map);
    }

    /**
     * 类目详细
     * @param categoryId 类目Id
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if (categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category",productCategory);
        }
        return new ModelAndView("/category/index",map);
    }

    /**
     * 保存或更新类目信息
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("/common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId()!=null){
                 productCategory = categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);
            productCategory.setUpdateTime(new Date());
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("/common/success",map);
    }
}

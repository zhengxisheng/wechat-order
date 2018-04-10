package com.xisheng.controller;

import com.xisheng.exception.SellException;
import com.xisheng.form.ProductForm;
import com.xisheng.pojo.ProductCategory;
import com.xisheng.pojo.ProductInfo;
import com.xisheng.service.CategoryService;
import com.xisheng.service.ProductService;
import com.xisheng.utils.KeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by fanlinglong on 2018/4/10.
 * 卖家端商品管理controller
 */

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    private static final String URL = "/sell/seller/product/list";

    private static final String ERROR = "/common/error";

    private static final String SUCCESS = "/common/success";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 商品列表
     * @param page 页码
     * @param size 每页条数
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/product/list",map);
    }

    /**
     * 商品上架
     * @param productId 商品Id
     * @param map
     * @return
     */
    @RequestMapping("/onsale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url",URL);
            return new ModelAndView(ERROR,map);
        }
        map.put("url",URL);
        return new ModelAndView(SUCCESS,map);
    }

    /**
     * 商品下架
     * @param productId 商品ID
     * @param map
     * @return
     */
    @RequestMapping("/offsale")
    public ModelAndView offSale(@RequestParam("productId")String productId,
                                Map<String,Object> map){
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url",URL);
            return new ModelAndView(ERROR,map);
        }
        map.put("url",URL);
        return new ModelAndView(SUCCESS,map);
    }

    /**
     *  商品详细
     * @param productId 商品Id
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("/product/index",map);
    }

    /**
     * 保存或更新商品
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }
}

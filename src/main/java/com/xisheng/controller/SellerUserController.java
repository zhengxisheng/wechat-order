package com.xisheng.controller;

import com.xisheng.enums.ResultEnum;
import com.xisheng.pojo.SellerInfo;
import com.xisheng.service.SellerUserService;
import com.xisheng.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * Created by fanlinglong on 2018/4/15.
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerUserService sellerUserService;

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login/login");
    }

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     * @param map
     * @param response
     * @return
     */
    @PostMapping("/login/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Map<String,Object> map,
                              HttpServletResponse response){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            map.put("msg",ResultEnum.Seller_NAME_PASSWORD_NOT_EMPTY.getMessage());
            map.put("url","/sell/seller/login");
            return new ModelAndView("/common/error");
        }
        SellerInfo sellerInfo = sellerUserService.findByUsername(username);
        if (sellerInfo == null){
            map.put("msg",ResultEnum.Seller_NAME_NOT_EXIT.getMessage());
            map.put("url","/sell/seller/login");
            return new ModelAndView("/common/error");
        }
        sellerInfo = sellerUserService.findByUsernameAndPassword(username,password);
        if (sellerInfo == null){
            map.put("msg",ResultEnum.Seller_PASSWORD_ERROR.getMessage());
            map.put("url","/sell/seller/login");
            return new ModelAndView("/common/error");
        }
        //设置Cookies
        String token = UUID.randomUUID().toString();
        CookieUtil.writeLoginToken(response,token);
        return new ModelAndView("redirect:"+"http://localhost:8080/sell/seller/order/list");
    }

    /**
     * 登出
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            CookieUtil.delLoginToken(request,response);
        }
        map.put("url","/sell/seller/login");
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        return new ModelAndView("/common/success",map);
    }
}

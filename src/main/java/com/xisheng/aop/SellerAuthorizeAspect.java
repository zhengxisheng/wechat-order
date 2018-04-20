package com.xisheng.aop;

import com.xisheng.enums.ResultEnum;
import com.xisheng.exception.SellException;
import com.xisheng.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanlinglong on 2018/4/17.
 */
@Component
@Aspect
@Slf4j
public class SellerAuthorizeAspect {


    @Pointcut("execution(public * com.xisheng.controller.Seller*.*(..))" +
            "&& !execution(public * com.xisheng.controller.SellerUserController.*(..))")
    public void verify(){}


    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String cookieVaule = CookieUtil.readLoginToken(request);
        Map<String,Object> map = new HashMap<>();
        if (cookieVaule == null){
            log.warn("【登陆校验】Cookie中查不到Cookie值");

        }
    }
}

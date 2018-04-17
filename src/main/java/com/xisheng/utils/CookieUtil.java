package com.xisheng.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fanlinglong on 2018/4/17.
 * Cookie工具类
 */
@Slf4j
public class CookieUtil {

    private static final String TOKEN = "token";

    private static final Integer EXPIRE = 60 * 60 * 24;

    /**
     * 设置cookie
     * @param response
     * @param token cookie值
     */
    public static void writeLoginToken(HttpServletResponse response,String token){
        Cookie cookie = new Cookie(TOKEN,token);
        cookie.setPath("/");
        cookie.setMaxAge(EXPIRE);
        log.info("write cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
        response.addCookie(cookie);
    }

    /**
     * 读取Cookie
     * @param request
     * @return
     */
    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies !=null){
            for (Cookie cookie : cookies){
                if (StringUtils.equals(cookie.getName(),TOKEN)){
                    log.info("read cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 删除Cookie
     * @param request
     * @param response
     */
    public static void delLoginToken(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (StringUtils.equals(cookie.getName(),TOKEN)){
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    log.info("del cookieName:{},cookieValue:{}",cookie.getName(),cookie.getValue());
                    response.addCookie(cookie);
                }
            }
        }
    }
}

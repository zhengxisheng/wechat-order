package com.xisheng.service;

import com.xisheng.pojo.SellerInfo;

/**
 * Created by fanlinglong on 2018/4/15.
 */
public interface SellerUserService {

    SellerInfo findByUsername(String username);

    SellerInfo findByUsernameAndPassword(String username,String password);
}

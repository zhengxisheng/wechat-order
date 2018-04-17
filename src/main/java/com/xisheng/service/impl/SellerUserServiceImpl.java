package com.xisheng.service.impl;

import com.xisheng.dao.SellerUserDao;
import com.xisheng.pojo.SellerInfo;
import com.xisheng.service.SellerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fanlinglong on 2018/4/15.
 */
@Service
public class SellerUserServiceImpl implements SellerUserService {

    @Autowired
    private SellerUserDao sellerUserDao;

    @Override
    public SellerInfo findByUsername(String username) {
        return sellerUserDao.findByUsername(username);
    }

    @Override
    public SellerInfo findByUsernameAndPassword(String username, String password) {
        return sellerUserDao.findByUsernameAndPassword(username,password);
    }
}

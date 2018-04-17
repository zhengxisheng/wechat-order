package com.xisheng.dao;

import com.xisheng.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fanlinglong on 2018/4/15.
 */
public interface SellerUserDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByUsername(String username);

    SellerInfo findByUsernameAndPassword(String username,String password);

}

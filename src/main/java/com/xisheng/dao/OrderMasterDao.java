package com.xisheng.dao;

import com.xisheng.pojo.OrderMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/21.
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{

    /**
     *  根据微信openId查找订单
     * @param buyerOpenid 微信openId
     * @param pageable 分页
     * @return
     */
    List<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}

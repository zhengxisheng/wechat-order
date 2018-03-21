package com.xisheng.dao;

import com.xisheng.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/21.
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String>{

    /**
     * 根据订单Id查找订单明细
     * @param orderId 订单Id
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}

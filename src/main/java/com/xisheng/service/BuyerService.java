package com.xisheng.service;

import com.xisheng.dto.OrderDTO;

/**
 * Created by fanlinglong on 2018/3/27.
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);
}

package com.xisheng.service.impl;

import com.xisheng.dao.OrderDetailDao;
import com.xisheng.dto.CartDTO;
import com.xisheng.dto.OrderDTO;
import com.xisheng.enums.OrderStatusEnum;
import com.xisheng.enums.PayStatusEnum;
import com.xisheng.enums.ResultEnum;
import com.xisheng.exception.SellException;
import com.xisheng.pojo.OrderDetail;
import com.xisheng.pojo.OrderMaster;
import com.xisheng.pojo.ProductInfo;
import com.xisheng.service.OrderService;
import com.xisheng.service.ProductService;
import com.xisheng.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fanlinglong on 2018/3/21.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;


    /**
     *  创建订单
     * @param orderDTO orderDetail实体类传产品Id,数量
     * @return
     */
    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //订单orderId
        String orderId = KeyUtil.getUniqueKey();
        //订单总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        //1.查询商品(价格,数量)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmout = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmout);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);
        }
        //3.写入订单表(orderMaster和orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setUpdateTime(new Date());
        //jdk1.8新特性 lambda
        List<CartDTO> orderDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                                        new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        //4.扣库存
        productService.decreaseStock(orderDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}

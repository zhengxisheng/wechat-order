package com.xisheng.service.impl;

import com.xisheng.converter.OrderMaster2OrderDTO;
import com.xisheng.dao.OrderDetailDao;
import com.xisheng.dao.OrderMasterDao;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fanlinglong on 2018/3/21.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;


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
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
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
        orderMasterDao.save(orderMaster);
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
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单]订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderDTO.setUpdateTime(new Date());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updataResult = orderMasterDao.save(orderMaster);
        if (updataResult == null){
            log.error("[取消订单]更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单]订单中无商品详情,orderDto={}",orderDTO);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //封装CartDto
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付,需执行退款操作 TODO
        return orderDTO;

    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            log.error("【完结订单】更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付】订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付】订单支付状态不正确,payStatus={}",orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            log.error("【订单支付】更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}

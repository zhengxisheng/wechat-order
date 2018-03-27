package com.xisheng.controller;

import com.xisheng.common.ServerResponse;
import com.xisheng.converter.OrderForm2OrderDTO;
import com.xisheng.dto.OrderDTO;
import com.xisheng.enums.ResultEnum;
import com.xisheng.exception.SellException;
import com.xisheng.form.OrderForm;
import com.xisheng.service.BuyerService;
import com.xisheng.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanlinglong on 2018/3/26.
 * 买家订单管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    /**
     * 创建订单
     * @param orderForm  订单Form
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/create")
    public ServerResponse<Map<String,String>> create(@Valid OrderForm orderForm,
                                                     BindingResult bindingResult){
        //数据校验
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车为空,items={}",orderForm.getItems());
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        //创建订单
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 订单列表
     * @param openid 买家微信openid
     * @param page 页码
     * @param size 每页条数
     * @return
     */
    @GetMapping(value = "/list")
    public ServerResponse<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page",defaultValue = "0") Integer page,
                                               @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
        return ServerResponse.createBySuccess(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param openid 微信openid
     * @param orderId 订单Id
     * @return
     */
    @GetMapping("/detail")
    public ServerResponse<OrderDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        if (StringUtils.isEmpty(openid)||StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】参数错误,openid={},orderId={}",openid,orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ServerResponse.createBySuccess(orderDTO);
    }

    /**
     * 取消订单
     * @param openid 微信openId
     * @param orderId 订单Id
     * @return
     */
    @PostMapping("/cancel")
    public ServerResponse cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        if (StringUtils.isEmpty(openid)||StringUtils.isEmpty(orderId)){
            log.error("【取消订单】参数错误,openid={},orderId={}",openid,orderId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid,orderId);
        return ServerResponse.createBySuccess();
    }

}

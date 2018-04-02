package com.xisheng.controller;

import com.lly835.bestpay.model.PayResponse;
import com.xisheng.dto.OrderDTO;
import com.xisheng.enums.ResultEnum;
import com.xisheng.exception.SellException;
import com.xisheng.service.OrderService;
import com.xisheng.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanlinglong on 2018/4/2.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 创建支付
     * @param orderId
     * @param returnUrl
     * @return
     */
    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponse = payService.create(orderDTO);
        Map<String,Object> map = new HashMap<>();
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("/pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     * @return
     */
    @PostMapping("/nofity")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}

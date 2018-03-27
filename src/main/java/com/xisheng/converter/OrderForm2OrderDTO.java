package com.xisheng.converter;

import com.xisheng.dto.OrderDTO;
import com.xisheng.form.OrderForm;
import com.xisheng.pojo.OrderDetail;
import com.xisheng.utils.JsonUtil;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * Created by fanlinglong on 2018/3/27.
 * orderForm->orderDTO
 */
public class OrderForm2OrderDTO {

    /**
     * orderForm转化成orderDto
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        //订单json转化成对象List
        List<OrderDetail> orderDetailList = JsonUtil.String2Obj(orderForm.getItems(),
                new TypeReference<List<OrderDetail>>() {
        });
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}

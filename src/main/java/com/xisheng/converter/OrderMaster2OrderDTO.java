package com.xisheng.converter;

import com.xisheng.dto.OrderDTO;
import com.xisheng.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fanlinglong on 2018/3/25.
 */
public class OrderMaster2OrderDTO {

    /**
     * orderMaster->orderDto
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    /**
     * List<OrderMaster>->List<OrderDto>
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> convert(e))
                .collect(Collectors.toList());
    }
}

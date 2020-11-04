package com.lc.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lc.order.dataobject.OrderDetail;
import com.lc.order.dto.OrderDto;
import com.lc.order.enums.ResultEnum;
import com.lc.order.exception.OrderException;
import com.lc.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单表单转换类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm) {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        // string -> Bean List转换操作.
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("[json转换]错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        // 订单信息
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}

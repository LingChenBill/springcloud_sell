package com.lc.order.service.impl;

import com.lc.order.dataobject.OrderMaster;
import com.lc.order.dto.OrderDto;
import com.lc.order.enums.OrderStatusEnum;
import com.lc.order.enums.PayStatusEnum;
import com.lc.order.repository.OrderDetailRepository;
import com.lc.order.repository.OrderMasterRepository;
import com.lc.order.service.OrderService;
import com.lc.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单服务实现类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDto create(OrderDto orderDto) {

        // TODO: 查询商品信息
        // TODO: 计算总价
        // TODO: 扣库存

        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        // 生成唯一主键.
        orderDto.setOrderId(KeyUtil.getUniqueKey());
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        orderMasterRepository.save(orderMaster);
        return orderDto;
    }
}

package com.lc.order.repository;

import com.lc.order.OrderApplicationTests;
import com.lc.order.dataobject.OrderMaster;
import com.lc.order.enums.OrderStatusEnum;
import com.lc.order.enums.PayStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("100000001");
        orderMaster.setBuyerName("小明");
        orderMaster.setBuyerPhone("18610220321");
        orderMaster.setBuyerAddress("西二旗");
        orderMaster.setBuyerOpenid("110110001");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assertions.assertTrue(result != null);
    }
}
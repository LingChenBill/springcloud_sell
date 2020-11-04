package com.lc.order.repository;

import com.lc.order.OrderApplicationTests;
import com.lc.order.dataobject.OrderDetail;
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
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("100001");
        orderDetail.setOrderId("100000001");
        orderDetail.setProductIcon("http://www.xxx.com/icon1.jpeg");
        orderDetail.setProductId("10000010001");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.02));
        orderDetail.setProductQuantity(2);
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assertions.assertTrue(result != null);
    }
}
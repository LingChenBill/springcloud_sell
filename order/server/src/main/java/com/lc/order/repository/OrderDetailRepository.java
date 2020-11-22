package com.lc.order.repository;

import com.lc.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单信息。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 查询订单信息列表.
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}

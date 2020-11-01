package com.lc.order.repository;

import com.lc.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单信息。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}

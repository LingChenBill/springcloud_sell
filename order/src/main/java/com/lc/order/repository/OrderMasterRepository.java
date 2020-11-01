package com.lc.order.repository;

import com.lc.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单仓库类。
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}

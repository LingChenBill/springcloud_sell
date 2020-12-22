package com.lc.order.service.impl;

import com.lc.order.dataobject.OrderDetail;
import com.lc.order.dataobject.OrderMaster;
import com.lc.order.dto.OrderDto;
import com.lc.order.enums.OrderStatusEnum;
import com.lc.order.enums.PayStatusEnum;
import com.lc.order.enums.ResultEnum;
import com.lc.order.exception.OrderException;
import com.lc.order.repository.OrderDetailRepository;
import com.lc.order.repository.OrderMasterRepository;
import com.lc.order.service.OrderService;
import com.lc.order.utils.KeyUtil;
import com.lc.product.client.ProductClient;
import com.lc.product.common.DecreaseStockInput;
import com.lc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单服务实现类.
 * @description:
 * @author: lingchen
 * @date: 2020/11/1
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        // 查询商品信息.
        List<String> productIdList = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        log.info("查询商品信息(调用商品服务)");
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 计算总价.
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    // 单价 * 数量.
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    // 保存商品信息表.
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存.
        List<DecreaseStockInput> decreaseStockInputList = orderDto.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        // 调用商品微服务扣库存接口.
        productClient.decreaseStock(decreaseStockInputList);

        // 订单入库.
        OrderMaster orderMaster = new OrderMaster();
        // 生成唯一主键.
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        orderMasterRepository.save(orderMaster);
        return orderDto;
    }

    /**
     * 完结订单(只能卖家操作).
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public OrderDto finish(String orderId) {
        // 先查询订单.
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 判断订单状态.
        OrderMaster orderMaster = orderMasterOptional.get();
        if (OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 修改订单状态为完结.
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        // 查询订单详情.
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}

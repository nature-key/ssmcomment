package com.jiepi.service;

import com.jiepi.bean.Orders;
import com.jiepi.dto.OrderDto;

public interface OrderService {

    int add(OrderDto orders);

    OrderDto selectById(Long id);
}

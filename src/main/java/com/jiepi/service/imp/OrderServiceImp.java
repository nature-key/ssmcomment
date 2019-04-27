package com.jiepi.service.imp;

import com.jiepi.bean.Orders;
import com.jiepi.constant.CommentStateConst;
import com.jiepi.dao.OrderDao;
import com.jiepi.dto.OrderDto;
import com.jiepi.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int add(OrderDto orderDto) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderDto, orders);
        orders.setCommentState(CommentStateConst.NOT_COMMENT);
        int count = orderDao.insert(orders);
        return count;
    }

    @Override
    public OrderDto selectById(Long id) {
        OrderDto orderDto = new OrderDto();
        Orders orders = orderDao.selectById(id);
        BeanUtils.copyProperties(orders, orderDto);
        return orderDto;
    }
}

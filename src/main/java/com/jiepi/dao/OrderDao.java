package com.jiepi.dao;

import com.jiepi.bean.Orders;

public interface OrderDao {

    int insert (Orders orders);

    Orders selectById(Long id);

    /**
     * 修改
     * @param orders 订单表对象
     * @return 影响行数
     */
    int update(Orders orders);
}

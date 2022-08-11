package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.model.Order;

public interface IOrderService {
    Order save(Order order);
    Order findById(Long id);

}

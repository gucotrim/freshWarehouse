package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.exception.InboundOrderNotFoundException;
import com.meli.freshWarehouse.model.Order;
import com.meli.freshWarehouse.repository.OrderRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Order save(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new InboundOrderNotFoundException("Inbound Order ID not found."));
    }
}

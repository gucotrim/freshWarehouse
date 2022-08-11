package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;
import com.meli.freshWarehouse.model.Order;

public interface IInboundOrderService {
    InboundOrderResponseDto save(InboundOrderDto inboundOrderDto);
    InboundOrderResponseDto update (Long id, InboundOrderDto inboundOrderDto);
    Order getInboundOrderById (Long inboundOrderId);
}

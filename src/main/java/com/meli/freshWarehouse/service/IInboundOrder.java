package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.model.InboundOrder;

public interface IInboundOrder {


    InboundOrder update ( Long id, InboundOrderDto inboundOrderDto);

    InboundOrder getInboundOrderById (Long inboundOrderId);
}

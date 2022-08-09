package com.meli.freshWarehouse.service;

import com.meli.freshWarehouse.dto.InboundOrderDto;
import com.meli.freshWarehouse.dto.InboundOrderResponseDto;

public interface IInboundOrderService {
    InboundOrderResponseDto save(InboundOrderDto inboundOrderDto);
}

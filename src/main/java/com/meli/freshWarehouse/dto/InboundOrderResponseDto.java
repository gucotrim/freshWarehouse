package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InboundOrderResponseDto {

        private Order order;
        private List<BatchResponseDto> batchStockList;
}

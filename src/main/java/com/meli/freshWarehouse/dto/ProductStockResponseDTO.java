package com.meli.freshWarehouse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class ProductStockResponseDTO {

    private Long productId;
    private List<WarehouseForProductStockResponseDTO> warehouses;
}

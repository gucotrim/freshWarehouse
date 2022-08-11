package com.meli.freshWarehouse.dto;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseForProductStockResponseDTO {

    private Long warehouseId;
    private Long totalQuantity;
}

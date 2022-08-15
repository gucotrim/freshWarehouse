package com.meli.freshWarehouse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseOrderTotalPriceDTO {
    private Long id;
    private Double totalPrice;
}

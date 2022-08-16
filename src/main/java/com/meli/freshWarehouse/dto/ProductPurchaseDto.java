package com.meli.freshWarehouse.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseDto {

    private Long id;
    private String name;
    private Double price;
}

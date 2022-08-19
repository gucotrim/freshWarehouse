package com.meli.freshWarehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter @Setter
public class ShoppingCartProductDto {
    @Positive
    @NotNull
    private Long productId;

    @Positive
    @NotNull
    private Integer quantity;
}

package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter @Setter
public class ShoppingCartProductDto {
    private Long productId;

    @Positive
    @NotNull
    private Integer quantity;
}

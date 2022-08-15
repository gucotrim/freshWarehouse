package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.PurchaseOrder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ShoppingCartProductDto {
    private Product product;

    @Positive
    @NotNull
    private Integer quantity;

    private PurchaseOrder purchaseOrder;
}

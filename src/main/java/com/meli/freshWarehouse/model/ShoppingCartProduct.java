package com.meli.freshWarehouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@Entity
@Table(name = "shopping_cart_product")
public class ShoppingCartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Positive
    @NotNull
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_purchase_order", nullable = false)
    private PurchaseOrder purchaseOrder;
}

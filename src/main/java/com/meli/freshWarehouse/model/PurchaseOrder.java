package com.meli.freshWarehouse.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column(name = "order_status")
    private String orderStatus;

    @OneToOne
    @JoinColumn(name = "id_shopping_cart", referencedColumnName = "id")
    private ShoppingCart shoppingCart;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_buyer", nullable = false)
    private Buyer buyer;
}

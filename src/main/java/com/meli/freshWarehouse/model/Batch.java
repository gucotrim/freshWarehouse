package com.meli.freshWarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "current_temperature")
    private float currentTemperature;

    @Column(name = "minimum_temperature")
    private float minimumTemperature;

    @Column(name = "initial_quantity")
    private int initialQuantity;

    @Column(name = "current_quantity")
    private int currentQuantity;

    @Column(name = "manufacturing_date")
    private Date manufacturingDate;

    @Column(name = "manufacturing_time")
    private Date manufacturingTime;

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_section", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;


}

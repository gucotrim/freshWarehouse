package com.meli.freshWarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_temperature")
    private Float currentTemperature;

    @Column(name = "minimum_temperature")
    private Float minimumTemperature;

    @Column(name = "initial_quantity")
    private Integer initialQuantity;

    @Column(name = "current_quantity")
    private Integer currentQuantity;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "manufacturing_time")
    private LocalDateTime manufacturingTime;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") TODO Voltar aqui
    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "id_section", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

}

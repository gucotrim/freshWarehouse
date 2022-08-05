package com.meli.freshWarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity Section Class
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    @NotEmpty(message = "The field name cannot be empty")
    private String name;

    @NotEmpty(message = "The field cannot be empty")
    @NotNull(message = "The file cannot be empty")
    @Column(name = "available_space", nullable = false)
    private int availableSpace;

    @ManyToOne
    @JoinColumn(name = "id_warehouse", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<Order> listOrder;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<Product> listProduct;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<Batch> listBatch;

}

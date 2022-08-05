package com.meli.freshWarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "available_space")
    private String availableSpace;

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

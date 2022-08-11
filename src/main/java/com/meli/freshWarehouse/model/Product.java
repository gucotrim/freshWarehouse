package com.meli.freshWarehouse.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Product name cannot exceed 45 characters")
    private String name;

    @Column(name = "price", nullable = false)
    @Digits(integer = 4, fraction = 2)
    @DecimalMax(value = "10000.0", message = "Price cannot exceed 10000.0") @DecimalMin(value = "0.0", message = "Price should be greater then zero")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_seller", nullable = false)
    private Seller seller;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
            ,mappedBy = "products"
    )
    private Set<Section> sections;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Batch> listBatch;

    public Product(String name, Double price, Long sellerId, Set<Long> sections) {
    }
}

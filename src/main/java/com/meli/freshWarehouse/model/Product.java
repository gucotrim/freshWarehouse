package com.meli.freshWarehouse.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
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

    @ManyToOne
    @JoinColumn(name = "id_section", nullable = false)
    private Section section;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Batch> listBatch;

    public Product(String name, Double price, Seller seller, Section section) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.section = section;
    }

}

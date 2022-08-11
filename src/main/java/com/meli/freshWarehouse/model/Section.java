package com.meli.freshWarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity Section Class
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    @NotEmpty(message = "The field name cannot be empty")
    private String name;

    @NotNull(message = "The field cannot be empty")
    @Column(name = "available_space", nullable = false)
    private Integer availableSpace;

    @ManyToOne
    @JoinColumn(name = "id_warehouse", nullable = false)
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    @JsonIgnore
    private Set<Order> listOrder;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "section_product",
            joinColumns = @JoinColumn(name = "id_section"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    @JsonIgnore
    private Set<Product> products;

    @OneToMany(mappedBy = "section")
    @JsonIgnore
    private Set<Batch> listBatch;

}

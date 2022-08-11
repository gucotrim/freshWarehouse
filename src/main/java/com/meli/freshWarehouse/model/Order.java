package com.meli.freshWarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") TODO - voltar aqui
    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "id_representative", nullable = false)
    private Representative representative;

    @ManyToOne
    @JoinColumn(name = "id_section", nullable = false)
    private Section section;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<Batch> listBatch;

}

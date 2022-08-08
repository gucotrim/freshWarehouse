package com.meli.freshWarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnore
    private Set<Representative> listRepresentative;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnore
    private Set<Section> listSection;


    public Warehouse(String address, String city, String state, String country, Integer number) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
    }


}

package com.meli.freshWarehouse.dto;


import com.meli.freshWarehouse.model.Warehouse;
import lombok.Getter;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter

public class WarehouseDTO {
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotNull
    @Positive
    private Integer number;

    @Deprecated
    public WarehouseDTO() {

    }

    public WarehouseDTO(String address, String city, String state, String country, Integer number) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
    }

    public Warehouse toModel(){
        return new Warehouse(this.address, this.city, this.state, this.country, this.number);
    }
}

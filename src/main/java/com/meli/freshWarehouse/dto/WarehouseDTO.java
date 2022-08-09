package com.meli.freshWarehouse.dto;


import com.meli.freshWarehouse.model.Warehouse;
import lombok.Getter;


import javax.validation.constraints.*;

@Getter

public class WarehouseDTO {
    @NotBlank
    @Size(max = 60)
    private String address;

    @NotBlank
    @Size(max = 45)
    private String city;

    @NotBlank
    @Size(max = 45)
    private String state;

    @NotBlank
    @Size(max = 45)
    private String country;

    @NotNull
    @Positive
    @Max(45)
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

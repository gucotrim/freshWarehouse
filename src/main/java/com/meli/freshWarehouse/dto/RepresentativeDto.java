package com.meli.freshWarehouse.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
public class RepresentativeDto {
    @NotBlank(message = "Representative name cannot be blank.")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôöúçñ/s]+", message = "The representative's name must begin with a capital letter.")
    @Size(max = 50, message = "Name length cannot exceed 50 characters.")
    private String name;

    @NotNull(message = "Warehouse Id cannot be null.")
    @Min(value = 0, message = "Warehouse Id must be a positive number.")
    private long warehouseId;
}

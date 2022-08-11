package com.meli.freshWarehouse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
@Builder
public class RepresentativeDTO {
    @NotBlank(message = "Representative name cannot be blank.")
    @Pattern(regexp = "^[A-Z][a-z]*(?: [A-Z][a-z]*)*$", message = "The representative's name must begin with a capital letter.")
    @Size(max = 50, message = "Name length cannot exceed 50 characters.")
    private String name;

    @NotNull(message = "Warehouse Id cannot be null.")
    @Min(value = 0, message = "Warehouse Id must be a positive number.")
    private Long warehouseId;
}

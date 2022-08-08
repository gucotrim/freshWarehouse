package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerDto {

    @NotEmpty(message = "Seller name cannot be empty.")
    @Size(max = 50, message = "Seller name can't exceed 50 characters.")
    @Pattern(regexp = "^[A-Z][a-z]*(?: [A-Z][a-z]*)*$",
            message = "The representative's name must begin with a capital letter.")
    private String name;

    public Seller toModel() {
        return new Seller(this.name);
    }
}

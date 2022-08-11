package com.meli.freshWarehouse.dto;

import com.meli.freshWarehouse.model.Product;
import com.meli.freshWarehouse.model.Section;
import com.meli.freshWarehouse.model.Seller;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
public class ProductDTO {

    @NotBlank(message = "Product name cannot be blank.")
    @Pattern(regexp = "^[A-Z][a-z]*(?: [A-Z][a-z]*)*$", message = "The product's name must begin with a capital letter.")
    private String name;

    @NotNull
    @Positive
    private Double price;

//    @NotNull(message = "Section Id cannot be null.")
//    @Min(value = 0, message = "Section Id must be a positive number.")
    private Set<Long> sectionsId;

    @NotNull(message = "Seller Id cannot be null.")
    @Min(value = 0, message = "Seller Id must be a positive number.")
    private Long sellerId;


    @Deprecated
    public ProductDTO() {

    }

    public Product toModel() {
        return new Product(this.name, this.price, this.sellerId, this.sectionsId);
    }

}

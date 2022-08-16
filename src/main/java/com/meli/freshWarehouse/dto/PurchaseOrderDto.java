package com.meli.freshWarehouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.freshWarehouse.model.Buyer;
import com.meli.freshWarehouse.model.ShoppingCartProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PurchaseOrderDto {

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The valid Date is: 'yyyy-MM-dd'")
    @NotBlank(message = "The field is not Blank.")
    private String date;

    private Long buyerId;

    private Set<ShoppingCartProductDto> shoppingCartProducts;
}

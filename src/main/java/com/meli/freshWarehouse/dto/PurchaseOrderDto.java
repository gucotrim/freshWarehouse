package com.meli.freshWarehouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.freshWarehouse.model.Buyer;
import com.meli.freshWarehouse.model.ShoppingCartProduct;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

public class PurchaseOrderDto {

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "The valid Date is: 'yyyy-MM-dd'")
    @NotBlank(message = "The field is not Blank.")
    private LocalDate date;

    private String buyerId;

    private String orderStatus;

    private Set<ShoppingCartProduct> shoppingCartProducts;
}

package com.meli.freshWarehouse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.freshWarehouse.model.PurchaseOrder;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class BuyerDto {

    @NotEmpty(message = "Buyer name cannot be empty.")
    @Size(max = 50, message = "Buyer name can't exceed 50 characters.")
    private String name;
}

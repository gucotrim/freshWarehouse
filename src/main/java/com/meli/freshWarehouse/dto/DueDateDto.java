package com.meli.freshWarehouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DueDateDto {

    private Long batchNumber;

    private Long productId;

    private String productTypeId;

    private LocalDateTime dueDate;

    private Integer quantity;

    public DueDateDto() {
    }

    public DueDateDto(Long batchNumber, Long productId, String productTypeId, LocalDateTime dueDate, Integer quantity) {
        this.batchNumber = batchNumber;
        this.productId = productId;
        this.productTypeId = productTypeId;
        this.dueDate = dueDate;
        this.quantity = quantity;
    }
}
